package com.worthsoln.test.service;

import com.worthsoln.test.BaseTestPvDbSchema;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
public abstract class BaseServiceTest extends BaseTestPvDbSchema {

}
