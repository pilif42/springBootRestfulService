package com.example.springboot.service;

import com.example.springboot.config.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

/**
 * Example of an integration test.
 *
 * Note that the class name ends up in ITCase. This is important for the class to be picked up automatically by Maven or
 * Gradle as an integration test to be run.
 *
 * In Maven, you should add the following to your pom:
 * <plugin>
 *   <groupId>org.apache.maven.plugins</groupId>
 *   <artifactId>maven-failsafe-plugin</artifactId>
 *   <version>${maventest.version}</version>
 *   <executions>
 *     <execution>
 *       <id>integration-test</id>
 *       <goals>
 *         <goal>integration-test</goal>
 *       </goals>
 *     </execution>
 *     <execution>
 *        <id>verify</id>
 *        <goals>
 *           <goal>verify</goal>
 *        </goals>
 *     </execution>
 *   </executions>
 *  </plugin>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class, initializers = ConfigFileApplicationContextInitializer.class)
public class ElasticSearchServiceImplITCase {

  @Autowired
  private ElasticSearchService elasticSearchService;

  /**
   * Run in debug and verify that fields (elasticsearchClusterName, etc.) are populated correctly in
   * elasticSearchService
   */
  @Test
  public void skeletonTest() {
    assertNotNull(elasticSearchService);
    assertTrue(true);
  }
}
