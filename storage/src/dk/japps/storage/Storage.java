package dk.japps.storage;

public interface Storage {
	void store(Persistable persistable);
	Persistable find(String id);
	void delete(Persistable persistable);
}
