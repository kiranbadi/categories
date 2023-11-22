package dropwizard.db;

import dropwizard.client.Category;
import org.jdbi.v3.core.Jdbi;

import java.util.Objects;
import java.util.Optional;


public class CategoryRepositoryImpl implements CategoryRepository {

    private final Jdbi jdbi;

    private static final String save_category_sql = "INSERT INTO categories (category_id,category_name,category_description,category_status) VALUES (:category_id,:category_name,:category_description,:category_status)";

    private static final String exists_category_sql = "SELECT EXISTS(SELECT 1 FROM categories WHERE category_id = :category_id)";

    public CategoryRepositoryImpl(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public Category findCategoryByCategoryId(Integer id) {
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

    @Override
    public Category saveCategory(Category category) {
        return jdbi.withHandle(handle -> handle.createUpdate(save_category_sql)
                .bind("category_id", category.getCategory_id())
                .bind("category_name", category.getCategory_name())
                .bind("category_description", category.getCategory_description())
                .bind("category_status", category.getCategory_status())
                .executeAndReturnGeneratedKeys()
                .mapTo(Long.class)
                .map(id -> findCategoryById(id.intValue()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElse(null));

    }

    @Override
    public void deleteCategory(Integer category_id) {
        jdbi.withHandle(handle -> handle.createUpdate("DELETE FROM categories WHERE category_id = :category_id")
                .bind("category_id", category_id)
                .execute());
    }

    @Override
    public Optional<Category> findCategoryById(Integer id) {
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM categories WHERE id = :id")
                .bind("id", id)
                .mapToBean(Category.class)
                .findFirst());
    }

    @Override
    public Optional<Category> upsertCategory(Category category) {
        return jdbi.withHandle(handle -> handle.createQuery(exists_category_sql)
                .bind("category_id", category.getCategory_id())
                .mapTo(Boolean.class)
                .findFirst()
                .map(exists -> {
                    if (exists) {
                        handle.createUpdate("UPDATE categories SET category_name = :category_name, category_description = :category_description, category_status = :category_status WHERE category_id = :category_id")
                                .bind("category_id", category.getCategory_id())
                                .bind("category_name", category.getCategory_name())
                                .bind("category_description", category.getCategory_description())
                                .bind("category_status", category.getCategory_status())
                                .execute();
                         return handle.createQuery("SELECT id FROM categories WHERE category_id = :category_id")
                                .bind("category_id", category.getCategory_id())
                                .mapTo(Long.class)
                                .findFirst()
                                .orElse(null);
                    } else {
                        return handle.createUpdate(save_category_sql)
                                .bind("category_id", category.getCategory_id())
                                .bind("category_name", category.getCategory_name())
                                .bind("category_description", category.getCategory_description())
                                .bind("category_status", category.getCategory_status())
                                .executeAndReturnGeneratedKeys()
                                .mapTo(Long.class)
                                .stream().peek(System.out::println)
                                .findFirst()
                                .orElse(null);
                    }
                })
                .flatMap(id -> findCategoryById(id.intValue()))
                .stream().peek(System.out::println)
                .filter(Objects::nonNull)
                .findFirst());



    }

}
