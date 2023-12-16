package dropwizard.db;

import dropwizard.client.Category;
import dropwizard.utils.TestUtils;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.HandleCallback;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.result.ResultBearing;
import org.jdbi.v3.core.result.ResultIterable;
import org.jdbi.v3.core.statement.Query;
import org.jdbi.v3.core.statement.Update;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CategoryRepositoryImplTest {

   private CategoryRepository categoryRepository;

   private AutoCloseable closeable;

   @Mock
   private Jdbi jdbi;


    @BeforeEach
    public void openMocks() {
       closeable = MockitoAnnotations.openMocks(this);
       categoryRepository = new CategoryRepositoryImpl(jdbi);
    }


    @AfterEach
    public void releaseMocks() throws Exception {
        closeable.close();
    }

    @Test
    @SuppressWarnings({"unchecked", "rawtypes"})
    void findCategoryByCategoryId() {
        Category category = category();
        when(jdbi.withHandle(any())).thenAnswer((Answer) invocation -> {
            HandleCallback callback = invocation.getArgument(0);
            Handle handle = mock(Handle.class);
            Query query = mock(Query.class);
            ResultIterable resultIterable = mock(ResultIterable.class);
            when(handle.createQuery(anyString())).thenReturn(query);
            when(query.bind(anyString(), (Integer) any())).thenReturn(query);
            when(query.mapToBean(Category.class)).thenReturn(resultIterable);
            when(resultIterable.findFirst()).thenReturn(Optional.of(category));
            return callback.withHandle(handle);
        });
        Category category1 = categoryRepository.findCategoryByCategoryId(1);
        assertEquals(category, category1);

    }

    @Test
    @SuppressWarnings({"unchecked", "rawtypes"})
    void findCategoryByNameOrId() {
        Category category = category();
        when(jdbi.withHandle(any())).thenAnswer((Answer) invocation -> {
            HandleCallback callback = invocation.getArgument(0);
            Handle handle = mock(Handle.class);
            Query query = mock(Query.class);
            ResultIterable resultIterable = mock(ResultIterable.class);
            when(handle.createQuery(anyString())).thenReturn(query);
            when(query.bind(anyString(), anyString())).thenReturn(query);
            when(query.bind(anyString(), (Integer) any())).thenReturn(query);
            when(query.mapToBean(Category.class)).thenReturn(resultIterable);
            when(resultIterable.findFirst()).thenReturn(Optional.of(category));
            return callback.withHandle(handle);
        });
        Category category1 = categoryRepository.findCategoryByNameOrId("Test", 1);
        assertEquals(category, category1);
    }

    @Test
    @SuppressWarnings({"rawtypes", "unchecked"})
    void saveCategory() {
        Category category = TestUtils.category();
        when(jdbi.withHandle(any())).thenAnswer((Answer) invocation -> {
            HandleCallback callback = invocation.getArgument(0);
            Handle handle = mock(Handle.class);
            Update update = mock(Update.class);
            ResultBearing resultBearing = mock(ResultBearing.class);
            ResultIterable resultIterable = mock(ResultIterable.class);
            when(handle.createUpdate(anyString())).thenReturn(update);
            when(update.bind(anyString(), any(Long.class))).thenReturn(update);
            when(update.bind(anyString(), anyString())).thenReturn(update);
            when(update.bind(anyString(), anyString())).thenReturn(update);
            when(update.bind(anyString(), anyString())).thenReturn(update);
            when(update.executeAndReturnGeneratedKeys()).thenReturn(resultBearing);
            when(resultBearing.mapTo(Long.class)).thenReturn(resultIterable);
            when(resultIterable.map(any())).thenReturn(resultIterable);
            when(resultIterable.filter(any())).thenReturn(resultIterable);
            when(resultIterable.map(any())).thenReturn(resultIterable);
            when(resultIterable.findFirst()).thenReturn(Optional.of(category));
            return callback.withHandle(handle);
        });
        Category category1 = categoryRepository.saveCategory(category);
        verify(jdbi, times(1)).withHandle(any());
        Assertions.assertEquals(category, category1);
    }

    @Test
    @SuppressWarnings({"rawtypes"})
    void deleteCategory() {
        when(jdbi.withHandle(any())).thenAnswer((Answer) invocation -> {
            HandleCallback callback = invocation.getArgument(0);
            Handle handle = mock(Handle.class);
            Update update = mock(Update.class);
            when(handle.createUpdate(anyString())).thenReturn(update);
            when(update.bind(anyString(), (Integer) any())).thenReturn(update);
            when(update.execute()).thenReturn(1);
            return callback.withHandle(handle);
        });
        categoryRepository.deleteCategory(1);
        verify(jdbi, times(1)).withHandle(any());
    }

    @Test
    @SuppressWarnings({"unchecked", "rawtypes"})
    void findCategoryById() {
        Category category = category();
        when(jdbi.withHandle(any())).thenAnswer((Answer) invocation -> {
            HandleCallback callback = invocation.getArgument(0);
            Handle handle = mock(Handle.class);
            Query query = mock(Query.class);
            ResultIterable resultIterable = mock(ResultIterable.class);
            when(handle.createQuery(anyString())).thenReturn(query);
            when(query.bind(anyString(), (Integer) any())).thenReturn(query);
            when(query.mapToBean(Category.class)).thenReturn(resultIterable);
            when(resultIterable.findFirst()).thenReturn(Optional.of(category));
            return callback.withHandle(handle);
        });
       Optional<Category> category1 = categoryRepository
                .findCategoryById(1);
        assertEquals(category, category1.stream().reduce((a, b) -> b).orElse(null));
    }

    @Test
    void upsertCategory() {
        // TODO: Implement
    }

    private Category category() {
        Category category = new Category();
        category.setCategory_id(1L);
        category.setCategory_name("Test");
        category.setCategory_description("Test");
        category.setCategory_status("Test");
        category.setCreated_by("Test");
        category.setUpdated_by("Test");
        category.setCreated_at("Test");
        category.setUpdated_at("Test");
        return category;
    }
}