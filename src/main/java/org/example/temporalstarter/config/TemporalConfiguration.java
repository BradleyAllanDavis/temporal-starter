package org.example.temporalstarter.config;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.spring.boot.TemporalOptionsCustomizer;
import io.temporal.worker.WorkerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class TemporalConfiguration {

//    @Bean
//    public TemporalClusterChecker temporalClusterChecker() {
//        // Logic to check Temporal cluster availability
//        return new TemporalClusterChecker();
//    }

    @Bean
    @DependsOn("temporalClusterChecker")
//    @ConditionalOnBean(TemporalClusterChecker.class)
    @Conditional(TemporalClusterAvailableCondition.class)
    public WorkflowServiceStubs workflowServiceStubs() {
        return WorkflowServiceStubs.newLocalServiceStubs(); // or your specific configuration
    }

    @Bean
    @DependsOn("temporalClusterChecker")
//    @ConditionalOnBean(TemporalClusterChecker.class)
    @Conditional(TemporalClusterAvailableCondition.class)
    public WorkflowClient workflowClient(WorkflowServiceStubs workflowServiceStubs) {
        return WorkflowClient.newInstance(workflowServiceStubs);
    }

    @Bean
    @DependsOn("temporalClusterChecker")
//    @ConditionalOnBean(TemporalClusterChecker.class)
    @Conditional(TemporalClusterAvailableCondition.class)
    public WorkerFactory workerFactory(WorkflowClient workflowClient) {
        return WorkerFactory.newInstance(workflowClient);
    }

    @Bean
    public TemporalOptionsCustomizer<WorkflowClient> workflowClientOptions() {
        // Customize workflow client options if needed
        return (options) -> options;
    }

    @Bean
    public TemporalOptionsCustomizer<WorkerFactory> workerFactoryOptions() {
        // Customize worker factory options if needed
        return (options) -> options;
    }
}
