package org.example.temporalstarter;

import io.temporal.workflow.WorkflowMethod;

@io.temporal.workflow.WorkflowInterface
public interface WorkflowInterface {

    @WorkflowMethod
    void echoCheck();

}
