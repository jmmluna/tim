package es.jmmluna.tim.domain.model;

public interface CommonRepository<T, ID> {
	public T save(T domain);

	public void delete(T domain);

	public T findById(ID id);

	public Iterable<T> findAll();

}
