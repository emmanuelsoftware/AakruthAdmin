var debitTable;
var creditTable;
/* OnLoad of page load the table withs the bill details and convert to datatable */

$(document).ready(
		function() {
			TableManageButton.init();
		});

var handleDataTableButton = function() {
	if ($("#datatable-debit").length) {
		debitTable = $("#datatable-debit")
				.DataTable(
						{
							ajax : {
								contentType : 'application/json',
								url : '/Aakruth/inventory/onLoad',
								type : 'POST',
								dataSrc : function(d) {
									console.log(d);
									return d;
								}
							},
							responsive : true,
							language : {
								processing : "<img src='assets/images/loading.gif'>"
							},
							processing : true,
						    order: [[ 0, 'desc' ]],
							columns : [
									{
										data : 'prdnme'
									},
									{
										data : 'bldnme'
									},
									{
										data : 'stockOpen'
									},
									{
										data : 'purchaseCnt'
									},
									{
										data : 'saleCnt'
									},
									{
										data : 'damagedCnt'
									},
									{
										data : 'stockClose'
									} ]
						});
	}
};
TableManageButton = function() {
	"use strict";
	return {
		init : function() {
			handleDataTableButton();
		}
	};
}();

$('#dateRange').click(function() {
	debitTable.destroy();
	if ($("#datatable-debit").length) {
		debitTable = $("#datatable-debit")
				.DataTable(
						{
							ajax : {
								contentType : 'application/json',
								url : '/Aakruth/inventory/search',
								data : {
									dateRange : $('#reportrange').val()
								},
								dataSrc : function(d) {
									console.log(d);
									return d;
								}
							},
							responsive : true,
							language : {
								processing : "<img src='assets/images/loading.gif'>"
							},
							processing : true,
							order: [[ 0, 'desc' ]],
							columns : [
								{
									data : 'prdnme'
								},
								{
									data : 'bldnme'
								},
								{
									data : 'stockOpen'
								},
								{
									data : 'purchaseCnt'
								},
								{
									data : 'saleCnt'
								},
								{
									data : 'damagedCnt'
								},
								{
									data : 'stockClose'
								} ]
						});
	}	
});
