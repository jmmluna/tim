// Call the dataTables jQuery plugin
$(document).ready(function() {

	$('#dataTable').DataTable(
		{
			select: true,
			"language": {
				"lengthMenu": "Mostrar _MENU_ registros por página",
				"zeroRecords": "No se encontró nada, lo siento ",
				"info": "Total: _TOTAL_ registros.",
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
				}
			}
		}
	);




	//    $('#dataTable tbody').on( 'click', 'tr',  ()=> {
	//	alert("entro en click")
	//        if ( $(this).hasClass('selected') ) {
	//            $(this).removeClass('selected');
	//        }
	//        else {
	//            table.$('tr.selected').removeClass('selected');
	//            $(this).addClass('selected');
	//        }
	//    } );

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