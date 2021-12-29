package es.jmmluna.tim;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import org.springframework.test.context.jdbc.Sql;

import es.jmmluna.tim.application.service.EElementList;
import es.jmmluna.tim.application.service.budget.BudgetDTO;
import es.jmmluna.tim.application.service.budget.BudgetItemDTO;
import es.jmmluna.tim.application.service.budget.useCase.AddBudgetItem;
import es.jmmluna.tim.application.service.budget.useCase.CreateBudget;
import es.jmmluna.tim.application.service.budget.useCase.DisableBudget;
import es.jmmluna.tim.application.service.budget.useCase.GetActiveBudgetCount;
import es.jmmluna.tim.application.service.budget.useCase.GetBudget;
import es.jmmluna.tim.application.service.budget.useCase.GetBudgetList;
import es.jmmluna.tim.application.service.budget.useCase.UpdateBudget;
import es.jmmluna.tim.domain.model.IdentifierNotAllowedException;
import es.jmmluna.tim.infrastructure.TimApplication;

@SpringBootTest(classes = TimApplication.class)
@DisplayName("Budget test")
@TestMethodOrder(OrderAnnotation.class)
//@Sql({"classpath:budget-test-data.sql", "classpath:budgetItem-test-data.sql"})
public class BudgetTests {

	@Autowired
	private GetBudget getBudget;

	@Autowired
	private CreateBudget createBudget;

	@Autowired
	private GetBudgetList getBudgetList;

	@Autowired
	private UpdateBudget updateBudget;

	@Autowired
	private GetActiveBudgetCount getActiveBudgetCount;

	@Autowired
	private DisableBudget disableBudget;

	@Autowired
	private AddBudgetItem addBudgetItem;

	@Test
	@Sql("classpath:budget-test-data.sql")
//	@Sql("classpath:budgetItem-test-data.sql")
	@DisplayName("Initialize budgets")
	@Order(1)
	public void shouldSaveBudgetsThroughSqlFile() {
		//give 
		
		// load sql
		
		// when
		Long budgetCount = this.getActiveBudgetCount.execute();
		
		// then
		 assertEquals(3, budgetCount);
	}


	@Test
	@DisplayName("Create budget with UUID")
	@Order(2)
	public void givenBudgetWithIdentifier_whenCreate_throwsException() {

		assertThrows(IdentifierNotAllowedException.class, () -> {
			var budgetDTO = this.getExternalBudget(true);
			createBudget.execute(budgetDTO);
		});
	}

	@Test
	@DisplayName("Create budget")
	@Order(3)
	public void TestCreateBudget() {
		//give
		
		var budgetDTO = this.getExternalBudget(false);
		
		// when
		
		createBudget.execute(budgetDTO);
		
		// then		

		 assertEquals(4, this.getActiveBudgetCount.execute());
	}

	@Test
	@DisplayName("Get active budget list")
	@Order(4)
	public void testGetActiveBudgetList() {
		List<BudgetDTO> budgets = this.getBudgetList.execute(EElementList.ACTIVE);
		assertTrue(budgets.size() == 4);
	}

	@Test
	@DisplayName("Update budget")
	@Order(5)
	public void TestUpdateBudget() {
		var budgetDTO = getBudget.execute(UUID.fromString("123e4567-e89b-12d3-a456-556642440000"));
		assertEquals(budgetDTO.getBudgetNumber(), 30, "No coincide el número del presupuesto original");

		budgetDTO.setBudgetNumber(2000);
		var savedBudgetDTO = updateBudget.execute(budgetDTO);
		assertEquals(savedBudgetDTO.getBudgetNumber(), 2000, "No coincide el número del presupuesto modificado");

	}


	@Test
	@DisplayName("Delete budget")
	@Order(7)
	public void testDisableBudget() {
		disableBudget.execute(UUID.fromString("123e4567-e89b-12d3-a456-556642440000"));

		var disabledBudgetDTO = this.getBudget.execute(UUID.fromString("123e4567-e89b-12d3-a456-556642440000"));
		assertNotNull(disabledBudgetDTO.getExpirationDate(),
				"El presupuesto tiene que tener asignado la fecha de baja");

		Long budgetCount = this.getActiveBudgetCount.execute();
		assertTrue(budgetCount == 3, "El número de presupuestos no es correcto después de eliminar");
	}

//	@Test
//	@DisplayName("Add budget Item")
//	@Order(8)
//	public void testAddBudgetItem() {
//
//		// given
//		BudgetItemDTO bItem1 = new BudgetItemDTO(UUID.randomUUID(), "Cajas de bombillas", 2, 5.0);
//
//		// when
//		var budgetDTO = addBudgetItem.execute(UUID.fromString("123e4567-e89b-12d3-a456-556642440000"), bItem1);
//		
//		// then
//		assertTrue(budgetDTO.getBudgetItems().size() == 1, "El número de elementos del presupuesto no es correcto");
//		
//
//	}

	private BudgetDTO getExternalBudget(boolean withUUID) {
		List<BudgetItemDTO> budgetItems = Collections.<BudgetItemDTO>emptyList();
		UUID identifier = withUUID ? UUID.randomUUID() : null;
		return new BudgetDTO(identifier, "Presupuesto de prueba", 1000, 2021, new Date(), budgetItems);
	}
}
