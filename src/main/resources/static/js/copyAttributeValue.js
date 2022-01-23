function copyValue(from, to) {
	const fromElement = document.getElementById(from);
	const toElement = document.getElementById(to);
    toElement.value = fromElement.value;
}

function copyValueFromConstructionMaterial() {
	const fromElement = document.getElementById('constructionMaterialId');
	const toDescriptionElement = document.getElementById('constructionMaterialDescription'); 
	const toPriceElement = document.getElementById('constructionMaterialPrice');
	const toQuantityElement = document.getElementById('constructionMaterialQuantity');
	const values = fromElement.value.split(" | ");
	toDescriptionElement.value = values[0];
	toPriceElement.value = values[1];
	toQuantityElement.value = "1";	
}

function enabledBasedOn(elementId, dependentElementId) {
	const  dependentElement = document.getElementById(dependentElementId);	
	const  element = document.getElementById(elementId);
	
	element.disabled = dependentElement.value === ""?true:false; 	
}