package es.jmmluna.tim.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaEmployeeRepository extends JpaRepository<JpaEmployeeEntity, String> {


}