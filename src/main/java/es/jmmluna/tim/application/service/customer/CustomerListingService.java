package es.jmmluna.tim.application.service.customer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.domainl.port.CustomerRepository;

@Service
public class CustomerListingService {

	@Autowired
	private CustomerRepository customerRepository;
	
	public List<CustomerDTO> getCustomerList() {
        var customers = customerRepository.findAll(); 
        
        var dtos = new ArrayList<CustomerDTO>();
        for (var customer : customers) {
            
            var dto = new CustomerDTO(); 
            dto.setId(customer.getCustomerId().getValue());
            dto.setName(customer.getName());
            dto.setSurnames(customer.getSurnames());
            dtos.add(dto);
        }
        return dtos;
    }
}
