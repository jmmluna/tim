$(document).ready(function() {
	
	const date = document.getElementById('date').value;
	if(date === "")
		document.getElementById('date').valueAsDate = new Date();
	
		
	
});

function copyValueFromEmployee() {
	
	const fromElement = document.getElementById('selectedEmployee');	
	const toPriceElement = document.getElementById('employeePrice');
	const toHoursElement = document.getElementById('hours');
	
	const employeeIdElement = document.getElementById('employeeId');
	
	const values = fromElement.value.split(" | ");
	employeeIdElement.value = values[0];
	toPriceElement.value = values[1];
	toHoursElement.value = "1";				
}

function enableSaveButtonBasedOn() {
	const savedButtonElement = document.getElementById('employeeHourSaveButton');
		
	const selectedEmployeeElement = document.getElementById("selectedEmployee");
	const selectedWorkElement = document.getElementById("selectedWork");
	
	

	savedButtonElement.disabled = (selectedEmployeeElement.value != "" && selectedWorkElement.value != "") ? false : true;
}