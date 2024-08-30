package org.example.temporalstarter;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.UUID;

@SpringBootApplication(scanBasePackages = "org.example.temporalstarter")
//@ComponentScan(basePackages = {
//        "org.example.temporalstarter",
//})
public class TemporalStarterApplication {

    public static void main(String[] args) {
        System.out.println("main before");
        SpringApplication.run(TemporalStarterApplication.class, args);
        System.out.println("main after");
    }

}
