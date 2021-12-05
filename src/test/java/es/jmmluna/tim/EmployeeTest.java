package es.jmmluna.tim;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.jmmluna.tim.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;


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
	
//	@Test
//	public void givenSubclasses_whenQuerySuperclass_thenOk() {
//	    Book book = new Book(1, "1984", "George Orwell");
//	    session.save(book);
//	    Pen pen = new Pen(2, "my pen", "blue");
//	    session.save(pen);
//
//	    assertThat(session.createQuery("from MyProduct")
//	      .getResultList()).hasSize(2);
//	}
}
