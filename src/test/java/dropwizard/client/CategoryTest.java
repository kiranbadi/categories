package dropwizard.client;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;

class CategoryTest {


    private Category categoryUnderTest;
    @BeforeEach
    void setUp() {
        categoryUnderTest = new Category();
        categoryUnderTest.setId(1L);
        categoryUnderTest.setCategory_id(1L);
        categoryUnderTest.setCategory_name("test_category_name");
        categoryUnderTest.setCategory_description("test_category_description");
        categoryUnderTest.setCategory_status("test_category_status");
        categoryUnderTest.setCreated_at("2021-01-01 00:00:00");
        categoryUnderTest.setUpdated_at("2021-01-01 00:00:00");
        categoryUnderTest.setCreated_by("system_user");
        categoryUnderTest.setUpdated_by("system_user");

    }

    /* Tests for constructor with all parameter arguments*/
    @Test
    void testCategoryConstructor_withAllParameters() {
        Category category = new Category(1L, 1L, "test_category_name", "test_category_description", "test_category_status", "2021-01-01 00:00:00", "2021-01-01 00:00:00", "system_user", "system_user");
        Assertions.assertEquals(category, categoryUnderTest);
    }

    @AfterEach
    void tearDown() {
        categoryUnderTest = null;
    }

    @Test
    void testCategoryBuilder() {
        Category category = Category.builder()
                .id(1L)
                .category_id(1L)
                .category_name("test_category_name")
                .category_description("test_category_description")
                .category_status("test_category_status")
                .created_at("2021-01-01 00:00:00")
                .updated_at("2021-01-01 00:00:00")
                .created_by("system_user")
                .updated_by("system_user")
                .build();
        Assertions.assertEquals(category, categoryUnderTest);
    }

    @Test
    public void testCategoryBuilder_toString() {
        Category category = Category.builder()
                .id(1L)
                .category_id(1L)
                .category_name("test_category_name")
                .category_description("test_category_description")
                .category_status("test_category_status")
                .created_at("2021-01-01 00:00:00")
                .updated_at("2021-01-01 00:00:00")
                .created_by("system_user")
                .updated_by("system_user")
                .build();
        Assertions.assertEquals(category.toString(), categoryUnderTest.toString());
    }

    @Test
    void testToString() {
        final String result = categoryUnderTest.toString();
        assert(result.contains("id=1"));
        assert(result.contains("category_id=1"));
        assert(result.contains("category_name=test_category_name"));
        assert(result.contains("category_description=test_category_description"));
        assert(result.contains("category_status=test_category_status"));
        assert(result.contains("created_at="));
        assert(result.contains("updated_at="));
        assert(result.contains("created_by=system_user"));
        assert(result.contains("updated_by=system_user"));
    }


    // using meanbean tests
    @Test
    public void testCategory()
    {
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(Category.class);
    }

    @Test
    public void hashCodeTest() {
        HashCodeMethodTester tester = new HashCodeMethodTester();
        tester.testHashCodeMethod(Category.class);
    }

    @Test
    public void testEquals() {
        EqualsMethodTester tester = new EqualsMethodTester();
        tester.testEqualsMethod(Category.class);
    }



}