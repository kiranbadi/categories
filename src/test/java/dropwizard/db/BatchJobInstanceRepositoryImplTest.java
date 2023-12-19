package dropwizard.db;

import dropwizard.client.BatchJobInstance;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.HandleCallback;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMapperFactory;
import org.jdbi.v3.core.mapper.reflect.ConstructorMapper;
import org.jdbi.v3.core.result.ResultIterable;
import org.jdbi.v3.core.statement.Query;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.stubbing.Answer;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
        jdbi.registerRowMapper(ConstructorMapper.factory(BatchJobInstance.class));
        batchJobInstanceRepository = new BatchJobInstanceRepositoryImpl(jdbi);


    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    @SuppressWarnings({"unchecked", "rawtypes" })
    @MockitoSettings(strictness = org.mockito.quality.Strictness.LENIENT)
    void findAllBatchJobInstances() {
        List<BatchJobInstance> list =
                List.of(new BatchJobInstance(BigInteger.ONE, BigInteger.ONE,
                        "test", "test"));
        when(jdbi.withHandle(any())).thenAnswer((Answer) invocation -> {
            HandleCallback callback = invocation.getArgument(0);
            Handle handle = mock(Handle.class);
            Query query = mock(Query.class);
            ResultIterable resultIterable = mock(ResultIterable.class);
            when(handle.createQuery(anyString())).thenReturn(query);
            when(query.bind(anyString(),(String) any())).thenReturn(query);
            when(query.bind(anyString(),(String) any())).thenReturn(query);
            when(query.registerRowMapper((RowMapperFactory) any())).thenReturn(query);
            when(query.mapTo(any(Class.class))).thenReturn(resultIterable);
            when(resultIterable.list()).thenReturn(list);
            return callback.withHandle(handle);
        });
        List<BatchJobInstance> result = batchJobInstanceRepository.
                findAllBatchJobInstances
                        (Map.of("jobName", "test", "jobKey", "test"));
        assertNotNull(result);
        assertEquals(list, result);

        }
    }
