package com.chaojidaogou.taskcenter.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * Created by Johnson on 2014/10/16.
 */
@Configuration
@ComponentScan(basePackages = {"com.chaojidaogou.taskcenter"}, includeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = {"com.chaojidaogou.taskcenter.web.*"}))
@EnableJpaRepositories("com.chaojidaogou.taskcenter.repository")
@EnableScheduling
@EnableAspectJAutoProxy
@EnableAsync
@PropertySource("classpath:/config.properties")
public class AppConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppConfig.class);
    @Inject
    Environment environment;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(jpaDataSource());
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setDatabasePlatform(environment.getProperty("jdbc.dialect"));
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        return factoryBean;
    }

    @Bean
    public DataSource jpaDataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
        dataSource.setUser(environment.getProperty("jdbc.username"));
        dataSource.setPassword(environment.getProperty("jdbc.password"));
        try {
            dataSource.setDriverClass(environment.getProperty("jdbc.driverClassName"));
        } catch (PropertyVetoException e) {
            LOGGER.error("Can not create Data source.");
        }
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(this.entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        connectionFactory.setHostName(environment.getProperty("redis.host"));
        connectionFactory.setPort(environment.getProperty("redis.port", Integer.class));
        connectionFactory.setUsePool(true);
        return connectionFactory;
    }

    @Bean
    public DataSource externalDataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setJdbcUrl(environment.getProperty("external.db.jdbc.url"));
        dataSource.setUser(environment.getProperty("external.db.jdbc.username"));
        dataSource.setPassword(environment.getProperty("external.db.jdbc.password"));
        try {
            dataSource.setDriverClass(environment.getProperty("jdbc.driverClassName"));
        } catch (PropertyVetoException e) {
            LOGGER.error("Can not create Data source.");
        }
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(externalDataSource());
    }

    @Bean(name = "stringRedisTemplate")
    public StringRedisTemplate getStringRedisTemplate() {
        return new StringRedisTemplate(redisConnectionFactory());
    }

    @Bean(name = "redisTemplate")
    public <V> RedisTemplate<String, V> getRedisTemplate() {
        RedisTemplate<String, V> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
