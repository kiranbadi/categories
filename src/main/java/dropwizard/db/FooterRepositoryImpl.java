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

    }
}
