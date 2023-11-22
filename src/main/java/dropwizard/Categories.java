package dropwizard;

import dropwizard.db.CategoryRepository;
import dropwizard.db.CategoryRepositoryImpl;
import dropwizard.db.FooterRepository;
import dropwizard.db.FooterRepositoryImpl;
import dropwizard.resources.CategoryResource;
import dropwizard.resources.FooterResource;
import dropwizard.service.CategoryService;
import dropwizard.service.FooterService;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi3.JdbiFactory;
import org.jdbi.v3.core.Jdbi;

public class Categories extends Application<CategoriesConfiguration> {


    public static void main(final String[] args) throws Exception {
        new Categories().run(args);
    }

    @Override
    public String getName() {
        return "Categories";
    }

    @Override
    public void initialize(final Bootstrap<CategoriesConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider
                (new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(), new EnvironmentVariableSubstitutor()));
    }

    @Override
    public void run(final CategoriesConfiguration configuration,
                    final Environment environment) {
        final JdbiFactory factory = new JdbiFactory() ;
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
        final CategoryRepository categoryRepository = new CategoryRepositoryImpl(jdbi);
        final CategoryService categoryService = new CategoryService(categoryRepository);
        final CategoryResource categoryResource = new CategoryResource(categoryService);
        environment.jersey().register(categoryResource);
        final FooterRepository footerRepository = new FooterRepositoryImpl(jdbi);
        final FooterService footerService = new FooterService(footerRepository);
        final FooterResource footerResource = new FooterResource(footerService);
        environment.jersey().register(footerResource);
    }
}
