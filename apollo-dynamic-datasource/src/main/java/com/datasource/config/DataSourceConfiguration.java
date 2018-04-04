package com.datasource.config;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.jdbc.metadata.HikariDataSourcePoolMetadata;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.Set;

/**
 * Created by ranai on 2018/4/2 0002.
 */
@Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
public class DataSourceConfiguration {

    private static final Logger log = LoggerFactory.getLogger(DataSourceConfiguration.class);

    private final static String DATASOURCE_TAG = "db";

    @Autowired
    ApplicationContext context;

    @ApolloConfig
    Config config;

    @Bean("datasource")
    public DynamicDataSource dynamicDataSource(){
        DynamicDataSource source = new DynamicDataSource();
        source.setTargetDataSources(Collections.singletonMap(DATASOURCE_TAG,dataSource()));
        return source;
    }

    @ApolloConfigChangeListener
    public void onChange(ConfigChangeEvent changeEvent){
        Set<String> changeKeys = changeEvent.changedKeys();
        if (changeKeys.contains("spring.datasource.url")){
            DynamicDataSource source = context.getBean(DynamicDataSource.class);
            source.setTargetDataSources(Collections.singletonMap(DATASOURCE_TAG,dataSource()));
            source.afterPropertiesSet();
            log.info("动态数据源切换为：{}",config.getProperty("spring.datasource.url",""));
        }
    }


    public DataSource dataSource(){

        /*
        //Hikari数据源
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(config.getProperty("spring.datasource.url",""));
        dataSource.setUsername(config.getProperty("spring.datasource.username",""));
        dataSource.setPassword(config.getProperty("spring.datasource.password",""));
         */
        //tomcat jdbc数据源
        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setUrl(config.getProperty("spring.datasource.url",""));
        dataSource.setUsername(config.getProperty("spring.datasource.username",""));
        dataSource.setPassword(config.getProperty("spring.datasource.password",""));
        return dataSource;

    }

    class DynamicDataSource extends AbstractRoutingDataSource{
        @Override
        protected Object determineCurrentLookupKey() {
            return DATASOURCE_TAG;
        }
    }
}
