package com.paradigmkick.sample.querydsl;

import static com.paradigmkick.sample.querydsl.QCustomer.customer;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;

import com.mysema.query.jpa.JPQLQueryFactory;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = QuerydslSampleApplication.class)
public class CustomerRepositoryTest extends TestCase {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private JPQLQueryFactory queryFactory;

  @Test
  public void simpleTest() {
    // given
    customerRepository.save(new Customer("Luke", "Skywalker", new Date(2014, 0, 1)));
    customerRepository.save(new Customer("Anakin", "Skywalker", new Date(2014, 0, 2)));

    // expect
    assertThat(
        queryFactory.from(customer).where(customer.lastName.eq("Skywalker")).list(customer),
        contains(
            hasProperty("firstName", equalTo("Luke")),
            hasProperty("firstName", equalTo("Anakin"))
        )
    );
  }
}