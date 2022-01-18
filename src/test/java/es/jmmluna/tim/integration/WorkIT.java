package es.jmmluna.tim.integration;

import es.jmmluna.tim.application.service.EElementList;
import es.jmmluna.tim.application.service.budget.BudgetDTO;
import es.jmmluna.tim.application.service.budget.BudgetItemDTO;
import es.jmmluna.tim.application.service.budget.useCase.*;
import es.jmmluna.tim.application.service.customer.useCase.GetCustomer;
import es.jmmluna.tim.application.service.work.useCase.GetActiveWorkCount;
import es.jmmluna.tim.application.service.work.useCase.GetWork;
import es.jmmluna.tim.domain.model.IdentifierNotAllowedException;
import es.jmmluna.tim.infrastructure.TimApplication;

import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = TimApplication.class)
@DisplayName("Budget test")
@TestMethodOrder(OrderAnnotation.class)
@ActiveProfiles("test")
@Slf4j
public class WorkIT {

	@Autowired
	private GetWork getWork;

//	@Autowired
//	private CreateBudget createBudget;
//
//	@Autowired
//	private GetBudgetList getBudgetList;
//
//	@Autowired
//	private UpdateBudget updateBudget;
//
	@Autowired
	private GetActiveWorkCount getActiveWorkCount;
//
//	@Autowired
//	private DisableBudget disableBudget;
//
//	@Autowired
//	private AddBudgetItem addBudgetItem;
//
//	@Autowired
//	private RemoveBudgetItem removeBudgetItem;
//
//	@Autowired
//	private GetCustomer getCustomer;

	@Test

	@Sql("classpath:drop-all.sql")
	@Sql("classpath:customer-test-data.sql")
	@Sql("classpath:budget-test-data.sql")
	@Sql("classpath:budgetItem-test-data.sql")
	@Sql("classpath:work-test-data.sql")
	@Sql("classpath:workItem-test-data.sql")
	@DisplayName("Initialize budgets")
	@Order(1)
	public void shouldSaveWorksThroughSqlFile() {
		//give 
		
		// load sql
		
		// when
		Long count = this.getActiveWorkCount.execute();
		
		// then
		 assertEquals(1, count);
	}
	
	@Test
	@DisplayName("Given work get customer id ")
	@Order(2)
	public void givenWorkWithIdentifier_whenExecute_getCustomer() {
		// give
		var uuid = UUID.fromString("123e4567-e89b-12d3-a456-556642440000");

		// when
		var workDTO = getWork.execute(uuid);

		// then
		 assertEquals( UUID.fromString("123e4567-e89b-12d3-a456-556642440002"), workDTO.getCustomerDTO().getUuid());
	}

//	@Test
//	@DisplayName("Given work get budget id ")
//	@Order(3)
//	public void givenWorkWithIdentifier_whenExecute_getBudget() {
//		// give
//		var uuid = UUID.fromString("123e4567-e89b-12d3-a456-556642440000");
//
//		// when
//		var workDTO = getWork.execute(uuid);
//
//		// then
//		assertEquals( UUID.fromString("123e4567-e89b-12d3-a456-556642440000"), workDTO.getBudgetDTO().getUuid());
//	}
//
//	@Test
//	@DisplayName("Given work get work items ")
//	@Order(4)
//	public void givenBudgetWithIdentifier_whenExecute_getItems() {
//		// give
//		var uuid = UUID.fromString("123e4567-e89b-12d3-a456-556642440000");
//
//		// when
//		var workDTO = getWork.execute(uuid);
//
//		// then
//		 assertEquals(0, workDTO.getWorkItems().size());
//	}







//
//
//	@Test
//	@DisplayName("Create budget with UUID")
//	@Order(4)
//	public void givenBudgetWithIdentifier_whenCreate_throwsException() {
//
//		assertThrows(IdentifierNotAllowedException.class, () -> {
//			var budgetDTO = this.getExternalBudget(true);
//			createBudget.execute(budgetDTO);
//		});
//	}
//
//	@Test
//	@DisplayName("Create budget")
//	@Order(5)
//	public void TestCreateBudget() {
//		//give
//		var budgetDTO = this.getExternalBudget(false);
//
//		//when
//		createBudget.execute(budgetDTO);
//
//		//then
//		 assertEquals(4, this.getActiveBudgetCount.execute());
//	}
//
//	@Test
//	@DisplayName("Get active budget list")
//	@Order(6)
//	public void testGetActiveBudgetList() {
//		List<BudgetDTO> budgets = this.getBudgetList.execute(EElementList.ACTIVE);
//		assertTrue(budgets.size() == 4);
//	}
//
//	@Test
//	@DisplayName("Update budget")
//	@Order(7)
//	public void TestUpdateBudget() {
//		var budgetDTO = getBudget.execute(UUID.fromString("123e4567-e89b-12d3-a456-556642440000"));
//		assertEquals(budgetDTO.getBudgetNumber(), 30, "No coincide el número del presupuesto original");
//
//		budgetDTO.setBudgetNumber(2000);
//		var savedBudgetDTO = updateBudget.execute(budgetDTO);
//		assertEquals(savedBudgetDTO.getBudgetNumber(), 2000, "No coincide el número del presupuesto modificado");
//
//	}
//
//	@Test
//	@DisplayName("Delete budget")
//	@Order(8)
//	public void testDisableBudget() {
//		disableBudget.execute(UUID.fromString("123e4567-e89b-12d3-a456-556642440000"));
//
//		var disabledBudgetDTO = this.getBudget.execute(UUID.fromString("123e4567-e89b-12d3-a456-556642440000"));
//		assertNotNull(disabledBudgetDTO.getExpirationDate(),
//				"El presupuesto tiene que tener asignado la fecha de baja");
//
//		Long budgetCount = this.getActiveBudgetCount.execute();
//		assertTrue(budgetCount == 3, "El número de presupuestos no es correcto después de eliminar");
//	}
//
//	@Test
//	@DisplayName("Add budget Item")
//	@Order(9)
//	public void testAddBudgetItem() {
//
//		// given
//		var uuid = UUID.fromString("123e4567-e89b-12d3-a456-556642440000");
//		BudgetItemDTO bItem1 = new BudgetItemDTO(UUID.randomUUID(), "Cajas de bombillas", 2, 5.0);
//
//		// when
//		var budgetDTO = addBudgetItem.execute(uuid, bItem1);
//
//		// then
//		assertEquals(2, budgetDTO.getBudgetItems().size());
//	}
//
//	@Test
//	@DisplayName("Remove budget Item")
//	@Order(10)
//	public void testRemoveBudgetItem() {
//
//		// given
//		var uuid = UUID.fromString("123e4567-e89b-12d3-a456-556642440000");
//		var budgetDTO = getBudget.execute(uuid);
//		var budgetItemDTO = budgetDTO.getBudgetItems().get(0);
//
//		// when
//		var removedBudgetDTO = removeBudgetItem.execute(uuid, budgetItemDTO);
//
//		// then
//		assertEquals(1, removedBudgetDTO.getBudgetItems().size());
//	}
//
//	private BudgetDTO getExternalBudget(boolean withUUID) {
//		List<BudgetItemDTO> budgetItems = Collections.<BudgetItemDTO>emptyList();
//		UUID budgetId = withUUID ? UUID.randomUUID() : null;
//		var customerDTO = getCustomer.execute(UUID.fromString("123e4567-e89b-12d3-a456-556642440002"));
//		return new BudgetDTO(budgetId, customerDTO, "Presupuesto de prueba", 1000, 2021, new Date(), 0.0, budgetItems);
//	}
}
