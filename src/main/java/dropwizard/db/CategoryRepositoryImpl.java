package dropwizard.db;

import com.google.inject.Singleton;
import dropwizard.client.Category;
import jakarta.inject.Inject;
import org.jdbi.v3.core.Jdbi;






public class CategoryRepositoryImpl implements CategoryRepository {

    private final Jdbi jdbi;


    public CategoryRepositoryImpl(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public Category findCategoryById(Integer id) {
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM categories WHERE category_id = :id")
                 .bind("id", id)
                 .mapToBean(Category.class)
                 .findFirst()
                 .orElse(null));
    }

    @Override
    public Category findCategoryByNameOrId(String name, Integer id) {
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM categories WHERE category_name = :name OR category_id = :id")
                 .bind("name", name)
                 .bind("id", id)
                 .mapToBean(Category.class)
                 .findFirst()
                 .orElse(null));
    }

}
