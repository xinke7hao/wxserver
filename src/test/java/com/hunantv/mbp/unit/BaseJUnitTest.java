package com.hunantv.mbp.unit;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

@ContextConfiguration(locations = { "/spring-test.xml" })
@TransactionConfiguration(transactionManager="transactionTestManager", defaultRollback=false)
public class BaseJUnitTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Test
	public void testInit(){
		assertTrue(true);
	}
}
