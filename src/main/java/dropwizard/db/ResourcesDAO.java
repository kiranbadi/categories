package dropwizard.db;

import dropwizard.client.Category;
import dropwizard.client.Footer;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@RegisterBeanMapper(Category.class)
@RegisterBeanMapper(Footer.class)
public interface ResourcesDAO {

    @SqlQuery("SELECT * FROM categories WHERE category_id = :id")
    Category findCategoryById(@Bind("id") Integer id);

    @SqlUpdate("INSERT INTO categories (category_id, category_name, category_description, category_status, created_at, updated_at, created_by, updated_by) VALUES (:id, :name, :description, :status, :created_at, :updated_at, :created_by, :updated_by)")
    void insert(@Bind("id") int id, @Bind("name") String name, @Bind("description") String description, @Bind("status") String status, @Bind("created_at") String created_at, @Bind("updated_at") String updated_at, @Bind("created_by") String created_by, @Bind("updated_by") String updated_by);

    @SqlQuery("SELECT * FROM footer WHERE id = :id")
    Footer findFooterById(@Bind("id") Integer id);

    @SqlUpdate("INSERT INTO footer (id, footer_links, footer_link_text, footer_link_id, footer_links_ref, footer_link_purpose, " +
            "footer_link_location, footer_link_status, created_at, updated_at, created_by, updated_by) VALUES (:id, :footer_links, :footer_link_text, :footer_link_id, :footer_links_ref, :footer_link_purpose, :footer_link_location, :footer_link_status, :created_at, :updated_at, :created_by, :updated_by)")
    void insertFooter(@Bind Footer footer);

}

