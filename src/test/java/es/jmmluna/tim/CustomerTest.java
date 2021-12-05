package es.jmmluna.tim;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import es.jmmluna.tim.application.service.customer.CustomerDTO;
import es.jmmluna.tim.application.service.customer.CustomerEditionService;
import es.jmmluna.tim.application.service.customer.CustomerListingService;
import es.jmmluna.tim.application.service.customer.CustomerSaveService;
import es.jmmluna.tim.domain.model.customer.Customer;
import es.jmmluna.tim.domain.model.customer.CustomerId;
import es.jmmluna.tim.domain.model.customer.CustomerRepository;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@DisplayName("Customer test")
@Slf4j
//@DataJpaTest
@ActiveProfiles("test")
public class CustomerTest {

	@Autowired
	private CustomerSaveService customerSaveService;
	
	@Autowired
	private CustomerListingService customerListingService;
	
	@Autowired
	private CustomerEditionService customerEditionService;

	@Autowired
	private CustomerRepository customerRepository;

	@Test
	@Sql("classpath:customer-test-data.sql")
	@DisplayName("Initialize customers")
	public void shouldSaveCustomersThroughSqlFile() {
		Customer customer = customerRepository.findByCustomerId(CustomerId.of("30"));
		assertNotNull(customer);
	}

	@Test
	@DisplayName("Customer Listing")
	public void testCustomerListing()  {
		var customers = customerListingService.execute();
		assumeTrue(customers.size() == 3);
	}

	@Test
	@DisplayName("Customer creation")
	public void testCustomerCreation()  {
		var customerDTO = getExternalCustomer();
				
		customerSaveService.execute(customerDTO);
		Customer customer = customerRepository.findByCustomerId(CustomerId.of("301"));
		assertNotNull(customer);
	}
	
	@Test
	@DisplayName("Customer edition")
	public void testCustomerEdition()  {		
		var customerDTO = getExternalCustomer();
		customerDTO.setName("Rafael");
						
		customerEditionService.execute(customerDTO);
		
		Customer modifiedCustomer = customerRepository.findByCustomerId(CustomerId.of("301"));
		assertNotNull(modifiedCustomer);
		assertEquals(modifiedCustomer.getName(), "Rafael");
	}
	
	private CustomerDTO getExternalCustomer() {
		CustomerDTO customerDTO = new CustomerDTO("301");
		customerDTO.setName("José María");
		customerDTO.setSurnames("Martínez Luna");
		customerDTO.setAddress("Parque de las avenidas");
		customerDTO.setPhone("679948260");
		customerDTO.setEmail("jmmluna@gmail.com");
		
		return customerDTO;
	}
}
