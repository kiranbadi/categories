package dropwizard.db;

import dropwizard.client.Category;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.result.ResultIterator;
import org.jdbi.v3.core.statement.Query;
import org.jdbi.v3.core.statement.Update;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CategoryRepositoryImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private Jdbi jdbi;

    @Mock
    private Handle handle;

    @Mock
    private Update update;

    @Mock
    private Query query;


    @BeforeEach
    public void setUp() {
        categoryRepository = new CategoryRepositoryImpl(jdbi);
        when(jdbi.open()).thenReturn(handle);
        when(handle.createUpdate(anyString())).thenReturn(update);
        when(update.bind(anyString(), Optional.ofNullable(any()))).thenReturn(update);
        when(update.execute()).thenReturn(1);
        };


    @Test
    void findCategoryByCategoryId() {
        int categoryId = 1;
        Category expectedCategory = new Category();
        ResultIterator<?> resultIterator = mock(ResultIterator.class);
        when(handle.createQuery(anyString())).thenReturn(query);
        when(query.bind("id",categoryId).thenReturn(query);
        when(query.mapToBean(Category.class)).thenReturn(expectedCategory);
        when(query.findFirst()).thenReturn(Optional.of(expectedCategory));
        when(query.iterator()).thenReturn(resultIterator);
        when(resultIterator.hasNext()).thenReturn(true, false);
        when(resultIterator.next()).thenReturn(expectedCategory);
        ResultIterator<Category> resultIterator = Mockito.mock(ResultIterator.class);

        when(handle.createQuery(anyString())).thenReturn(query);
        when(query.bind("id", categoryId)).thenReturn(query);
        when(query.mapToBean(Category.class)).thenReturn(query);
        when(query.findFirst()).thenReturn(Optional.of(expectedCategory));
        when(query.iterator()).thenReturn(resultIterator);
        when(resultIterator.hasNext()).thenReturn(true, false);
        when(resultIterator.next()).thenReturn(expectedCategory);

        // Act
        Category result = yourRepository.findCategoryByCategoryId(categoryId);

        // Assert
        assertNotNull(result);
        assertEquals(expectedCategory, result);

        // Verify that the methods were called with the expected arguments and in the correct order
        verify(handle).createQuery("SELECT * FROM categories WHERE category_id = :id");
        verify(query).bind("id", categoryId);
        verify(query).mapToBean(Category.class);
        verify(query).findFirst();
        verify(resultIterator, times(2)).hasNext();
        verify(resultIterator).next();
    }

    @Test
    void findCategoryByNameOrId() {
        String categoryName = "name";
        int categoryId = 1;
        Category category = new Category(1L,1L, "name", "description", "status","created_at","updated_at","created_by","updated_by");
        when(categoryRepository.findCategoryByNameOrId(categoryName,categoryId))
                .thenReturn(category);
        Category result = categoryRepository.findCategoryByNameOrId(categoryName,categoryId);
        assertEquals(category, result);
    }

    @Test
    void saveCategory() {
        Category category = new Category(1L,1L, "name", "description", "status","created_at","updated_at","created_by","updated_by");
        when(categoryRepository.saveCategory(category))
                .thenReturn(category);
        Category result = categoryRepository.saveCategory(category);
        assertEquals(category, result);
    }

    @Test
    void deleteCategory() {
        Integer categoryIdToDelete = 123;
        when(handle.createUpdate(anyString())).thenReturn(update);

        // Your repository method
        categoryRepository.deleteCategory(categoryIdToDelete);

        // Act
        verify(handle).createUpdate("DELETE FROM categories WHERE category_id = :category_id");
        verify(update).bind("category_id", categoryIdToDelete);
        verify(update).execute();
    }

    @Test
    void findCategoryById() {
        int categoryId = 1;
        Category category = new Category(1L,1L, "name", "description", "status","created_at","updated_at","created_by","updated_by");
        when(categoryRepository.findCategoryById(categoryId))
                .thenReturn(Optional.of(category));
        Optional<Category> result = categoryRepository.findCategoryById(categoryId);
        assertEquals(Optional.of(category), result);
    }

    @Test
    void upsertCategory() {
        Category category = new Category(1L,1L, "name", "description", "status","created_at","updated_at","created_by","updated_by");
        when(categoryRepository.upsertCategory(category))
                .thenReturn(Optional.of(category));
        Optional<Category> result = categoryRepository.upsertCategory(category);
        assertEquals(Optional.of(category), result);
    }
}