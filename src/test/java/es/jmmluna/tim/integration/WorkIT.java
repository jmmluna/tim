package es.jmmluna.tim.integration;

import es.jmmluna.tim.application.service.EElementList;
import es.jmmluna.tim.application.service.work.WorkDTO;
import es.jmmluna.tim.application.service.work.WorkItemDTO;
import es.jmmluna.tim.application.service.work.useCase.*;
import es.jmmluna.tim.domain.model.IdentifierNotAllowedException;
import es.jmmluna.tim.infrastructure.TimApplication;
import lombok.extern.slf4j.Slf4j;
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
    @DisplayName("Initialize works")
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
    public void givenWorkWithIdentifier_whenExecute_getCustomerId() {
        // give
        var uuid = UUID.fromString("123e4567-e89b-12d3-a456-556642440000");

        // when
        var workDTO = getWork.execute(uuid);

        // then
        assertEquals(UUID.fromString("123e4567-e89b-12d3-a456-556642440002"), workDTO.getCustomerId());
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
        assertEquals(UUID.fromString("123e4567-e89b-12d3-a456-556642440000"), workDTO.getBudgetId());
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
        //give
        assertEquals(1, this.getActiveWorkCount.execute());
        var workDTO = this.getExternalWork(false);
        assertNull(workDTO.getUuid());

        //when
        var savedWork = createWork.execute(workDTO);

        //then
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
        //given
        var workDTO = getWork.execute(UUID.fromString("123e4567-e89b-12d3-a456-556642440000"));
        assertEquals(workDTO.getDescription(), "Trabajo 1", "No coincide la descripción del trabajo original");
        //when
        workDTO.setDescription("Fachada");
        //then
        var savedWorkDTO = updateWork.execute(workDTO);
        assertEquals(savedWorkDTO.getDescription(), "Fachada", "No coincide la descripción del trabajo modificado");

    }

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
    private WorkDTO getExternalWork(boolean withUUID) {
        var workItems = Collections.<WorkItemDTO>emptyList();
        UUID workId = withUUID ? UUID.randomUUID() : null;
        var customerId = UUID.fromString("123e4567-e89b-12d3-a456-556642440002");
        var budgetId = UUID.fromString("123e4567-e89b-12d3-a456-556642440000");
        return new WorkDTO(workId, budgetId, customerId, "Trabajo de prueba", 0, new Date(), 0.0, workItems);
    }
}
