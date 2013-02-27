package dk.japps.test.storage;

import junit.framework.Assert;

import org.junit.Test;

import dk.japps.storage.Guid;

public class GuidTest {

	@Test
	public void testGetId() {
		String id1 = new Guid().getId();
		String id2 = new Guid().getId();
		Assert.assertNotNull(id1);
		Assert.assertNotNull(id2);
		Assert.assertNotSame(id1, id2);
	}

}
