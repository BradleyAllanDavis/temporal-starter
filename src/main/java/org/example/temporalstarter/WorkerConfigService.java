package org.example.temporalstarter;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class WorkerConfigService {

    private final Map<String, WorkerConfig> workerConfigs = new HashMap<>();

    public WorkerConfigService() {
        workerConfigs.put(TaskQueue.STARTER, new WorkerConfig(
                Collections.singletonList(WorkflowImpl.class),
                Collections.singletonList(new ActivityImpl())
        ));
    }

    public Map<String, WorkerConfig> getWorkerConfigs() {
        return workerConfigs;
    }

    public WorkerConfig getWorkerConfig(String taskQueue) {
        return workerConfigs.get(taskQueue);
    }

    public boolean containsTaskQueue(String taskQueue) {
        return workerConfigs.containsKey(taskQueue);
    }
}