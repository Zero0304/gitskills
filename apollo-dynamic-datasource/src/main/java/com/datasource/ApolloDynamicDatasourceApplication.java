package com.datasource;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableEurekaClient
@EnableApolloConfig
@MapperScan("com.datasource.dao")
public class ApolloDynamicDatasourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApolloDynamicDatasourceApplication.class, args);
	}
}
