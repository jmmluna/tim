
$(document).ready(function() {

	$('#workItemDataTable').DataTable(
		{
			 
		"scrollY":        "200px",
        "scrollCollapse": true,
		"scrollX":        "200px",
        "paging":         false,

			"columnDefs": [
				{//date column
					"targets": [0],
					"visible": true,
					"searchable": true,
					className: "text-center",
					"orderable": true
				},
				{//description column
					"targets": [1],
					"visible": true,
					"searchable": true,
					className: "text-center",
					"orderable": true
				},
				{//quantity column
					"targets": [2],
					"visible": true,
					"searchable": true,
					className: "text-center",
					"orderable": true
				},
				{ // Price column
					"targets": [3],
					"visible": true,
					"searchable": true,
					className: "text-right",
					"orderable": true,
					"render": function(data, type, row) {
						return "<b>" + data + ' <i class="fas fa-euro-sign"></i></b>';
					},
				},

				{ // Total column
					"targets": [4],
					"visible": true,
					"searchable": true,
					className: "text-right",
					"orderable": true,
					"render": function(data, type, row) {
						return "<b>" + data + ' <i class="fas fa-euro-sign"></i></b>';
					},
				},
				{//delete column
					"targets": [5],
					"visible": true,
					"searchable": false,
					className: "text-center",
					"orderable": false
				}
			],
			select: true,
			"language": {
				select: {
					//					rows: "%d filas seleccionadas"
					rows: "",
				},
				"lengthMenu": "Mostrar _MENU_ registros por página",
				"zeroRecords": "No se encontró ningún elemento ",
				//				"info": "Total: _TOTAL_ registros.",
				"info": "Mostrando _START_ a _END_ de _TOTAL_ entradas",
				"infoEmpty": "No hay registros disponibles",
				"infoFiltered": "(filtrado desde _MAX_ registros)",
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


	//	table.on('select', (e, dt, type, indexes) => {
	//		var id = dt.rows({ selected: true }).data();
	//		//			alert(id[0])
	//	})

});


