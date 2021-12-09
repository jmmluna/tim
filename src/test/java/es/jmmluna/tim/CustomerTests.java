package es.jmmluna.tim;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
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
import es.jmmluna.tim.domain.model.customer.Dni;
import es.jmmluna.tim.infrastructure.TimApplication;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = TimApplication.class)
@Slf4j
@DisplayName("Customer test")
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
public class CustomerTests {

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
	@Order(1)
	public void shouldSaveCustomersThroughSqlFile() {
		Customer customer = customerRepository.getById(CustomerId.of("123e4567-e89b-12d3-a456-556642440000"));
		
		assertNotNull(customer);
	}

	@Test
	@DisplayName("Customer Listing")
	@Order(2)
	public void testCustomerListing()  {
		var customers = customerListingService.execute();
		
		assertEquals(customers.size(), 3, "No coincide el número total de clientes");		
	}

	@Test
	@DisplayName("Customer creation")
	@Order(3)
	public void testCustomerCreation()  {
		var customerDTO = getExternalCustomer();
		
		customerSaveService.execute(customerDTO);
		Customer customer = customerRepository.getById(CustomerId.of(customerDTO.getUuid()));
		assertNotNull(customer);
		assertEquals(customer.getCustomerId().getValue(), customerDTO.getUuid(), "El empleado creado no tiene el mismo UUID");
	}
	
//	@Test
//	@DisplayName("Customer edition")
//	@Order(4)
//	public void testCustomerEdition()  {		
//		var customerDTO = getExternalCustomer();
//		customerDTO.setName("Rafael");
//						
//		customerEditionService.execute(customerDTO);
//		
//		Customer modifiedCustomer = customerRepository.getById(CustomerId.of(customerDTO.getUuid()));
//		assertNotNull(modifiedCustomer);
//		assertEquals(modifiedCustomer.getName(), "Rafael");
//	}
	
	private CustomerDTO getExternalCustomer() {
		CustomerDTO customerDTO = new CustomerDTO(UUID.randomUUID());
		customerDTO.setDni("30835115G");
		customerDTO.setName("José María");
		customerDTO.setSurnames("Martínez Luna");
		customerDTO.setAddress("Parque de las avenidas");
		customerDTO.setPhone("679948260");
		customerDTO.setEmail("jmmluna@gmail.com");
		
		return customerDTO;
	}
}
