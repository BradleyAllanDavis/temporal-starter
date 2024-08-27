package org.example.temporalstarter;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Service
public class TestService {

    @Autowired
    private WorkflowClient workflowClient;

    @PostConstruct
    public void test() {
        System.out.println("service before");
        WorkflowInterface workflow = workflowClient.newWorkflowStub(
                WorkflowInterface.class,
                WorkflowOptions.newBuilder()
                        .setWorkflowId("workflowId" + "-" + UUID.randomUUID().toString().substring(0, 6))
                        .setTaskQueue("echo-string")
                        .build()
        );
        workflow.echoCheck();
        System.out.println("service after");
    }

}
