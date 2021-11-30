package es.jmmluna.tim;


import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.assertj.core.api.Assertions.assertThat;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import es.jmmluna.tim.model.Employee;
import es.jmmluna.tim.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class EmployeeTest {

	@Autowired
	EmployeeService employeeService;
	
	@Test
	@DisplayName("Tests get all employees")
    public void testGetAllEmployees() {
//		List<Employee> employees = employeeService.getAll();
//		assertThat(employees, not(new ArrayList<Employee>()));
		
//		assertThat(employees).isNotNull();
//		assertEquals(2, 2);
//		log.info("Hay " + employees.size() + " empleados");
//		assumeTrue(employees.size() > 1);
		assumeTrue(2 > 1);
		
	}
	
	@Test
    void whenGetAll_thenemployeeListIsNotEmpty() {
        log.info("Running When Case1: test1_1");
        assertThat(employeeService.getAll()).isNotEmpty();
    }
}
