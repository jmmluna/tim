package es.jmmluna.tim;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import es.jmmluna.tim.domain.model.Price;
import es.jmmluna.tim.domain.model.budget.Budget;
import es.jmmluna.tim.domain.model.budget.BudgetId;
import es.jmmluna.tim.domain.model.budget.BudgetItem;
import es.jmmluna.tim.domain.model.budget.BudgetItemId;

public class BudgetUnitTest {
	
	 @DisplayName("Given budget with two items, when calculate total cost, then sum is returned")
	    @Test
	    void test0() throws Exception {
	        // given
	        BudgetItem bItem1 = new BudgetItem(BudgetItemId.of(UUID.randomUUID()), "Cajas de bombillas", 2, Price.of(5.0) );
	        BudgetItem bItem2 = new BudgetItem(BudgetItemId.of(UUID.randomUUID()), "Escalon de piedra", 4, Price.of(50.0) );
	        
	        Budget budget = new Budget(BudgetId.of(UUID.randomUUID()), 
					1000, 
					"Presupuesto para reforma de casa calle El Olivo",
					2021,
					new Date(), 
					List.of(bItem1, bItem2) 
					);


	        // when
	        Price totalCost = budget.getTotalCost();

	        // then
	        assertEquals(totalCost, Price.of(210.0), "El coste del presupuesto no es correcto");
	    }
	 
	 @DisplayName("Given order with two line items, when add another line item, then total cost is updated")
	    @Test
	    void test1() throws Exception {
		   // given
	        BudgetItem bItem1 = new BudgetItem(BudgetItemId.of(UUID.randomUUID()), "Cajas de bombillas", 2, Price.of(5.0) );
	        BudgetItem bItem2 = new BudgetItem(BudgetItemId.of(UUID.randomUUID()), "Escalon de piedra", 4, Price.of(50.0) );
	        
	        Budget budget = new Budget(BudgetId.of(UUID.randomUUID()), 
					1000, 
					"Presupuesto para reforma de casa calle El Olivo",
					2021,
					new Date(), 
					List.of(bItem1, bItem2) 
					);


//	        // when
	        
	        BudgetItem bItem3 = new BudgetItem(BudgetItemId.of(UUID.randomUUID()), "Marcos de las ventanas", 2, Price.of(10.0) );
	        budget.add(bItem3);


//	        // then
	        assertEquals(budget.getTotalCost(), Price.of(230.0), "El coste del presupuesto no es correcto");
	 }
}
