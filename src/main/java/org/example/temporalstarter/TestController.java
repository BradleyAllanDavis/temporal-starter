package org.example.temporalstarter;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.example.temporalstarter.config.TemporalBeanManager;
import org.example.temporalstarter.config.TemporalClusterAvailabilityService;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@Lazy
@RestController
@RequestMapping("/temporal")
public class TestController {

    private final TemporalBeanManager temporalBeanManager;
    private final TemporalClusterAvailabilityService temporalClusterAvailabilityService;

    public TestController(TemporalBeanManager temporalBeanManager,
                          TemporalClusterAvailabilityService temporalClusterAvailabilityService) {
        this.temporalBeanManager = temporalBeanManager;
        this.temporalClusterAvailabilityService = temporalClusterAvailabilityService;
    }

    @GetMapping("/test")
    public String test() {
//        // Get or create the worker for the task queue
//        Optional<Worker> workerOptional = temporalBeanManager.getWorker(taskQueue);
//
//        if (!workerOptional.isPresent()) {
//            return "Temporal client unavailable, falling back to non-Temporal workflow.";
//        }

        // Workers already started, proceed with workflow
//        if (!temporalBeanManager.areWorkersStarted()) {
//            temporalBeanManager.startWorkers();
//        }
        if (temporalClusterAvailabilityService.isTemporalClusterAvailable()) {
            Optional<WorkflowClient> workflowClientOptional = temporalBeanManager.getWorkflowClient();
            if (!workflowClientOptional.isPresent()) {
                return "Temporal client unavailable, falling back to non-Temporal workflow.";
            }

            WorkflowClient workflowClient = workflowClientOptional.get();
            WorkflowInterface workflow = workflowClient.newWorkflowStub(WorkflowInterface.class,
                    WorkflowOptions.newBuilder()
                            .setWorkflowId(UUID.randomUUID().toString().substring(0, 4))
                            .setTaskQueue(TaskQueue.STARTER)
                            .build()
            );
            WorkflowClient.start(workflow::echoCheck);

            return "Temporal workflow started successfully.";
        } else {
            return "cluster unavailable, falling back to non-Temporal workflow.";
        }
    }

//    @GetMapping("/test")
//    public String test() {
//        String taskQueue = "starter";
//
//        // Get or create the worker for the task queue
//        Optional<Worker> workerOptional = temporalBeanManager.getWorker(taskQueue);
//
//        workerOptional.ifPresent(worker -> {
//            // Register workflow and activity implementations if the worker is not already started
//            if (!temporalBeanManager.areWorkersStarted()) {
//                worker.registerWorkflowImplementationTypes(WorkflowImpl.class);
//                worker.registerActivitiesImplementations(new ActivityImpl());
//
//                // Start the workers
//                temporalBeanManager.startWorkers();
//            }
//        });
//
//        // Get the WorkflowClient
//        Optional<WorkflowClient> workflowClientOptional = temporalBeanManager.getWorkflowClient();
//
//        if (workflowClientOptional.isPresent()) {
//            WorkflowClient workflowClient = workflowClientOptional.get();
//
//            // Create a workflow stub and start the workflow
//            WorkflowInterface workflow = workflowClient.newWorkflowStub(WorkflowInterface.class,
//                    WorkflowOptions.newBuilder()
//                            .setWorkflowId(UUID.randomUUID().toString().substring(0, 4))
//                            .setTaskQueue(taskQueue)
//                            .build()
//            );
//            WorkflowClient.start(workflow::echoCheck);
//
//            return "Temporal workflow started successfully.";
//        } else {
//            return "Temporal client unavailable, falling back to non-Temporal workflow.";
//        }
//    }

//    @GetMapping("/test")
//    public String test() {
//        String taskQueue = "starter";
//
//        // Get or create the worker for the task queue
//        Optional<Worker> workerOptional = temporalBeanManager.getWorker(taskQueue);
//
//        if (workerOptional.isPresent()) {
//            Worker worker = workerOptional.get();
//
//            // Check if the workers are already started
//            if (!temporalBeanManager.areWorkersStarted()) {
//                // Register workflow and activity implementations before starting the workers
//                worker.registerWorkflowImplementationTypes(WorkflowImpl.class);
//                worker.registerActivitiesImplementations(new ActivityImpl());
//
//                // Start the workers
//                temporalBeanManager.startWorkers();
//            }
//
//            // Get the WorkflowClient
//            Optional<WorkflowClient> workflowClientOptional = temporalBeanManager.getWorkflowClient();
//
//            if (workflowClientOptional.isPresent()) {
//                WorkflowClient workflowClient = workflowClientOptional.get();
//
//                // Create a workflow stub and start the workflow
//                WorkflowInterface workflow = workflowClient.newWorkflowStub(WorkflowInterface.class,
//                        WorkflowOptions.newBuilder()
//                                .setWorkflowId(UUID.randomUUID().toString().substring(0, 4))
//                                .setTaskQueue(taskQueue)
//                                .build()
//                );
//                WorkflowClient.start(workflow::echoCheck);
//
//                return "Temporal workflow started successfully.";
//            } else {
//                return "Temporal client unavailable, falling back to non-Temporal workflow.";
//            }
//        } else {
//            return "Temporal workers unavailable, falling back to non-Temporal workflow.";
//        }
//    }

//    @GetMapping("/test")
//    public String test() {
//        Optional<WorkflowClient> workflowClientOpt = temporalBeanManager.getWorkflowClient();
//        Optional<WorkerFactory> workerFactoryOpt = temporalBeanManager.getWorkerFactory();
//
//        if (!workflowClientOpt.isPresent() || !workerFactoryOpt.isPresent()) {
//            // Fallback to non-Temporal workflow
//            return "Temporal cluster unavailable, using fallback.";
//        }
//
//        WorkflowClient workflowClient = workflowClientOpt.get();
//        WorkerFactory workerFactory = workerFactoryOpt.get();
//
//        Worker worker = workerFactory.newWorker("starter");
//        worker.registerWorkflowImplementationTypes(WorkflowImpl.class);
//        worker.registerActivitiesImplementations(new ActivityImpl());
//        workerFactory.start();
//
//        WorkflowInterface workflow = workflowClient.newWorkflowStub(WorkflowInterface.class,
//                WorkflowOptions.newBuilder()
//                        .setWorkflowId(UUID.randomUUID().toString().substring(0, 4))
//                        .setTaskQueue("starter")
//                        .build()
//        );
//        WorkflowClient.start(workflow::echoCheck);
//
//        return "Temporal workflow started successfully.";
//    }

//    private final Optional<WorkflowClient> workflowClientOptional;
//    private final Optional<WorkerFactory> workerFactoryOptional;

//    public TestController(Optional<WorkflowClient> workflowClientOptional,
//                          Optional<WorkerFactory> workerFactoryOptional) {
//        this.workflowClientOptional = workflowClientOptional;
//        this.workerFactoryOptional = workerFactoryOptional;
//
////        workerFactory.newWorker("starter");
////        workerFactory.start();
//        if (workerFactoryOptional.isPresent()) {
//            WorkerFactory workerFactory = workerFactoryOptional.get();
//            Worker worker = workerFactory.newWorker("starter");
//            worker.registerWorkflowImplementationTypes(WorkflowImpl.class);
//            worker.registerActivitiesImplementations(new ActivityImpl());
//            workerFactory.start();
//        }
//    }

    //    private final WorkerManager workerManager;

//    public TestController(WorkflowClient workflowClient,
//                          WorkerManager workerManager) {
//        this.workflowClient = workflowClient;
//        this.workerManager = workerManager;
//    }

//    @GetMapping("/test")
//    public void test() {
//        if (workflowClientOptional.isPresent()) {
//            WorkflowClient workflowClient = workflowClientOptional.get();
//
//            WorkflowInterface workflow = workflowClient.newWorkflowStub(WorkflowInterface.class,
//                    WorkflowOptions.newBuilder()
//                            .setWorkflowId(UUID.randomUUID().toString().substring(0, 4))
//                            .setTaskQueue("starter")
//                            .build()
//            );
//            WorkflowClient.execute(workflow::echoCheck);
//        } else {
//            System.out.println("fallback workflow");
//        }
//    }

}
