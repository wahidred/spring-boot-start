package business.config;

import business.drh.dao._Dao;
import business.drh.service._Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import twork.DebugBpp;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * The @EnableTransactionManagement annotation is equivalent to <tx:annotation-driven/> annotation.
 * they are responsible for registering the necessary Spring components that power annotation-driven
 * transaction management, such as the TransactionInterceptor ... when @Transactional methods are
 * invoked.
 */

@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@PropertySource("classpath:/sgbd.properties") // propertySource still WORKS with Boot
// @ConfigurationProperties("classpath:/sgbd.properties")
@ComponentScan(basePackageClasses = {_Service.class, _Dao.class})
public class AppConfig {

    @Autowired
    Environment env;

    @Autowired
    EntityManagerFactory emf;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    DebugBpp debugBpp() {
        return new DebugBpp();
    }

    /**
     * this bean uses the property : spring.bean.package.to.scan = business.dao, business.service
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        String packageToScan = env.getProperty("jpa.model.package.to.scan");
        logger.debug("key = \"jpa.model.package.to.scan\" = " + packageToScan);
        em.setPackagesToScan(new String[]{packageToScan});
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }

    /**
     * this bean uses the properties : hbm2ddl.auto=create jpa.model.package.to.scan =
     * business.model
     */
    Properties additionalProperties() {
        Properties properties = new Properties();
        String hbmauto = env.getProperty("hbm2ddl.auto");
        String dialect = env.getProperty("db.hibernate.dialect");
        logger.debug("key = \"hbm2ddl.auto\" = " + hbmauto);
        logger.debug("key = \"db.hibernate.dialect\" = " + dialect);
        properties.setProperty("hibernate.hbm2ddl.auto", hbmauto);
        properties.setProperty("hibernate.dialect", dialect);
        String meta = env.getProperty("spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults");
        properties.setProperty("hibernate.temp.use_jdbc_metadata_defaults", meta);
        // spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults=false
        return properties;
    }

    /**
     * this bean uses the properties :
     * <p>
     * <pre>
     * db.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
     * db.url=jdbc:postgresql://localhost:5432/minibase db.driver=org.postgresql.Driver
     * db.user=minibase db.password=minibase
     *
     * <pre>
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        String driver = env.getProperty("db.driver");
        String url = env.getProperty("db.url");
        String user = env.getProperty("db.user");
        String pwd = env.getProperty("db.password");
        logger.debug("key = \"db.driver\" = " + driver);
        logger.debug("key = \"db.url\" = " + url);
        logger.debug("key = \"db.user\" = " + user);
        logger.debug("key = \"db.pwd\" = " + pwd);
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(pwd);
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

}
