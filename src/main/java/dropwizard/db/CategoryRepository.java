package dropwizard.db;

import com.google.inject.Singleton;
import dropwizard.client.Category;
import org.jdbi.v3.sqlobject.customizer.Bind;


public interface CategoryRepository {

    Category findCategoryById(@Bind("id") Integer id);

    Category findCategoryByNameOrId(@Bind("name") String name, @Bind("id") Integer id);

}
