
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;
import dao.AddressDAO;
import dao.PersonDAO;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import pojo.Person;
import proxies.AddressAuthenticator;
import proxies.PersonAuthenticator;
import ui.HelloWorldUI;
import utilities.HelloWorldResource;
import utilities.TemplateHealthCheck;
import utilities.VaadinBundle;

public class HelloWorldApplication extends Application<HelloWorldConfiguration>{

    private static PersonAuthenticator personAuthenticator;
    private static AddressAuthenticator addressAuthenticator;
    private final HibernateBundle<HelloWorldConfiguration> hibernate = new HibernateBundle<HelloWorldConfiguration>(Person.class){
        @Override
        public DataSourceFactory getDataSourceFactory(HelloWorldConfiguration configuration){
            return configuration.getDataSourceFactory();
        }
    };

    @VaadinServletConfiguration(productionMode = false, ui = HelloWorldUI.class)
    public static class Servlet extends VaadinServlet {
        //empty
    }

    public static void main(String[] args) throws Exception{
        new HelloWorldApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap){
        bootstrap.addBundle(new VaadinBundle(Servlet.class, "/vaadin/*"));
    }

    @Override
    public void run(HelloWorldConfiguration configuration, Environment environment){
        final HelloWorldResource resource = new HelloWorldResource(configuration.getTemplate(), configuration.getDefaultName());
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        final PersonDAO personDAO = new PersonDAO(hibernate.getSessionFactory());
        final AddressDAO addressDAO = new AddressDAO(hibernate.getSessionFactory());

        personAuthenticator = new UnitOfWorkAwareProxyFactory(hibernate).create(PersonAuthenticator.class, PersonDAO.class, personDAO);
        addressAuthenticator = new UnitOfWorkAwareProxyFactory(hibernate).create(AddressAuthenticator.class, AddressDAO.class, addressDAO);

        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }

    @Override
    public String getName(){
        return "hello-world";
    }

    public static PersonAuthenticator getPersonAuthenticator() {
        return personAuthenticator;
    }

    public static AddressAuthenticator getAddressAuthenticator() {
        return addressAuthenticator;
    }
}
