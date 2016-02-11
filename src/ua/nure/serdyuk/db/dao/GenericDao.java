package ua.nure.serdyuk.db.dao;

import java.util.List;

public interface GenericDao<E> {

	boolean create(E item);

	E get(long id);

	boolean update(E item);

	boolean delete(E item);

	List<E> getAll();
}
