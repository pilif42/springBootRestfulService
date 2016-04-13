package com.example.springboot.config;

import com.example.springboot.service.ElasticSearchService;
import com.example.springboot.service.impl.ElasticSearchServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource(value= {"classpath:application.properties"})
public class TestConfig {
  /**
   * This is required so ${...} in @Value are interpreted correctly.
   * @return
   */
  @Bean
  public static PropertySourcesPlaceholderConfigurer propertyConfigInTest() {
    return new PropertySourcesPlaceholderConfigurer();
  }

  @Bean
  public ElasticSearchService elasticSearchService() {
    return new ElasticSearchServiceImpl();
  }
}
