package com.paradigmkick.sample.querydsl;

import com.mysema.query.jpa.JPQLQueryFactory;
import com.mysema.query.jpa.impl.JPAProvider;
import com.mysema.query.jpa.impl.JPAQueryFactory;

import javax.inject.Provider;
import javax.persistence.EntityManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class QuerydslSampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(QuerydslSampleApplication.class, args);
  }

  @Bean
  public JPQLQueryFactory queryFactoryForQueryDsl(Provider<EntityManager> entityManager) {
    return new JPAQueryFactory(JPAProvider.getTemplates(entityManager.get()), entityManager);
  }
}
