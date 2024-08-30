package org.example.temporalstarter;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
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

    private final Optional<WorkflowClient> workflowClientOptional;
    private final Optional<WorkerFactory> workerFactoryOptional;

    public TestController(Optional<WorkflowClient> workflowClientOptional,
                          Optional<WorkerFactory> workerFactoryOptional) {
        this.workflowClientOptional = workflowClientOptional;
        this.workerFactoryOptional = workerFactoryOptional;

//        workerFactory.newWorker("starter");
//        workerFactory.start();
        if (workerFactoryOptional.isPresent()) {
            WorkerFactory workerFactory = workerFactoryOptional.get();
            Worker worker = workerFactory.newWorker("starter");
            worker.registerWorkflowImplementationTypes(WorkflowImpl.class);
            worker.registerActivitiesImplementations(new ActivityImpl());
            workerFactory.start();
        }
    }

    //    private final WorkerManager workerManager;

//    public TestController(WorkflowClient workflowClient,
//                          WorkerManager workerManager) {
//        this.workflowClient = workflowClient;
//        this.workerManager = workerManager;
//    }

    @GetMapping("/test")
    public void test() {
        if (workflowClientOptional.isPresent()) {
            WorkflowClient workflowClient = workflowClientOptional.get();

            WorkflowInterface workflow = workflowClient.newWorkflowStub(WorkflowInterface.class,
                    WorkflowOptions.newBuilder()
                            .setWorkflowId(UUID.randomUUID().toString().substring(0, 4))
                            .setTaskQueue("starter")
                            .build()
            );
            WorkflowClient.execute(workflow::echoCheck);
        } else {
            System.out.println("fallback workflow");
        }



    }

}
