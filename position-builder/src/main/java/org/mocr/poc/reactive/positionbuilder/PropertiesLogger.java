package org.mocr.poc.reactive.positionbuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.PropertySource;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PropertiesLogger implements ApplicationListener<ApplicationPreparedEvent> {
  private static final Logger log = LoggerFactory.getLogger(PropertiesLogger.class);

  private ConfigurableEnvironment environment;

  @Override
  public void onApplicationEvent(ApplicationPreparedEvent event) {
    environment = event.getApplicationContext().getEnvironment();
    printProperties();
  }

  public void printProperties() {
    for (EnumerablePropertySource propertySource : findPropertiesPropertySources()) {
      log.info("********** " + propertySource.getName() + " **********");
      String[] propertyNames = propertySource.getPropertyNames();
      Arrays.sort(propertyNames);
      for (String propertyName : propertyNames) {
        log.info("{}={}", propertyName, environment.getProperty(propertyName));
      }
    }
  }

  private List<EnumerablePropertySource> findPropertiesPropertySources() {
    List<EnumerablePropertySource> propertiesPropertySources = new LinkedList<>();
    for (PropertySource<?> propertySource : environment.getPropertySources()) {
      if (propertySource instanceof EnumerablePropertySource) {
        propertiesPropertySources.add((EnumerablePropertySource) propertySource);
      }
    }
    return propertiesPropertySources;
  }
}