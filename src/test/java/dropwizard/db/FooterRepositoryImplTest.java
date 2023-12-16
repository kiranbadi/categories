package dropwizard.db;

import dropwizard.client.Footer;
import dropwizard.utils.TestUtils;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.HandleCallback;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.result.ResultIterable;
import org.jdbi.v3.core.statement.Query;
import org.jdbi.v3.core.statement.Update;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class FooterRepositoryImplTest {

    private FooterRepository footerRepository;

    AutoCloseable closeable;

    @Mock
    private Jdbi jdbi;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        footerRepository = new FooterRepositoryImpl(jdbi);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    @SuppressWarnings({"unchecked", "rawtypes"})
    void findFooterById() {
        Footer footer = TestUtils.footer();
        when(jdbi.withHandle(any())).thenAnswer((Answer) invocation -> {
            HandleCallback callback = invocation.getArgument(0);
            Handle handle = mock(Handle.class);
            Query query = mock(Query.class);
            ResultIterable resultIterable = mock(ResultIterable.class);
            when(handle.createQuery(anyString())).thenReturn(query);
            when(query.bind(anyString(), (Integer) any())).thenReturn(query);
            when(resultIterable.findFirst()).thenReturn(Optional.of(footer));
            when(query.mapToBean(any())).thenReturn(resultIterable);
            when(resultIterable.findFirst()).thenReturn(Optional.of(footer));
            return callback.withHandle(handle);
        });

        Footer footer1 = footerRepository.findFooterById(1);
        Assertions.assertEquals(footer, footer1);

    }

    @Test
    @SuppressWarnings({"rawtypes"})
    void insertFooter() {
        Footer footer = TestUtils.footer();
        when(jdbi.withHandle(any())).thenAnswer((Answer) invocation -> {
            HandleCallback callback = invocation.getArgument(0);
            Handle handle = mock(Handle.class);
            Update update = mock(Update.class);
            when(handle.createUpdate(anyString())).thenReturn(update);
            when(update.bind(anyString(), (Integer) any())).thenReturn(update);
            when(update.bind(anyString(), (Integer) any())).thenReturn(update);
            when(update.bind(anyString(), (Integer) any())).thenReturn(update);
            when(update.bind(anyString(), (Integer) any())).thenReturn(update);
            when(update.bind(anyString(), (Integer) any())).thenReturn(update);
            when(update.bind(anyString(), (Integer) any())).thenReturn(update);
            when(update.bind(anyString(), (Integer) any())).thenReturn(update);
            when(update.bind(anyString(), (Integer) any())).thenReturn(update);
            when(update.bind(anyString(), (Integer) any())).thenReturn(update);
            when(update.bind(anyString(), (Integer) any())).thenReturn(update);
            when(update.bind(anyString(), (Integer) any())).thenReturn(update);
            when(update.execute()).thenReturn(1);
            return callback.withHandle(handle);
        });
        footerRepository.insertFooter(footer);
        verify(jdbi, times(1)).useHandle(any());

    }
}