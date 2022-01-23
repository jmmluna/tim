package es.jmmluna.tim.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.Date;
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
import es.jmmluna.tim.application.service.budget.useCase.GetBudget;
import es.jmmluna.tim.application.service.customer.useCase.GetCustomer;
import es.jmmluna.tim.application.service.work.WorkDTO;
import es.jmmluna.tim.application.service.work.WorkItemDTO;
import es.jmmluna.tim.application.service.work.useCase.AddWorkItem;
import es.jmmluna.tim.application.service.work.useCase.CreateWork;
import es.jmmluna.tim.application.service.work.useCase.DisableWork;
import es.jmmluna.tim.application.service.work.useCase.GetActiveWorkCount;
import es.jmmluna.tim.application.service.work.useCase.GetWork;
import es.jmmluna.tim.application.service.work.useCase.GetWorkList;
import es.jmmluna.tim.application.service.work.useCase.RemoveWorkItem;
import es.jmmluna.tim.application.service.work.useCase.UpdateWork;
import es.jmmluna.tim.domain.model.IdentifierNotAllowedException;
import es.jmmluna.tim.infrastructure.TimApplication;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = TimApplication.class)
@DisplayName("Work test")
@TestMethodOrder(OrderAnnotation.class)
@ActiveProfiles("test")
@Slf4j
public class WorkIT {

	@Autowired
	private GetWork getWork;

	@Autowired
	private CreateWork createWork;

	@Autowired
	private GetWorkList getWorkList;

	@Autowired
	private UpdateWork updateWork;
	//
	@Autowired
	private GetActiveWorkCount getActiveWorkCount;

	@Autowired
	private DisableWork disableWork;

	@Autowired
	private AddWorkItem addWorkItem;

	@Autowired
	private RemoveWorkItem removeWorkItem;

	@Autowired
	private GetBudget getBudget;

	@Autowired
	private GetCustomer getCustomer;

	@Test
	@Sql("classpath:drop-all.sql")
	@Sql("classpath:customer-test-data.sql")
	@Sql("classpath:budget-test-data.sql")
	@Sql("classpath:budgetItem-test-data.sql")
	@Sql("classpath:work-test-data.sql")
	@Sql("classpath:workItem-test-data.sql")
	@DisplayName("Initialize works")
	@Order(1)
	public void shouldSaveWorksThroughSqlFile() {
		// give

		// loaded data

		// when
		Long count = this.getActiveWorkCount.execute();

		// then
		assertEquals(1, count);
	}

	@Test
	@DisplayName("Given work get customer id ")
	@Order(2)
	public void givenWorkWithIdentifier_whenExecute_getCustomerId() {
		// give
		var uuid = UUID.fromString("123e4567-e89b-12d3-a456-556642440000");

		// when
		var workDTO = getWork.execute(uuid);

		// then
		assertEquals(UUID.fromString("123e4567-e89b-12d3-a456-556642440002"), workDTO.getCustomerDTO().getUuid());
	}

	@Test
	@DisplayName("Given work get budget id ")
	@Order(3)
	public void givenWorkWithIdentifier_whenExecute_getBudgetId() {
		// give
		var uuid = UUID.fromString("123e4567-e89b-12d3-a456-556642440000");

		// when
		var workDTO = getWork.execute(uuid);

		// then
		assertEquals(UUID.fromString("123e4567-e89b-12d3-a456-556642440000"), workDTO.getBudgetDTO().getUuid());
	}

	@Test
	@DisplayName("Given work get work items ")
	@Order(4)
	public void givenWorkWithIdentifier_whenExecute_getItems() {
		// give
		var uuid = UUID.fromString("123e4567-e89b-12d3-a456-556642440000");

		// when
		var workDTO = getWork.execute(uuid);

		// then
		assertEquals(2, workDTO.getWorkItems().size());
	}

	@Test
	@DisplayName("Create budget with UUID")
	@Order(5)
	public void givenWorkWithIdentifier_whenCreate_throwsException() {
		assertThrows(IdentifierNotAllowedException.class, () -> {
			var workDTO = this.getExternalWork(true);
			createWork.execute(workDTO);
		});
	}

