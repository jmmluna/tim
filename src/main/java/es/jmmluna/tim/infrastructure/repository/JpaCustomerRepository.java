package es.jmmluna.tim.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.jmmluna.tim.infrastructure.entity.JpaCustomerEntity;

@Repository
public interface JpaCustomerRepository extends JpaRepository<JpaCustomerEntity, String> {


}