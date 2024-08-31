package org.example.temporalstarter;

import java.util.List;

public class WorkerConfig {
    private final List<Class<?>> workflowImplementationTypes;
    private final List<Object> activityImplementations;

    public WorkerConfig(List<Class<?>> workflowImplementationTypes, List<Object> activityImplementations) {
        this.workflowImplementationTypes = workflowImplementationTypes;
        this.activityImplementations = activityImplementations;
    }

    public List<Class<?>> getWorkflowImplementationTypes() {
        return workflowImplementationTypes;
    }

    public List<Object> getActivityImplementations() {
        return activityImplementations;
    }
}