package es.jmmluna.tim;

import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import es.jmmluna.tim.application.service.employee.EmployeeDeletionService;
import es.jmmluna.tim.application.service.employee.EmployeeListingService;
import es.jmmluna.tim.application.service.employee.EmployeeSaveService;
import es.jmmluna.tim.application.service.employee.EmployeeService;
import es.jmmluna.tim.domain.model.employee.EmployeeId;
import es.jmmluna.tim.domain.model.employee.EmployeeRepository;
import es.jmmluna.tim.infrastructure.TimApplication;
import lombok.extern.slf4j.Slf4j;
@SpringBootTest(classes = TimApplication.class)
@Slf4j
@DisplayName("Employee test")
@ActiveProfiles("test")
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
	private EmployeeByIdService employeeByIdService;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	@Sql("classpath:employee-test-data.sql")
	@DisplayName("Initialize employees")
	@Order(1)
	public void shouldSaveEmployeesThroughSqlFile() {
		var employee = this.employeeRepository.getById(EmployeeId.of(1L));
		assertNotNull(employee);
	}

	@Test
	@DisplayName("Employee creation")
	@Order(2)
	public void testEmployeeCreation() {
		var employeeDTO = this.getExternalEmployee();
		this.employeeSaveService.execute(employeeDTO);
		var employee = this.employeeRepository.getByName("José María");
		assertNotNull(employee);
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
		var employee = this.employeeRepository.getByName("José María");
		employeeDeletionService.execute(EmployeeService.toDTO(employee));

		Long employeeCount = this.employeeCountService.execute();
		assertTrue(employeeCount == 4);
	}

	@Test
	@DisplayName("Find employee by Id")
	@Order(6)
	public void testFindEmployeeById() {
		var employeeDTO = employeeByIdService.execute(1L);
		assertNotNull(employeeDTO);

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
