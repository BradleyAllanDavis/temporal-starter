package org.example.temporalstarter;

import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.UUID;

//@Service
public class TestService {

//    @Lazy
//    @Autowired
//    private WorkflowClient workflowClient;

//    @Autowired
//    private WorkerFactory workerFactory;

//    @EventListener(ApplicationStartedEvent.class)
    public void test() {
        System.out.println("service before");

//        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
//        WorkflowClient client = WorkflowClient.newInstance(service);
//        WorkerFactory factory = WorkerFactory.newInstance(client);
//        Worker yourWorker = factory.newWorker("echo-string");
//        // Register Workflow
//        // and/or register Activities
//        factory.start();

//        Worker worker = workerFactory.newWorker("echo-string");
//        worker.registerWorkflowImplementationTypes(WorkflowImpl.class);
//        worker.start()???

//        workerFactory.start();

//        Worker worker = workerFactory.newWorker("echo-string");
////        worker.registerActivitiesImplementations(ActivityImpl.class);
//        worker.registerWorkflowImplementationFactory(WorkflowImpl.class, workerFactory, WorkerFactory.newInstance(workflowClient));

//        // Autoconfigure
//        WorkflowInterface workflow = workflowClient.newWorkflowStub(
//                WorkflowInterface.class,
//                WorkflowOptions.newBuilder()
//                        .setWorkflowId("starter" + "-" + UUID.randomUUID().toString().substring(0, 6))
//                        .setTaskQueue("starter")
//                        .build()
//        );
//
//        // DOES NOT WORK
//        workflow.echoCheck();

        // WORKS
//        WorkflowClient.execute(workflow::echoCheck);
//        WorkflowExecution execution = WorkflowClient.start(workflow::echoCheck);

        // Old way
//        WorkflowServiceStubs service = WorkflowServiceStubs.newServiceStubs(WorkflowServiceStubsOptions.newBuilder()
//                .setTarget("temporal-frontend.dev-com.svc.dev.local")
////                .setTarget("temporal-frontend.dev-com.svc.dev.local:7233")
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
//
//
//        WorkflowInterface workflow = client.newWorkflowStub(
//                WorkflowInterface.class,
//                WorkflowOptions.newBuilder()
//                        .setWorkflowId("workflowId" + "-" + UUID.randomUUID().toString().substring(0, 6))
//                        .setTaskQueue("echo-string")
//                        .build()
//        );
////        workflow.echoCheck();
//        WorkflowClient.execute(workflow::echoCheck);



        System.out.println("service after");
    }

}
