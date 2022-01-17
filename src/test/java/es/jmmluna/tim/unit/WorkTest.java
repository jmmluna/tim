package es.jmmluna.tim.unit;

import es.jmmluna.tim.domain.model.Price;
import es.jmmluna.tim.domain.model.budget.BudgetId;
import es.jmmluna.tim.domain.model.customer.CustomerId;
import es.jmmluna.tim.domain.model.work.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkTest {
	private static final String WORK_DESCRIPTION = "Trabajo para reforma de casa calle El Olivo";
	private static final String WORK_ITEM1 = "Cajas de bombillas";
	private static final String WORK_ITEM2 = "Escalon de piedra";

	@DisplayName("Given work with two items, when calculate total cost, then sum is returned")
	@Test
	void testCalculateWorkCost() throws Exception {
		// given
		var workItems = getWorkItems();

		var work = new Work(WorkId.of(UUID.randomUUID()), BudgetId.of(UUID.randomUUID()), CustomerId.of(UUID.randomUUID()),
				WORK_DESCRIPTION, WorkStatus.of(0), new Date(), workItems);

		// when
		Price totalCost = work.getTotalCost();

		// then
		assertEquals(totalCost, Price.of(210.0), "El coste del trabajo no es correcto");
	}

	@DisplayName("Given work with two line items, when add another line item, then total cost is updated")
	@Test
	void testAddWorkItem() throws Exception {
		// given
		var workItems = getWorkItems();

		var work = new Work(WorkId.of(UUID.randomUUID()), BudgetId.of(UUID.randomUUID()), CustomerId.of(UUID.randomUUID()),
				WORK_DESCRIPTION, WorkStatus.of(0), new Date(), workItems);

		// when
		var workItem = new WorkItem(WorkItemId.of(UUID.randomUUID()), "Marcos de las ventanas", 2,
				Price.of(10.0));
		work.add(workItem);

		// then
		assertEquals(work.getTotalCost(), Price.of(230.0), "El coste del trabajo no es correcto");
	}

	@DisplayName("Given work with two line items, when remove one item, then total cost is updated")
	@Test
	void testRemoveWorkItem() throws Exception {
		// given
		var workItems = getWorkItems();

		var work = new Work(WorkId.of(UUID.randomUUID()), BudgetId.of(UUID.randomUUID()), CustomerId.of(UUID.randomUUID()),
				WORK_DESCRIPTION, WorkStatus.of(0), new Date(), workItems);

		// when
		work.remove(workItems.get(1));

		// then
		assertEquals(work.getTotalCost(), Price.of(10.0), "El coste del trabajo no es correcto");

	}

	private List<WorkItem> getWorkItems() {
		var wItem1 = new WorkItem(WorkItemId.of(UUID.randomUUID()), WORK_ITEM1 , 2, Price.of(5.0));
		var wItem2 = new WorkItem(WorkItemId.of(UUID.randomUUID()), WORK_ITEM2 , 4, Price.of(50.0));

		return List.of(wItem1, wItem2);
	}

}
