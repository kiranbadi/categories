package dropwizard;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CategoriesConfigurationTest {

    private ObjectMapper objectMapper;
    private Validator validator;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.findAndRegisterModules();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterEach
    void tearDown() {
        objectMapper = null;
        validator = null;
    }

    @Test
    public void testConfigurationParsing() throws IOException {
        CategoriesConfiguration configuration = objectMapper.readValue(new File("src/test/resources/test-config.yml"), CategoriesConfiguration.class);
        Set<ConstraintViolation<CategoriesConfiguration>> violations = validator.validate(configuration);
        assertEquals(0, violations.size());
        assertNotNull(configuration.getDataSourceFactory());
        }
    }
