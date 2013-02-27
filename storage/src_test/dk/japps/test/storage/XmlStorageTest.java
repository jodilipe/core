package dk.japps.test.storage;

import static org.junit.Assert.fail;
import junit.framework.Assert;

import org.junit.Test;

import dk.japps.storage.Guid;
import dk.japps.storage.Persistable;
import dk.japps.storage.XmlStorage;

public class XmlStorageTest {

	@Test
	public void testStore() {
		A persistable = new A();
		XmlStorage xmlStorage = new XmlStorage();
		xmlStorage.store(persistable);
		A foundPersistable = (A) xmlStorage.find(persistable.getId());
		Assert.assertNotNull(foundPersistable);
		Assert.assertEquals(persistable.getId(), foundPersistable.getId());
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testFind() {
		fail("Not yet implemented");
	}

	static class A implements Persistable {
		private Guid id = new Guid();
		@Override
		public String getId() {
			return id.getId(); 
		}
	}
}
