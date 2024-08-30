package org.example.temporalstarter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NonTemporalWorkflowConfiguration {

    @Bean
//    public NonTemporalWorkflow nonTemporalWorkflow() {
    public void nonTemporalWorkflow() {
        System.out.println("nonTemporalWorkflow");

//        return new NonTemporalWorkflow();
    }
}
