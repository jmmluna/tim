package es.jmmluna.tim;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import org.springframework.test.context.jdbc.Sql;

import es.jmmluna.tim.application.service.EElementList;
import es.jmmluna.tim.application.service.customer.CustomerDTO;
import es.jmmluna.tim.application.service.customer.useCase.DisableCustomer;
import es.jmmluna.tim.application.service.customer.useCase.GetActiveCustomerCount;
import es.jmmluna.tim.application.service.customer.useCase.GetCustomer;
import es.jmmluna.tim.application.service.customer.useCase.GetCustomerList;
import es.jmmluna.tim.application.service.customer.useCase.SaveCustomer;
import es.jmmluna.tim.infrastructure.TimApplication;

@SpringBootTest(classes = TimApplication.class)
@DisplayName("Customer test")
@TestMethodOrder(OrderAnnotation.class)
public class CustomerTests {

	@Autowired
	private GetCustomer getCustomer;

	@Autowired
	private SaveCustomer saveCustomer;

	@Autowired
	private GetCustomerList getCustomerList;

	@Autowired
	private GetActiveCustomerCount getActiveCustomerCount;

	@Autowired
	private DisableCustomer disableCustomer;

	@Test
	@Sql("classpath:customer-test-data.sql")
	@DisplayName("Initialize customers")
	@Order(1)
	public void shouldSaveCustomersThroughSqlFile() {
		var customerDTO = getCustomer.execute(UUID.fromString("123e4567-e89b-12d3-a456-556642440000"));
		assertNotNull(customerDTO, "El cliente debe estar registrado");
		assertEquals(customerDTO.getName(), "usuario 1", "No coincide el nombre del cliente");
	}

	@Test
	@DisplayName("Customer creation")
	@Order(2)
	public void testCustomerCreation() {
		var customerDTO = this.getExternalCustomer();
		var savedCustomerDTO = this.saveCustomer.execute(customerDTO);
		assertEquals(savedCustomerDTO.getUuid(), customerDTO.getUuid(), "No coincide el id del cliente");
		assertEquals(savedCustomerDTO.getName(), customerDTO.getName(), "No coincide el nombre del cliente");
	}

	@Test
	@DisplayName("Active customer listing")
	@Order(3)
	public void testGetCustomerListg() {
		List<CustomerDTO> customers = this.getCustomerList.execute(EElementList.ACTIVE);
		assertTrue(customers.size() == 4);
	}

	@Test
	@DisplayName("Active customer count")
	@Order(4)
	public void testActiveCustomerCount() {
		Long customerCount = this.getActiveCustomerCount.execute();
		assertTrue(customerCount == 4);
	}

	@Test
	@DisplayName("Delete customer")
	@Order(5)
	public void testDisableCustomer() {

		disableCustomer.execute(UUID.fromString("123e4567-e89b-12d3-a456-556642440000"));

		var deletedCustomerDTO = this.getCustomer.execute(UUID.fromString("123e4567-e89b-12d3-a456-556642440000"));
		assertNotNull(deletedCustomerDTO.getExpirationDate(), "El cliente tiene que tener asignado la fecha de baja");

		Long customerCount = this.getActiveCustomerCount.execute();
		assertTrue(customerCount == 3, "El número de clientes no es correcto después de eliminar");
	}

	private CustomerDTO getExternalCustomer() {
		return new CustomerDTO(UUID.randomUUID(), "30XXXXXX", "José María", "Martínez Luna", "XXXXX", "6666666",
				"jmmluna@gmail.com");
	}
}
