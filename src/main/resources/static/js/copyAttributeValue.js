function copyValue(from, to) {
	const description = document.getElementById(from);
	const budgetDescription = document.getElementById(to);
    budgetDescription.value = description.value;
}