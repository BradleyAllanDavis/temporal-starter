package org.example.temporalstarter;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
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




        // Autoconfigure
        WorkflowInterface workflow = workflowClient.newWorkflowStub(
                WorkflowInterface.class,
                WorkflowOptions.newBuilder()
                        .setWorkflowId("workflowId" + "-" + UUID.randomUUID().toString().substring(0, 6))
                        .setTaskQueue("echo-string")
                        .build()
        );
        workflow.echoCheck();




        // Old way
//        WorkflowServiceStubs service = WorkflowServiceStubs.newServiceStubs(WorkflowServiceStubsOptions.newBuilder()
////                .setTarget("temporal-frontend.dev-com.svc.dev.local")
//                .setTarget("temporal-frontend.dev-com.svc.dev.local:7233")
//                .build());
//
//        // Workflow client can be used to start, signal, query, cancel, and terminate workflows.
//        WorkflowClient client = WorkflowClient.newInstance(service);
//
//        // Worker factory is used to create Workers that poll specific Task Queues.
//        WorkerFactory factory = WorkerFactory.newInstance(client);
//
//        // Worker that listens on the task queue and hosts both workflow and activity implementations.
//        Worker worker = factory.newWorker("echo-string");
//
//        // Register the workflow implementation with the worker.
//        worker.registerWorkflowImplementationTypes(WorkflowImpl.class);
//
//        // Register the activity implementation with the worker.
//        worker.registerActivitiesImplementations(new ActivityImpl());
//
//        // Start all the workers created by this factory.
//        factory.start();




        System.out.println("service after");
    }

}
