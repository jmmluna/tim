package es.jmmluna.tim;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import es.jmmluna.tim.application.service.EElementList;
import es.jmmluna.tim.application.service.employee.EmployeeDTO;
import es.jmmluna.tim.application.service.employee.EmployeeListingService;
import es.jmmluna.tim.application.service.employee.EmployeeSaveService;
import es.jmmluna.tim.domain.model.employee.EmployeeId;
import es.jmmluna.tim.domain.model.employee.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@DisplayName("Employee test")
@ActiveProfiles("test")
public class EmployeeTest {

	@Autowired
	private EmployeeSaveService employeeSaveService;
	
	@Autowired
	private EmployeeListingService employeeListingService;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	@Sql("classpath:employee-test-data.sql")
	@DisplayName("Initialize employees")
	public void shouldSaveEmployeesThroughSqlFile() {
		var employee = employeeRepository.getById(EmployeeId.of(1L));
		assertNotNull(employee);
		
	}

	@Test
	@DisplayName("Customer creation")
	public void testCustomerCreation() {
		var employeeDTO = getExternalEmployee();
		employeeSaveService.execute(employeeDTO);
		var employee = employeeRepository.getByName("José María");
		assertNotNull(employee);
	}
	
	@Test
	@DisplayName("Customer listing")
	public void testCustomerListing() {		
		List<EmployeeDTO> employees= employeeListingService.execute(EElementList.ACTIVE);		
		assertTrue(employees.size() == 5);
	}


	private EmployeeDTO getExternalEmployee() {
		var employeeDTO = new EmployeeDTO(301L);
		employeeDTO.setName("José María");
		employeeDTO.setSurnames("Martínez Luna");
		employeeDTO.setCustomerHourPrice(10D);
		employeeDTO.setEmployeeHourPrice(15D);

		return employeeDTO;
	}
}
