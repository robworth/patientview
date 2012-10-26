package com.worthsoln.test.repository;

import com.worthsoln.test.TestPvDbSchema;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *  All repository tests should extend.
 *
 *  Sets up everything required for hibernate, persistence.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml", "classpath:test-context.xml"})
@Transactional
public abstract class BaseDaoTest extends TestPvDbSchema {
}
