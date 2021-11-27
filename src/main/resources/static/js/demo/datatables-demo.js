// Call the dataTables jQuery plugin
$(document).ready(function() {




	var table = $('#dataTable').DataTable(
		{
			//			columns: columns,
			"columnDefs": [
				{
					"targets": [0],
					"visible": false,
					"searchable": false
				}
				, {
					name: "pppp",
					"targets": [3],
					"visible": true,
					"searchable": true,
					className: "text-right",
					render: $.fn.dataTable.render.number(',', '.', 2)
				}
				, {
					"targets": [4],
					"visible": true,
					"searchable": true,
					className: "text-right",
					render: $.fn.dataTable.render.number(',', '.', 2)
				}



			],
			select: true,
			"language": {
				select: {
					//					rows: "%d filas seleccionadas"
					rows: "",
				},
				"lengthMenu": "Mostrar _MENU_ registros por página",
				"zeroRecords": "No se encontró nada, lo siento ",
				//				"info": "Total: _TOTAL_ registros.",
				"info": "Mostrando _START_ a _END_ de _TOTAL_ entradas",
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

	table
		.on('select', (e, dt, type, indexes) => {
			var id = dt.rows({ selected: true }).data();
//			alert(id[0])
		})






});


//{
//    "decimal":        ",",
//    "thousands":      ".",
//    "emptyTable":     "No hay datos disponibles para esta tabla. Es posible que aún no se hayan cargado o que haya habido un error en el servidor. Por favor, prueba de clicar de nuevo en el link que abre la tabla después de unos segundos.",
//    "info":           "Mostrando _START_ a _END_ de _TOTAL_ entradas",
//    "infoEmpty":      "Mostrando 0 a 0 de 0 entradas",
//    "infoFiltered":   "filtrado de un total de _MAX_ registros",
//    "infoPostFix":    "",
//    "lengthMenu":     "Mostrar _MENU_ registros",
//    "loadingRecords": "Cargando...",
//    "processing":     "Procesando...",
//    "search":         "Busqueda:",
//    "zeroRecords":    "No se ha encontrado ningún registo igual",
//    "paginate": {
//        "first":      "Primero",
//        "last":       "Último",
//        "next":       "Siguiente",
//        "previous":   "Anterior"
//    },
//    "aria": {
//        "sortAscending":  ": Activar para ordenar ascedentemente",
//        "sortDescending": ": Activar para ordenar descendentemente"
//    }







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