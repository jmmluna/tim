package es.jmmluna.tim;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import es.jmmluna.tim.application.service.customer.CustomerListingService;
import es.jmmluna.tim.domain.model.customer.Customer;
import es.jmmluna.tim.domain.model.customer.CustomerId;
import es.jmmluna.tim.domain.model.customer.validation.InvalidDNIException;
import es.jmmluna.tim.domainl.port.CustomerRepository;
import lombok.extern.slf4j.Slf4j;


@SpringBootTest
@DisplayName("Customer test")
@Slf4j
//@DataJpaTest
@ActiveProfiles("test")
public class CustomerTest {

	@Autowired
	private CustomerListingService customerListingService;
	
	@Autowired
    private CustomerRepository customerRepository;
	
	@Test
    @Sql("classpath:customer-test-data.sql")
	@DisplayName("Initialize customers")
    public void shouldSaveCustomersThroughSqlFile() {
        Customer customer1 = customerRepository.findByCustomerId(CustomerId.of("30"));
        assertNotNull(customer1);        
    }
	
	@Test
	@DisplayName(" get all customers")
    public void testCustomerListing() throws InvalidDNIException {
		
		var customers = customerListingService.getCustomerList();
		
		log.info("#######Clientes: " + customers.size());

		assumeTrue(customers.size() > 1);
		
	}
	
	
}
