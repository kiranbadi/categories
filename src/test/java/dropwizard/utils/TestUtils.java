package dropwizard.utils;

import dropwizard.client.Category;
import dropwizard.client.Footer;

public class TestUtils {

    public static Category category() {
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

    public static Footer footer() {
        Footer footer = new Footer();
        footer.setFooter_links("Test");
        footer.setFooter_link_text("Test");
        footer.setFooter_link_id("Test");
        footer.setFooter_links_ref("Test");
        footer.setFooter_link_purpose("Test");
        footer.setFooter_link_location("Test");
        footer.setFooter_link_status("Test");
        footer.setCreated_by("Test");
        footer.setUpdated_by("Test");
        footer.setCreated_at("Test");
        footer.setUpdated_at("Test");
        return footer;
    }
}
