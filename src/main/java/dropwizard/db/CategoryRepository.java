package dropwizard.db;

import com.google.inject.Singleton;
import dropwizard.client.Category;
import org.jdbi.v3.sqlobject.customizer.Bind;

import javax.swing.text.html.Option;
import java.util.Optional;


public interface CategoryRepository {

    Category findCategoryByCategoryId(@Bind("id") Integer id);

    Category findCategoryByNameOrId(@Bind("name") String name, @Bind("id") Integer id);

    Category saveCategory(Category category);

    void deleteCategory(@Bind("id") Integer category_id);

    Optional<Category> findCategoryById(@Bind("id") Integer id);

    Optional<Category> upsertCategory(Category category);

}
