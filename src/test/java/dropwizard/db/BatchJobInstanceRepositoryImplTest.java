package dropwizard.db;

import dropwizard.client.BatchJobInstance;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.HandleCallback;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.ConstructorMapper;
import org.jdbi.v3.core.result.ResultBearing;
import org.jdbi.v3.core.result.ResultIterable;
import org.jdbi.v3.core.statement.Query;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class BatchJobInstanceRepositoryImplTest {

    private BatchJobInstanceRepository batchJobInstanceRepository;

    @Mock
    private Jdbi jdbi;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        batchJobInstanceRepository = new BatchJobInstanceRepositoryImpl(jdbi);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    @SuppressWarnings({"unchecked", "rawtypes" })
    void findAllBatchJobInstances() {

        List<BatchJobInstance> list = List.of(new BatchJobInstance(BigInteger.ONE, BigInteger.ONE, "test", "test"));

        when(jdbi.withHandle(any())).thenAnswer((Answer) invocation -> {
            HandleCallback callback = invocation.getArgument(0);
            Handle handle = mock(Handle.class);
            Query query = mock(Query.class);
            ResultIterable resultIterable = mock(ResultIterable.class);
            ResultBearing resultBearing = mock(ResultBearing.class);
            when(handle.createQuery(anyString())).thenReturn(query);
            when(query.bind(anyString(),anyString())).thenReturn(query);
            when(query.bind(anyString(),anyString())).thenReturn(query);
            when(query.registerRowMapper(ConstructorMapper.factory(BatchJobInstance.class)))
                    .thenReturn(query);
            when(query.mapTo(BatchJobInstance.class)).thenReturn(resultIterable);
            when(resultIterable.list()).thenReturn(list);
            return callback.withHandle(handle);
        });

        List<BatchJobInstance> result = batchJobInstanceRepository.findAllBatchJobInstances(Map.of("jobName", "test", "jobKey", "test"));
        assertNotNull(result);
        assertEquals(list, result);

        }
    }