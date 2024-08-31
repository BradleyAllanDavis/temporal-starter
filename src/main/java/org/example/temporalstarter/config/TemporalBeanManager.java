package org.example.temporalstarter.config;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.example.temporalstarter.WorkerConfig;
import org.example.temporalstarter.WorkerConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class TemporalBeanManager {

    private final TemporalClusterAvailabilityService temporalClusterAvailabilityService;
    private final WorkerConfigService workerConfigService;

    private Optional<WorkflowServiceStubs> workflowServiceStubs = Optional.empty();
    private Optional<WorkflowClient> workflowClient = Optional.empty();
    private Optional<WorkerFactory> workerFactory = Optional.empty();
//    private final Map<String, Optional<Worker>> workers = new HashMap<>();
    private boolean workersStarted = false;
    private boolean workersRegistered = false;

    @Autowired
    public TemporalBeanManager(TemporalClusterAvailabilityService temporalClusterAvailabilityService,
                               WorkerConfigService workerConfigService) {
        this.temporalClusterAvailabilityService = temporalClusterAvailabilityService;
        // Setup to monitor cluster changes
        this.temporalClusterAvailabilityService.setOnClusterAvailabilityChange(this::onClusterAvailabilityChange);
        this.workerConfigService = workerConfigService;
    }

    public synchronized Optional<WorkflowServiceStubs> getWorkflowServiceStubs() {
        if (!workflowServiceStubs.isPresent() && temporalClusterAvailabilityService.isTemporalClusterAvailable()) {
            workflowServiceStubs = Optional.of(WorkflowServiceStubs.newLocalServiceStubs());
        }
        return workflowServiceStubs;
    }

    public synchronized Optional<WorkflowClient> getWorkflowClient() {
        if (!workflowClient.isPresent() && temporalClusterAvailabilityService.isTemporalClusterAvailable()) {
            workflowClient = getWorkflowServiceStubs().map(WorkflowClient::newInstance);
        }
        return workflowClient;
    }

    public synchronized Optional<WorkerFactory> getWorkerFactory() {
        if (!workerFactory.isPresent() && temporalClusterAvailabilityService.isTemporalClusterAvailable()) {
            workerFactory = getWorkflowClient().map(WorkerFactory::newInstance);
        }
        return workerFactory;
    }

    public synchronized void registerAllWorkers(Map<String, WorkerConfig> workerConfigs) {
        Optional<WorkerFactory> workerFactoryOpt = getWorkerFactory();
        if (workerFactoryOpt.isPresent() && !workersRegistered) {
            WorkerFactory factory = workerFactoryOpt.get();
            for (Map.Entry<String, WorkerConfig> entry : workerConfigs.entrySet()) {
                String taskQueue = entry.getKey();
                WorkerConfig config = entry.getValue();
                Worker worker = factory.newWorker(taskQueue);
                worker.registerWorkflowImplementationTypes(
                        config.getWorkflowImplementationTypes().toArray(new Class<?>[0])
                );
                worker.registerActivitiesImplementations(
                        config.getActivityImplementations().toArray()
                );
//                workers.put(taskQueue, Optional.of(worker));
            }
            workersRegistered = true;
        }
    }

//    public synchronized Optional<Worker> getWorker(String taskQueue) {
//        if (workersStarted) {
//            // Workers have already been started, so new workers cannot be created
//            return workers.getOrDefault(taskQueue, Optional.empty());
//        }
//        if (!workers.containsKey(taskQueue)) {
//            Optional<Worker> worker = Optional.empty();
//            Optional<WorkerFactory> workerFactoryOpt = getWorkerFactory();
//            if (workerFactoryOpt.isPresent()) {
//                worker = workerFactoryOpt.map(factory -> {
//                    Worker newWorker = factory.newWorker(taskQueue);
//                    newWorker.registerWorkflowImplementationTypes(WorkflowImpl.class);
//                    newWorker.registerActivitiesImplementations(new ActivityImpl());
//                    return newWorker;
//                });
//                workers.put(taskQueue, worker);
//            }
//        }
//        return workers.get(taskQueue);
//    }

    public boolean areWorkersStarted() {
        return workersStarted;
    }

    public synchronized void startWorkers() {
        if (!workersStarted) {
            registerAllWorkers(workerConfigService.getWorkerConfigs());
            getWorkerFactory().ifPresent(factory -> {
                factory.start();
                workersStarted = true;
            });
        }
    }

    public synchronized void stopWorkers() {
        if (workersStarted) {
            workerFactory.ifPresent(WorkerFactory::shutdown);
            workersStarted = false;
        }
    }

    private void onClusterAvailabilityChange(boolean isAvailable) {
        if (isAvailable) {
            startWorkers();
        } else {
            stopWorkers();
        }
    }
}
