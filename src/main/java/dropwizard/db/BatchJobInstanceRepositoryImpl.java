package dropwizard.db;

import dropwizard.client.BatchJobInstance;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.ConstructorMapper;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public class BatchJobInstanceRepositoryImpl implements BatchJobInstanceRepository {

    private final Jdbi jdbi;

    public BatchJobInstanceRepositoryImpl(Jdbi jdbi) {
        this.jdbi = jdbi;
    }
    /**
     * @param map
     * @return
     */
    @Override
    public List<BatchJobInstance> findAllBatchJobInstances(Map<String, String> map) {
        StringBuilder find_all_batch_sql = new StringBuilder("SELECT JOB_INSTANCE_ID AS jobInstanceId," +
                "VERSION as version, JOB_NAME AS jobName , " +
                " JOB_KEY AS jobKey FROM BATCH_JOB_INSTANCE WHERE JOB_NAME= :jobName");
        String jobKey = map.get("jobKey");
        String jobName = map.get("jobName");
        if(jobKey != null && !jobKey.isEmpty()) {
            find_all_batch_sql.append(" AND JOB_KEY= :jobKey");
        }
        return jdbi.withHandle(handle -> handle.createQuery(find_all_batch_sql.toString())
                .bind("jobKey", jobKey)
                .bind("jobName", jobName)
                .registerRowMapper(ConstructorMapper.factory(BatchJobInstance.class))
                .mapTo(BatchJobInstance.class)
                .list());

    }
}
