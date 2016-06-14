package modal.dbservice;

import modal.entity.Fish;
import modal.entity.Fisher;
import modal.entity.Lake;
import modal.entity.Lure;
import modal.entity.User;
import modal.entity.joinentity.Availability;
import modal.entity.joinentity.Distance;
import modal.entity.joinentity.Lived;
import modal.entity.joinentity.Peck;
import modal.entity.joinentity.Prefers;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class DBService {
    private static final String HIBERNATE_SHOW_SQL = "true";
    private static final String HIBERNATE_HBM2DDL_AUTO = "update";
    private static final String HIBERNATE_DIALECT = "org.hibernate.dialect.PostgreSQLDialect";
    private static final String HIBERNATE_CONNECTION_DRIVER = "org.postgresql.Driver";
    private static final String CONNECTION_URL = "jdbc:postgresql://localhost:5432/fishclub";
    private static final String DATABASE_USERNAME = "postgres";
    private static final String DATABASE_PASSWORD = "masterkey";
    private final SessionFactory sessionFactory;
    private static DBService service;

    private DBService() {
        Configuration configuration = this.getPostgreSqlConfiguration();
        this.sessionFactory = createSessionFactory(configuration);
    }

    public static synchronized DBService instance() {
        if (service == null) {
            service = new DBService();
        }
        return service;
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    private Configuration getPostgreSqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Availability.class);
        configuration.addAnnotatedClass(Distance.class);
        configuration.addAnnotatedClass(Fish.class);
        configuration.addAnnotatedClass(Fisher.class);
        configuration.addAnnotatedClass(Lake.class);
        configuration.addAnnotatedClass(Lure.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Peck.class);
        configuration.addAnnotatedClass(Prefers.class);
        configuration.addAnnotatedClass(Lived.class);
        configuration.setProperty("hibernate.dialect", HIBERNATE_DIALECT);
        configuration.setProperty("hibernate.connection.driver_class", HIBERNATE_CONNECTION_DRIVER);
        configuration.setProperty("hibernate.connection.url", CONNECTION_URL);
        configuration.setProperty("hibernate.connection.username", DATABASE_USERNAME);
        configuration.setProperty("hibernate.connection.password", DATABASE_PASSWORD);
        configuration.setProperty("hibernate.show_sql", HIBERNATE_SHOW_SQL);
        configuration.setProperty("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);
        return configuration;
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        StandardServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public void closeSessionFactory() {
        this.sessionFactory.close();
    }
}