	@Test
	@DisplayName("Create work")
	@Order(6)
	public void TestCreateWork() {
		// give
		assertEquals(1, this.getActiveWorkCount.execute());
		var workDTO = this.getExternalWork(false);
		assertNull(workDTO.getUuid());

		// when
		var savedWork = createWork.execute(workDTO);

		// then
		assertEquals(2, this.getActiveWorkCount.execute());
		assertNotNull(savedWork.getUuid());
	}

	@Test
	@DisplayName("Get active work list")
	@Order(7)
	public void testGetActiveWorkList() {
		List<WorkDTO> works = this.getWorkList.execute(EElementList.ACTIVE);
		assertTrue(works.size() == 2);
	}

	@Test
	@DisplayName("Update work")
	@Order(8)
	public void TestUpdateWork() {
		// given
		var workDTO = getWork.execute(UUID.fromString("123e4567-e89b-12d3-a456-556642440000"));
		assertEquals(workDTO.getDescription(), "Trabajo 1", "No coincide la descripción del trabajo original");
		// when
		workDTO.setDescription("Fachada");
		// then
		var savedWorkDTO = updateWork.execute(workDTO);
		assertEquals(savedWorkDTO.getDescription(), "Fachada", "No coincide la descripción del trabajo modificado");

	}

	@Test
	@DisplayName("Delete work")
	@Order(9)
	public void testDisableWork() {
		// given
		var workId = UUID.fromString("123e4567-e89b-12d3-a456-556642440000");
		// when
		disableWork.execute(workId);

		// then
		var disabledWorkDTO = this.getWork.execute(workId);
		assertNotNull(disabledWorkDTO.getExpirationDate(), "El trabajo debe tener asignado una fecha de baja");

		Long workCount = this.getActiveWorkCount.execute();
		assertTrue(workCount == 1, "El número de trabajos no es correcto después de eliminar");
	}

	@Test
	@DisplayName("Add work Item")
	@Order(10)
	public void testAddWorkItem() {

		// given
		var workId = UUID.fromString("123e4567-e89b-12d3-a456-556642440000");
		var workItem = new WorkItemDTO(UUID.randomUUID(), "Planchas", 2, 5.0, new Date());

		// when
		var workDTO = addWorkItem.execute(workId, workItem);

		// then
		assertEquals(3, workDTO.getWorkItems().size());
	}

	@Test
	@DisplayName("Remove work Item")
	@Order(11)
	public void testRemoveWorkItem() {

		// given
		var workId = UUID.fromString("123e4567-e89b-12d3-a456-556642440000");
		var workDTO = getWork.execute(workId);
		var workItemDTO = workDTO.getWorkItems().get(0);

		// when
		var removedWorkDTO = removeWorkItem.execute(workId, workItemDTO);

		// then
		assertEquals(2, removedWorkDTO.getWorkItems().size());
	}

	@Test
	@DisplayName("Create work based on budget")
	@Order(12)
	public void TestCreateWorkFromBudget() {
		// give
		var budgetId = UUID.fromString("123e4567-e89b-12d3-a456-556642440000");
		var budgetDTO = getBudget.execute(budgetId);

		// when
		var savedWorkDTO = createWork.execute(budgetDTO);

		// then
		assertEquals(1, savedWorkDTO.getWorkItems().size());
		Long workCount = this.getActiveWorkCount.execute();
		assertTrue(workCount == 2, "El número de trabajos no es correcto después de crear");
	}

	private WorkDTO getExternalWork(boolean withUUID) {
		var workItems = Collections.<WorkItemDTO>emptyList();
		UUID workId = withUUID ? UUID.randomUUID() : null;
		var customerDTO = getCustomer.execute(UUID.fromString("123e4567-e89b-12d3-a456-556642440002"));
		var budgetDTO = getBudget.execute(UUID.fromString("123e4567-e89b-12d3-a456-556642440000"));
		return new WorkDTO(workId, budgetDTO, customerDTO, "Trabajo de prueba", 0, new Date(), 0.0, workItems);
	}
}
