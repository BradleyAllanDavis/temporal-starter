package org.example.temporalstarter.config;

import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import io.temporal.spring.boot.autoconfigure.properties.ConnectionProperties;
import io.temporal.spring.boot.autoconfigure.properties.TemporalProperties;
import io.temporal.spring.boot.autoconfigure.properties.TestServerProperties;
import io.temporal.spring.boot.autoconfigure.properties.WorkerProperties;
import io.temporal.spring.boot.autoconfigure.properties.WorkersAutoDiscoveryProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

@Configuration
@EnableConfigurationProperties(TemporalProperties.class)
public class ExtendedTemporalProperties {

//    @Bean
//    public TemporalProperties temporalProperties(TemporalProperties temporalProperties) {
//        return temporalProperties;
//    }

//    @Bean
//    public WorkflowServiceStubs workflowServiceStubs(TemporalProperties temporalProperties, TemporalClusterAvailabilityService availabilityService) {
//        if (availabilityService.isTemporalClusterAvailable()) {
//            return WorkflowServiceStubs.newServiceStubs(
//                    WorkflowServiceStubsOptions.newBuilder()
//                            .setTarget(temporalProperties.getConnection().getTarget())
//                            .build()
//            );
//        }
//        return null; // Return null or throw an exception if the service is not available
//    }

}
