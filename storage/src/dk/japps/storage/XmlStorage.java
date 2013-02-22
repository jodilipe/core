package dk.japps.storage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XmlStorage implements Storage {
	private Map<String, Persistable> persistableObjects = new HashMap<String, Persistable>();

	@Override
	public void store(Persistable persistable) {
		getPersistableObjects().put(persistable.getId(), persistable);
		try {
			FileWriter fileWriter = new FileWriter(new File("storage.xml"));
			new XStream(new DomDriver()).toXML(getPersistableObjects(), fileWriter);
			fileWriter.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Persistable find(String id) {
		return null;
	}

	@Override
	public void delete(Persistable persistable) {
	}
	
	@SuppressWarnings("unchecked")
	private Map<String, Persistable> getPersistableObjects() {
		if (persistableObjects.isEmpty()) {
			try {
				File file = new File("storage.xml");
				if (file.exists() && file.length() > 0) {
					FileReader fileReader = new FileReader(file);
					persistableObjects = (Map<String, Persistable>) new XStream(new DomDriver()).fromXML(fileReader);
					fileReader.close();
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return persistableObjects;
	}
	
	public class Person implements Persistable {
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String getId() {
			return "123";
		}
	}
	
	public static void main(String[] args) {
		Person person = new XmlStorage().new Person();
		person.name = "Goofie";
		new XmlStorage().store(person);
	}
}
