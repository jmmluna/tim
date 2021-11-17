// Call the dataTables jQuery plugin
$(document).ready(function() {
	
  $('#dataTable').DataTable(
	
	{		
//		"scrollY": "200px",
//		"scrollCollapse": true,
//		"paging": false,
//		info: false,
		"language": {
			"lengthMenu": "Mostrar _MENU_ registros por página",
			"zeroRecords": "No se encontró nada, lo siento ",
//			"info": "Mostrando página _PAGE_ de _PAGES_",
//			"info" : "Showing _START_ to _END_ of _TOTAL_ entries." ,
			"info" : "Total: _TOTAL_ registros.",
			"infoEmpty": "No hay registros disponibles",
			"infoFiltered": "(filtrado desde _MAX_ total registros)",
			"loadingRecords": "Loading...",
			"processing": "Procesando...",
			"search": "Buscar:",
			"paginate": {
				"first": "Primero",
				"last": "Último",
				"next": "Siguiente",
				"previous": "Anterior"
			},
			"aria": {
				"sortAscending": ": activar para ordenar columna ascendentemente",
				"sortDescending": ": activar para ordenar columna ascendentemente"
			},

		}
	}
);
});


//Configuración
//{		
//		"scrollY": "200px",
//		"scrollCollapse": true,
//		"paging": false,
////		info: false,
//		"language": {
//			"lengthMenu": "Mostrar _MENU_ registros por página",
//			"zeroRecords": "No se encontró nada, lo siento ",
////			"info": "Mostrando página _PAGE_ de _PAGES_",
////			"info" : "Showing _START_ to _END_ of _TOTAL_ entries." ,
//			"info" : "Total: _TOTAL_ registros.",
//			"infoEmpty": "No hay registros disponibles",
//			"infoFiltered": "(filtrado desde _MAX_ total registros)",
//			"loadingRecords": "Loading...",
//			"processing": "Procesando...",
//			"search": "Buscar:",
//			"paginate": {
//				"first": "Primero",
//				"last": "Último",
//				"next": "Siguiente",
//				"previous": "Anterior"
//			},
//			"aria": {
//				"sortAscending": ": activar para ordenar columna ascendentemente",
//				"sortDescending": ": activar para ordenar columna ascendentemente"
//			},
//
//		}
//	}