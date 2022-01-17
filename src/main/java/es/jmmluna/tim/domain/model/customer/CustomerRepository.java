package es.jmmluna.tim.domain.model.customer;

import java.util.List;

import es.jmmluna.tim.domain.model.CommonRepository;

public interface CustomerRepository extends CommonRepository<Customer, CustomerId> {

    long getActiveCount();

    List<Customer> getActives();

    List<Customer> getInactives();

    Customer getByName(String name);

}
