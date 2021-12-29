package es.jmmluna.tim.domain.model;

import java.util.List;

public interface CommonRepository<T, ID> {
	public T save(T domain);

	public void delete(T domain);

	public T findById(ID id);

	public List<T> findAll();

}
