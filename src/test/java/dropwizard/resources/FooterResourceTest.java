package dropwizard.resources;

import dropwizard.client.Footer;
import dropwizard.service.FooterService;
import dropwizard.utils.TestUtils;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FooterResourceTest {

    @InjectMocks
    FooterResource footerResource;

    AutoCloseable closeable;

    @Mock
    FooterService footerService;

    @Mock
    Response response;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        footerResource = new FooterResource(footerService);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void getFooterById() {
        // TODO : Add test for getFooterById
    }

    @Test
    void createFooter() {
        // TODO : Add test for createFooter
    }
}