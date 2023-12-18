package dropwizard.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jdbi.v3.core.mapper.reflect.JdbiConstructor;

import java.math.BigInteger;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor //(onConstructor = @__(@JdbiConstructor))
public class BatchJobInstance {

    private BigInteger jobInstanceId;

    private BigInteger version;

    private String jobName;

    private String jobKey;

}
