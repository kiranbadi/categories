package dropwizard.service;

import dropwizard.client.Footer;
import dropwizard.db.FooterRepository;
import jakarta.inject.Inject;

public class FooterService {

    private final FooterRepository footerRepository;

    @Inject
    public FooterService(FooterRepository footerRepository) {
        this.footerRepository = footerRepository;
    }

    public Footer findFooterById(Integer id) {
        return this.footerRepository.findFooterById(id);
    }

    public void insertFooter(Footer footer) {
        this.footerRepository.insertFooter(footer);
    }
}
