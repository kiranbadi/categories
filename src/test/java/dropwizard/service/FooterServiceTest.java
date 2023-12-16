package dropwizard.service;

import dropwizard.client.Footer;
import dropwizard.db.CategoryRepository;
import dropwizard.db.FooterRepository;
import dropwizard.utils.TestUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FooterServiceTest {

    @Mock
    private FooterRepository footerRepository;

    AutoCloseable closeable;

    @InjectMocks
    private FooterService footerService;

    @BeforeEach
    void setUp() {
        closeable = org.mockito.MockitoAnnotations.openMocks(this);
        footerService = new FooterService(footerRepository);
    }

    @AfterEach
    void tearDown() {
        footerService = null;
        closeable = null;
    }

    @Test
    void findFooterById() {
        Footer footer = TestUtils.footer();
        Integer footerId = 1;
        when(footerRepository.findFooterById(footerId)).thenReturn(footer);
        Footer footer1 = footerService.findFooterById(footerId);
        assertEquals(footer, footer1);
    }

    @Test
    void insertFooter() {
        Footer footer = TestUtils.footer();
        footerService.insertFooter(footer);
        verify(footerRepository).insertFooter(footer);

    }
}