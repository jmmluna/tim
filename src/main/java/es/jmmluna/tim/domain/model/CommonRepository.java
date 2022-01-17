package es.jmmluna.tim.domain.model;

import java.util.List;

public interface CommonRepository<T, ID> {
    T save(T domain);

    void delete(T domain);

    T findById(ID id);

    List<T> findAll();

}
