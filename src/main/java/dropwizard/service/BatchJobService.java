package dropwizard.service;

import dropwizard.client.BatchJobInstance;
import dropwizard.db.BatchJobInstanceRepository;

import java.util.List;
import java.util.Map;

public class BatchJobService {

    private final BatchJobInstanceRepository batchJobInstanceRepository;

    public BatchJobService(BatchJobInstanceRepository batchJobInstanceRepository) {
        this.batchJobInstanceRepository = batchJobInstanceRepository;
    }

    public List<BatchJobInstance> findAllBatchJobInstances(Map<String,String> map) {
        return this.batchJobInstanceRepository.findAllBatchJobInstances(map);
    }
}
