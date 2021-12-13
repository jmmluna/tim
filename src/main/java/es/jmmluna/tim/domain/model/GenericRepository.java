package es.jmmluna.tim.domain.model;

import java.util.List;

public interface GenericRepository <T, ID extends ValueObject>{

	public T save(T model);

	public List<T> getAll();

	public T getById(ID id);

	public long getActiveCount();

	public T delete(T model);

	public T delete(String id);
	
	public T delete(ID id);

	public List<T> getActives();

	public List<T> getInactives();

	public T getByName(String name);

	

}
