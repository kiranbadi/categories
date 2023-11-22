package dropwizard.service;

import dropwizard.client.Category;
import dropwizard.db.CategoryRepository;

import java.util.Optional;


public class CategoryService {


    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category findCategoryById(Integer id) {
        return categoryRepository.findCategoryByCategoryId(id);
    }

    public Category findCategoryByNameOrId(String name, Integer id) {
        return categoryRepository.findCategoryByNameOrId(name, id);
    }

    public Category saveCategory(Category category) {
        return categoryRepository.saveCategory(category);
    }

    public Optional<Category> upsertCategory(Category category) {
        return categoryRepository.upsertCategory(category);
    }

    public void deleteCategory(Integer category_id) {
        categoryRepository.deleteCategory(category_id);
    }

}
