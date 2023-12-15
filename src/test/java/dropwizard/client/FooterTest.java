package dropwizard.client;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;

import static org.junit.jupiter.api.Assertions.*;

class FooterTest {

    private  Footer footerUnderTest;

    @BeforeEach
    void setUp() {
        footerUnderTest = new Footer();
    }

    @AfterEach
    void tearDown() {
        footerUnderTest = null;
    }

    @Test
    public void testFooter(){
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(Footer.class);
    }

    @Test
    public void test_toString() {
        String expected = "Footer(id=null, footer_links=null, footer_link_text=null, footer_link_id=null, footer_links_ref=null, footer_link_purpose=null, footer_link_location=null, footer_link_status=null, created_at=null, updated_at=null, created_by=null, updated_by=null)";
        String actual = footerUnderTest.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void hashCodeTest() {
        HashCodeMethodTester tester = new HashCodeMethodTester();
        tester.testHashCodeMethod(Footer.class);
    }

    @Test
    public void testEquals() {
        EqualsMethodTester tester = new EqualsMethodTester();
        tester.testEqualsMethod(Footer.class);
    }
}