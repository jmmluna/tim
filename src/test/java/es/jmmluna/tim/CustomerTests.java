package es.jmmluna.tim;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import es.jmmluna.tim.application.service.EElementList;
import es.jmmluna.tim.application.service.customer.CustomerByIdService;
import es.jmmluna.tim.application.service.customer.CustomerCountService;
import es.jmmluna.tim.application.service.customer.CustomerDTO;
import es.jmmluna.tim.application.service.customer.CustomerDeletionByIdService;
import es.jmmluna.tim.application.service.customer.CustomerDeletionService;
import es.jmmluna.tim.application.service.customer.CustomerListingService;
import es.jmmluna.tim.application.service.customer.CustomerSaveService;
import es.jmmluna.tim.infrastructure.TimApplication;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = TimApplication.class)
@Slf4j
@DisplayName("Customer test")
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
public class CustomerTests {

	@Autowired
	private CustomerByIdService customerByIdService;

	@Autowired
	private CustomerSaveService customerSaveService;

	@Autowired
	private CustomerListingService customerListingService;

	@Autowired
	private CustomerCountService customerCountService;

	@Autowired
	private CustomerDeletionService customerDeletionService;
	
	@Autowired
	private CustomerDeletionByIdService customerDeletionByIdService;

	@Test
	@Sql("classpath:customer-test-data.sql")
	@DisplayName("Initialize customers")
	@Order(1)
	public void shouldSaveCustomersThroughSqlFile() {
		var customerDTO = customerByIdService.execute("123e4567-e89b-12d3-a456-556642440000");
		assertNotNull(customerDTO, "El cliente debe estar registrado");
		assertEquals(customerDTO.getName(), "usuario 1", "No coincide el nombre del cliente");
	}

	@Test
	@DisplayName("Customer creation")
	@Order(2)
	public void testCustomerCreation() {
		var customerDTO = this.getExternalCustomer();
		var savedCustomerDTO = this.customerSaveService.execute(customerDTO);
		assertEquals(savedCustomerDTO.getUuid(), customerDTO.getUuid(), "No coincide el id del cliente");
		assertEquals(savedCustomerDTO.getName(), customerDTO.getName(), "No coincide el nombre del cliente");
	}

	@Test
	@DisplayName("Active customer listing")
	@Order(3)
	public void testActiveCustomerListing() {
		List<CustomerDTO> customers = this.customerListingService.execute(EElementList.ACTIVE);
		assertTrue(customers.size() == 4);
	}

	@Test
	@DisplayName("Active customer count")
	@Order(4)
	public void testActiveCustomerCount() {
		Long customerCount = this.customerCountService.execute();
		assertTrue(customerCount == 4);
	}

	@Test
	@DisplayName("Delete customer")
	@Order(5)
	public void testDeleteCustomer() {

		var customerDTO = this.customerByIdService.execute("123e4567-e89b-12d3-a456-556642440000");
		var deletedCustomerDTO = customerDeletionService.execute(customerDTO);

		assertEquals(customerDTO.getName(), deletedCustomerDTO.getName(),
				"No coincide el nombre del cliente eliminado");

		assertNull(customerDTO.getExpirationDate(), "La fecha de baja tiene que ser nula");
		assertNotNull(deletedCustomerDTO.getExpirationDate(), "El cliente tiene que tener asignado la fecha de baja");

		Long customerCount = this.customerCountService.execute();
		assertTrue(customerCount == 3, "El número de clientes no es correcto después de eliminar");
	}

	@Test
	@DisplayName("Delete customer by Id")
	@Order(6)
	public void testDeleteEmployeeById() {
		var deletedCustomerDTO = customerDeletionByIdService.execute("123e4567-e89b-12d3-a456-556642440001");

		assertEquals("123e4567-e89b-12d3-a456-556642440001", deletedCustomerDTO.getUuid().toString(), "No coincide el nombre del cliente eliminado");
		assertNotNull(deletedCustomerDTO.getExpirationDate(), "El cliente tiene que tener asignado la fecha de baja");

		Long customerCount = this.customerCountService.execute();
		assertTrue(customerCount == 2, "El número de clientes no es correcto después de eliminar");

	}

	private CustomerDTO getExternalCustomer() {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setUuid(UUID.randomUUID());
		customerDTO.setDni("30XXXXXX");
		customerDTO.setName("José María");
		customerDTO.setSurnames("Martínez Luna");
		customerDTO.setAddress("XXXXX");
		customerDTO.setPhone("6666666");
		customerDTO.setEmail("jmmluna@gmail.com");

		return customerDTO;
	}
}
