package dropwizard.resources;

import dropwizard.client.BatchJobInstance;
import dropwizard.client.BatchRequest;
import dropwizard.service.BatchJobService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.NonNull;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Path("/batch")
@Produces(MediaType.APPLICATION_JSON)
public class BatchJobResource {

    private final BatchJobService batchJobService;

    public BatchJobResource(BatchJobService batchJobService) {
        this.batchJobService = batchJobService;
    }

    @POST
    @Path("/details")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getBatchJobInstanceById(
             BatchRequest batchRequest) {
        Objects.requireNonNull(batchRequest.getJobName());
        Map<String,String> map = Map.of("jobName", batchRequest.getJobName(), "jobKey",
                batchRequest.getJobKey() == null ? "" : batchRequest.getJobKey());
        List<BatchJobInstance> batchJobInstance = batchJobService.findAllBatchJobInstances(map);
        if (batchJobInstance != null) {
            return Response.ok(batchJobInstance).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
