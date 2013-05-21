package com.worthsoln.test.service;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *  All service tests should extend.
 *
 *  Sets up everything required for hibernate, persistence.
 *
 *  NOTE: these tests are not transaction driven, the transactions are create new in the service layer,
 *  hence the need to manually roll back the database in between each test.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml", "classpath:test-context.xml"})
@Transactional
public abstract class BaseServiceTest {

}
