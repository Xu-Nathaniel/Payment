package com.wechat.payment.config;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
@MapperScan(basePackages = "com.wechat.payment.mapper")
public class DateSourceConfig {

  @Value("${spring.datasource.url}")
  private String url;

  @Value("${spring.datasource.username}")
  private String username;

  @Value("${spring.datasource.password}")
  private String password;

  @Value("${spring.datasource.driver-class-name}")
  private String driverClassName;

  @Bean(name = "dataSource")
  @Primary
  public DataSource dataSource() {
    HikariDataSource dataSource = DataSourceBuilder
        .create(getClass().getClassLoader())
        .driverClassName(driverClassName)
        .url(url)
        .username(username)
        .password(password)
        .type(HikariDataSource.class)
        .build();
    dataSource.setPoolName("wechatPaymentDataSource");
    return dataSource;
  }

  @Bean(name = "transactionManager")
  @Primary
  public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }
}
