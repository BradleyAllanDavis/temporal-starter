package org.example.temporalstarter.config;

import io.grpc.StatusRuntimeException;
import io.temporal.api.workflowservice.v1.GetSystemInfoRequest;
import io.temporal.api.workflowservice.v1.GetSystemInfoResponse;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import io.temporal.spring.boot.autoconfigure.properties.TemporalProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.function.Consumer;

//@Component
@Service
public class TemporalClusterAvailabilityService {


    private final TemporalProperties temporalProperties;
    private boolean temporalClusterAvailable;
    private ClusterAvailabilityChangeHandler clusterAvailabilityChangeHandler;

    @Autowired
    public TemporalClusterAvailabilityService(TemporalProperties temporalProperties) {
        this.temporalProperties = temporalProperties;
        this.temporalClusterAvailable = checkTemporalCluster();
    }

    @Scheduled(fixedDelayString = "${temporal.cluster.check.interval:1000}") // Check every 10 seconds by default
    private void refreshClusterStatus() {
        boolean currentStatus = checkTemporalCluster();
        if (currentStatus != temporalClusterAvailable) {
            temporalClusterAvailable = currentStatus;
            System.out.println("cluster changed status: " + temporalClusterAvailable);
            // Notify listeners about the change
            if (clusterAvailabilityChangeHandler != null) {
                clusterAvailabilityChangeHandler.onClusterAvailabilityChange(currentStatus);
            }
        }
    }

    public boolean isTemporalClusterAvailable() {
        return temporalClusterAvailable;
    }

    public void setOnClusterAvailabilityChange(ClusterAvailabilityChangeHandler handler) {
        this.clusterAvailabilityChangeHandler = handler;
    }

    private boolean checkTemporalCluster() {
        WorkflowServiceStubsOptions options = WorkflowServiceStubsOptions.newBuilder()
                .setTarget(temporalProperties.getConnection().getTarget())
                .setRpcTimeout(Duration.ofSeconds(3)) // Set a reasonable timeout
                .setConnectionBackoffResetFrequency(Duration.ofSeconds(1))
                .build();

        try {
            WorkflowServiceStubs service = WorkflowServiceStubs.newServiceStubs(options);
            GetSystemInfoResponse systemInfo = service.blockingStub().getSystemInfo(GetSystemInfoRequest.newBuilder().build());
//            temporalClusterAvailable = true;
//            if (clusterAvailabilityChangeHandler != null) {
//                clusterAvailabilityChangeHandler.onClusterAvailabilityChange(true);
//            }
            return true;
        } catch (StatusRuntimeException e) {
//            temporalClusterAvailable = false;
//            if (clusterAvailabilityChangeHandler != null) {
//                clusterAvailabilityChangeHandler.onClusterAvailabilityChange(false);
//            }
            return false;
        }
    }

    public interface ClusterAvailabilityChangeHandler {
        void onClusterAvailabilityChange(boolean isAvailable);
    }
}