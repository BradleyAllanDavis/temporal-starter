package org.example.temporalstarter;

import io.temporal.worker.WorkerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

//@Lazy
//@Component
public class WorkerManager {

    private final WorkerFactory workerFactory;

    public WorkerManager(WorkerFactory workerFactory) {
        this.workerFactory = workerFactory;
    }

    public void startWorkers() {
        workerFactory.start();
    }


}
