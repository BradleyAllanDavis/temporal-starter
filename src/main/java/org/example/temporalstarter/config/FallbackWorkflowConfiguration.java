package org.example.temporalstarter.config;

import io.temporal.client.WorkflowClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FallbackWorkflowConfiguration {

    @Bean
    @ConditionalOnMissingBean(WorkflowClient.class)
//    public FallbackWorkflow fallbackWorkflow() {
    public void fallbackWorkflow() {
        System.out.println("fallbackWorkflow");
//        return new FallbackWorkflow();
    }
}
