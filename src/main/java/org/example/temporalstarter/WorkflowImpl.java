package org.example.temporalstarter;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;

@io.temporal.spring.boot.WorkflowImpl(
        taskQueues = "echo-string"
)
//@AllArgsConstructor
public class WorkflowImpl implements WorkflowInterface {

    @Override
    public void echoCheck() {
        System.out.println("WorkflowImpl echoCheck");

        ActivityInterface activity = Workflow.newActivityStub(ActivityInterface.class,
                ActivityOptions.newBuilder()
                        .setStartToCloseTimeout(Duration.ofSeconds(5))
                        .build());

        activity.echo();
    }

}
