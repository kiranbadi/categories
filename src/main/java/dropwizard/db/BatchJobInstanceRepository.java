package dropwizard.db;

import dropwizard.client.BatchJobInstance;

import java.util.List;
import java.util.Map;

public interface BatchJobInstanceRepository {

    List<BatchJobInstance> findAllBatchJobInstances(Map<String,String> map);
}
