package dropwizard.client;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jdbi.v3.core.mapper.reflect.JdbiConstructor;

import java.math.BigInteger;


@Getter
@Setter
@NoArgsConstructor
public class BatchJobInstance {

    private BigInteger jobInstanceId;

    private BigInteger version;

    private String jobName;

    private String jobKey;


    @JdbiConstructor
    public BatchJobInstance(BigInteger jobInstanceId, BigInteger version, String jobName, String jobKey) {
        this.jobInstanceId = jobInstanceId;
        this.version = version;
        this.jobName = jobName;
        this.jobKey = jobKey;
    }
}
