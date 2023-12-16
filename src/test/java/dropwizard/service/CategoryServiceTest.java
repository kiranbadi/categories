package dropwizard.service;

import dropwizard.client.Category;
import dropwizard.db.CategoryRepository;
import dropwizard.utils.TestUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;



class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    AutoCloseable closeable;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
          closeable = MockitoAnnotations.openMocks(this);
          categoryService = new CategoryService(categoryRepository);
    }

    @AfterEach
    void tearDown() {
       categoryService = null;
       closeable = null;
    }

    @Test
    void findCategoryById() {
        Category category = TestUtils.category();
        Integer categoryId = 1;
        when(categoryRepository.findCategoryByCategoryId(categoryId)).thenReturn(category);
        Category category1 = categoryService.findCategoryById(categoryId);
        Assertions.assertEquals(category, category1);
    }

    @Test
    void findCategoryByNameOrId() {
        Category category = TestUtils.category();
        Integer categoryId = 1;
        String categoryName = "test";
        when(categoryRepository.findCategoryByNameOrId(categoryName,categoryId)).thenReturn(category);
        Category category1 = categoryService.findCategoryByNameOrId(categoryName,categoryId);
        Assertions.assertEquals(category, category1);
    }

    @Test
    void saveCategory() {
        Category category = TestUtils.category();
        when(categoryRepository.saveCategory(category)).thenReturn(category);
        Category category1 = categoryService.saveCategory(category);
        Assertions.assertEquals(category, category1);
    }

    @Test
    void upsertCategory() {
        Category category = TestUtils.category();
        when(categoryRepository.upsertCategory(category)).thenReturn(Optional.of(category));
        Optional<Category> category1 = categoryService.upsertCategory(category);
        Assertions.assertEquals(Optional.of(category), category1);
    }

    @Test
    void deleteCategory() {
        Integer categoryId = 1;
        doNothing().when(categoryRepository).deleteCategory(categoryId);
        categoryService.deleteCategory(categoryId);
        verify(categoryRepository, times(1)).deleteCategory(categoryId);
    }
}