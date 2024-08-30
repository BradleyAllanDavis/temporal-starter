package org.example.temporalstarter.config;

import io.grpc.StatusRuntimeException;
import io.temporal.api.workflowservice.v1.GetSystemInfoRequest;
import io.temporal.api.workflowservice.v1.GetSystemInfoResponse;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
//@Service
public class TemporalClusterChecker {

    private final boolean temporalClusterAvailable;

    public TemporalClusterChecker() {
        System.out.println("clust check");
        this.temporalClusterAvailable = checkTemporalCluster();
    }

    public boolean isTemporalClusterAvailable() {
        return temporalClusterAvailable;
    }

    private boolean checkTemporalCluster() {
        WorkflowServiceStubsOptions options = WorkflowServiceStubsOptions.newBuilder()
                .setTarget("localhost:7233") // Specify your Temporal cluster URL
                .build();

        try {
            WorkflowServiceStubs service = WorkflowServiceStubs.newServiceStubs(options);
            GetSystemInfoResponse systemInfo = service.blockingStub().getSystemInfo(GetSystemInfoRequest.newBuilder().build());
            return true;
        } catch (StatusRuntimeException e) {
            return false;
        }
    }

//    public boolean isTemporalClusterAvailable() {
//        WorkflowServiceStubsOptions options = WorkflowServiceStubsOptions.newBuilder()
//                .setTarget("localhost:7233") // Specify your Temporal cluster URL
//                .build();
//
//        try {
//            WorkflowServiceStubs service = WorkflowServiceStubs.newServiceStubs(options);
//            GetSystemInfoResponse systemInfo = service.blockingStub().getSystemInfo(GetSystemInfoRequest.newBuilder()
//                    .build());
//            //            service.blockingStub().getSystemInfo();
//            return true;
//        } catch (StatusRuntimeException e) {
//            return false;
//        }
//    }
}