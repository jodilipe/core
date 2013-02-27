package dk.japps.storage;

import java.util.Collection;

public interface Storage {
	void store(Persistable persistable);
	Persistable find(String id);
	Collection<Persistable> findAll(Class<? extends Persistable> type);
	void delete(Persistable persistable);
}
