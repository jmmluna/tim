package es.jmmluna.tim;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

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
import es.jmmluna.tim.application.service.employee.EmployeeByIdService;
import es.jmmluna.tim.application.service.employee.EmployeeCountService;
import es.jmmluna.tim.application.service.employee.EmployeeDTO;
import es.jmmluna.tim.application.service.employee.EmployeeDeletionByIdService;
import es.jmmluna.tim.application.service.employee.EmployeeDeletionService;
import es.jmmluna.tim.application.service.employee.EmployeeListingService;
import es.jmmluna.tim.application.service.employee.EmployeeSaveService;
import es.jmmluna.tim.infrastructure.TimApplication;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = TimApplication.class)
@Slf4j
@DisplayName("Employee test")
//@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
public class EmployeeTests {

	@Autowired
	private EmployeeSaveService employeeSaveService;

	@Autowired
	private EmployeeListingService employeeListingService;

	@Autowired
	private EmployeeCountService employeeCountService;

	@Autowired
	private EmployeeDeletionService employeeDeletionService;

	@Autowired
	private EmployeeDeletionByIdService employeeDeletionByIdService;

	@Autowired
	private EmployeeByIdService employeeByIdService;

	@Test
	@Sql("classpath:employee-test-data.sql")
	@DisplayName("Initialize employees")
	@Order(1)
	public void shouldSaveEmployeesThroughSqlFile() {
		var employeeDTO = this.employeeByIdService.execute(1L);

		assertEquals(employeeDTO.getName(), "usu1", "No coincide el nombre del empleado");
	}

	@Test
	@DisplayName("Employee creation")
	@Order(2)
	public void testEmployeeCreation() {
		var employeeDTO = this.getExternalEmployee();
		var savedEmployeeDTO = this.employeeSaveService.execute(employeeDTO);

		assertEquals(savedEmployeeDTO.getName(), employeeDTO.getName(), "No coincide el nombre del empleado");
	}

	@Test
	@DisplayName("Active employee listing")
	@Order(3)
	public void testActiveEmployeeListing() {
		List<EmployeeDTO> employees = this.employeeListingService.execute(EElementList.ACTIVE);
		assertTrue(employees.size() == 5);
	}

	@Test
	@DisplayName("Active employee count")
	@Order(4)
	public void testActiveEmployeeCount() {
		Long employeeCount = this.employeeCountService.execute();
		assertTrue(employeeCount == 5);
	}

	@Test
	@DisplayName("Delete employee")
	@Order(5)
	public void testDeleteEmployee() {
		var employeeDTO = this.employeeByIdService.execute(1L);
		var deletedEmployeeDTO = employeeDeletionService.execute(employeeDTO);

		assertEquals(employeeDTO.getName(), deletedEmployeeDTO.getName(),
				"No coincide el nombre del empleado eliminado");
		
		assertNull(employeeDTO.getExpirationDate(), "La fecha de baja tiene que ser nula");
		assertNotNull(deletedEmployeeDTO.getExpirationDate(), "El empleado tiene que tener asignado la fecha de baja");

		Long employeeCount = this.employeeCountService.execute();
		assertTrue(employeeCount == 4, "El número de empleados no es correcto después de eliminar");
	}

	@Test
	@DisplayName("Delete employee by Id")
	@Order(6)
	public void testDeleteEmployeeById() {
		var deletedEmployeeDTO = employeeDeletionByIdService.execute(2L);

		assertEquals(2L, deletedEmployeeDTO.getId(), "No coincide el nombre del empleado eliminado");
		assertNotNull(deletedEmployeeDTO.getExpirationDate(), "El empleado tiene que tener asignado la fecha de baja");

		Long employeeCount = this.employeeCountService.execute();
		assertTrue(employeeCount == 3, "El número de empleados no es correcto después de eliminar");

	}

	private EmployeeDTO getExternalEmployee() {
		var employeeDTO = new EmployeeDTO();
		employeeDTO.setName("José María");
		employeeDTO.setSurnames("Martínez Luna");
		employeeDTO.setCustomerHourPrice(10D);
		employeeDTO.setEmployeeHourPrice(15D);

		return employeeDTO;
	}
}
