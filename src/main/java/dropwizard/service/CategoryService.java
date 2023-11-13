package dropwizard.service;

import dropwizard.client.Category;
import dropwizard.db.CategoryRepository;


public class CategoryService {


    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category findCategoryById(Integer id) {
        return categoryRepository.findCategoryById(id);
    }

    public Category findCategoryByNameOrId(String name, Integer id) {
        return categoryRepository.findCategoryByNameOrId(name, id);
    }

}
