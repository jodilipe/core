package dk.japps.storage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XmlStorage implements Storage {
	private static final String STORAGE_XML = "storage.xml";
	private Map<String, Persistable> persistableObjects = new HashMap<String, Persistable>();

	@Override
	public synchronized void store(Persistable persistable) {
		getPersistableObjects().put(persistable.getId(), persistable);
	}

	public synchronized void rollBack() {
		loadPersistableObjects();
	}
	
	public synchronized void commit() {
		try {
			FileWriter fileWriter = new FileWriter(new File(STORAGE_XML));
			new XStream(new DomDriver()).toXML(getPersistableObjects(), fileWriter);
			fileWriter.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public synchronized Persistable find(String id) {
		return getPersistableObjects().get(id);
	}

	@Override
	public synchronized void delete(Persistable persistable) {
	}
	

	@Override
	public synchronized Collection<Persistable> findAll(Class<? extends Persistable> type) {
		return null;
	}

	private Map<String, Persistable> getPersistableObjects() {
		if (persistableObjects.isEmpty()) {
			loadPersistableObjects();
		}
		return persistableObjects;
	}

	@SuppressWarnings("unchecked")
	protected void loadPersistableObjects() {
		try {
			File file = new File(STORAGE_XML);
			if (file.exists() && file.length() > 0) {
				FileReader fileReader = new FileReader(file);
				persistableObjects = (Map<String, Persistable>) new XStream(new DomDriver()).fromXML(fileReader);
				fileReader.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
