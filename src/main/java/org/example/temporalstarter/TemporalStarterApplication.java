package org.example.temporalstarter;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.spring.boot.autoconfigure.RootNamespaceAutoConfiguration;
import io.temporal.spring.boot.autoconfigure.ServiceStubsAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.UUID;

@SpringBootApplication(scanBasePackages = "org.example.temporalstarter"
        ,exclude = {
        ServiceStubsAutoConfiguration.class,
        RootNamespaceAutoConfiguration.class
}
)
@EnableScheduling
public class TemporalStarterApplication {

    public static void main(String[] args) {
        System.out.println("main before");
        SpringApplication.run(TemporalStarterApplication.class, args);
        System.out.println("main after");
    }

}
