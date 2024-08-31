package org.example.temporalstarter;

import org.springframework.stereotype.Component;

//@Component
@io.temporal.spring.boot.ActivityImpl(
        taskQueues = TaskQueue.STARTER
)
public class ActivityImpl implements ActivityInterface {

    @Override
    public void echo() {
        System.out.println("ActivityImpl echo");
    }

}
