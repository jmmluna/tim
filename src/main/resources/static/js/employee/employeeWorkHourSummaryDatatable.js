
$(document).ready(function() {


//	const table = $('#employeeDataTable').DataTable(
	$('#employeeWorkHourSummaryDataTable').DataTable(
		{

			"columnDefs": [
				{//employee name column
					"targets": [0],
					"visible": true,
					"searchable": true,					
					orderable: true					
				},
				{//work description column
					"targets": [1],
					"visible": true,
					"searchable": true,					
					orderable: true					
				},
				 { // total hours column
					"targets": [2],
					"visible": true,
					"searchable": true,
					className: "text-right",
					render: $.fn.dataTable.render.number(',', '.', 2, '', ' h')
					
				}
				, { // salary column
					"targets": [3],
					"visible": true,
					"searchable": true,
					className: "text-right",
					
					render: function ( data, type, row ) {
										
				        return  `<span style="font-weight:bold;">${row[2]} € <\span>`;
				    }
				},
				{//month column
					"targets": [4],
					"visible": true,
					"searchable": true,					
					orderable: true					
				}
				,
				{//anno column
					"targets": [5],
					"visible": true,
					"searchable": true,
					className: "text-center",					
					orderable: true					
				},{//entries column
					"targets": [6],
					"visible": true,
					"searchable": true,
					className: "text-center",					
					orderable: true					
				}

			],
//			"rowCallback" : function(nRow, aData, iDisplayIndex) {
//            if (aData != null && aData != "") {
//                  $('td', nRow).eq(2).css({"background-color": " #d1fbc1 "});
//            }
//          },
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


//	table.on('select', (e, dt, type, indexes) => {
//		var id = dt.rows({ selected: true }).data();
//		//			alert(id[0])
//	})

});


