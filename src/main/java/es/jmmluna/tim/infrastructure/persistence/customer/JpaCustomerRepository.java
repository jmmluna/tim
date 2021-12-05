package es.jmmluna.tim.infrastructure.persistence.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCustomerRepository extends JpaRepository<JpaCustomerEntity, String> {


}