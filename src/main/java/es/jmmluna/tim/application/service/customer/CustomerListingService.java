package es.jmmluna.tim.application.service.customer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.domain.model.customer.CustomerRepository;

@Service
public class CustomerListingService extends CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	public List<CustomerDTO> execute() {
        var customers = customerRepository.findAll();         
        var dtos = new ArrayList<CustomerDTO>();
        
        for (var customer : customers) {
        	                        
            dtos.add(this.toDTO(customer));
        }
        return dtos;
    }
}
