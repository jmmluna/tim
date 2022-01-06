function copyValue(from, to) {
	var description = document.getElementById(from); //"description"
	var budgetDescription = document.getElementById(to); //"budgetDescription"
//	
    budgetDescription.value = description.value;
//alert(budgetDescription.value)
}