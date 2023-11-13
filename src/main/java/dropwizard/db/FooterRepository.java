package dropwizard.db;

import dropwizard.client.Footer;
import org.jdbi.v3.sqlobject.customizer.Bind;

public interface FooterRepository {

    Footer findFooterById(@Bind("id") Integer id);
    void insertFooter(@Bind Footer footer);
}
