
$(document).ready(function() {


		const table = $('#invoiceDataTable').DataTable(

		{

			"columnDefs": [
				{
					"targets": [0],
					"visible": false,
					"searchable": false
				},
				{ //customer column
					"targets": [1],
					"visible": true,
					"searchable": true,
					className: "text-left",
					orderable: true
				},
				{ //invoice number column
					"targets": [2],
					"visible": true,
					"searchable": true,
					className: "text-center",
					orderable: true
				},
				{ //description column
					"targets": [3],
					"visible": true,
					"searchable": true,
					className: "text-left",
					orderable: true
				},
				{ //date column
					"targets": [4],
					"visible": true,
					"searchable": true,
					className: "text-center",
					orderable: true
				},
				{ // Total column
					"targets": [5],
					"visible": true,
					"searchable": true,
					className: "text-right",
					orderable: true,
					"render": function(data, type, row) {
						return "<b>" + data + "</b>";
					},
				}
				,
				{//download column
					"targets": [6],
					"visible": true,
					"searchable": false,
					className: "text-center",
					orderable: false
				},
				{//edit column
					"targets": [7],
					"visible": true,
					"searchable": false,
					className: "text-center",
					orderable: false
				},
				{//delete column
					"targets": [8],
					"visible": true,
					"searchable": false,
					className: "text-center",
					orderable: false
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


//
//		table.on('select', (e, dt, type, indexes) => {
//			var id = dt.rows({ selected: true }).data();
//			alert("id: " + id[0][0] + " cliente: " + id[0][1]); 
//						//alert(JSON.stringify(id))
//		})

});


