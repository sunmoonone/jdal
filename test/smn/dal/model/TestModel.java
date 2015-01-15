package smn.dal.model;

import org.junit.Test;

import junit.framework.TestCase;

public class TestModel extends TestCase {

	@Test
	public void TestGetTableName() {
		Member m = new Member();
		assertTrue("member".equals(m.__getTable()));
	}
}
