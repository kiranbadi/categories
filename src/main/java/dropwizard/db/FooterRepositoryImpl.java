package dropwizard.db;

import dropwizard.client.Footer;
import jakarta.inject.Inject;
import org.jdbi.v3.core.Jdbi;

public class FooterRepositoryImpl implements FooterRepository {

    private final Jdbi jdbi;
    public FooterRepositoryImpl(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public Footer findFooterById(Integer id) {
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM footer WHERE id = :id")
                .bind("id", id)
                .mapToBean(Footer.class)
                .findFirst()
                .orElse(null));
    }

    @Override
    public void insertFooter(Footer footer) {
        jdbi.useHandle(handle -> handle.createUpdate("INSERT INTO footer (footer_links, footer_link_text, footer_link_id, footer_links_ref, footer_link_purpose, footer_link_location, footer_link_status, created_by, updated_by, created_at, updated_at) VALUES (:footer_links, :footer_link_text, :footer_link_id, :footer_links_ref, :footer_link_purpose, :footer_link_location, :footer_link_status, :created_by, :updated_by, :created_at, :updated_at)")
                .bind("footer_links", footer.getFooter_links())
                .bind("footer_link_text", footer.getFooter_link_text())
                .bind("footer_link_id", footer.getFooter_link_id())
                .bind("footer_links_ref", footer.getFooter_links_ref())
                .bind("footer_link_purpose", footer.getFooter_link_purpose())
                .bind("footer_link_location", footer.getFooter_link_location())
                .bind("footer_link_status", footer.getFooter_link_status())
                .bind("created_by", footer.getCreated_by())
                .bind("updated_by", footer.getUpdated_by())
                .bind("created_at", footer.getCreated_at())
                .bind("updated_at", footer.getUpdated_at())
                .execute());

    }
}
