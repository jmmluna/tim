package es.jmmluna.tim;


import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.jmmluna.tim.application.service.customer.CustomerListingService;
import es.jmmluna.tim.domain.model.customer.validation.InvalidDNIException;
import lombok.extern.slf4j.Slf4j;

//@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CustomerTest {

	@Autowired
	private CustomerListingService customerListingService;
	
	@Test
	@DisplayName(" get all customers")
    public void testCustomerListing() throws InvalidDNIException {
		
		var customers = customerListingService.getCustomerList();
		
		log.info("#######Clientes: " + customers.size());
//		List<Employee> employees = employeeService.getAll();
//		assertThat(employees, not(new ArrayList<Employee>()));
		
//		assertThat(employees).isNotNull();
//		assertEquals(2, 2);
//		log.info("Hay " + employees.size() + " empleados");
//		assumeTrue(employees.size() > 1);
		assumeTrue(2 > 1);
		
	}
	
	
}
