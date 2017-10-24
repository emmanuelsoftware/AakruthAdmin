var table;
var modalTable;
var billNum = 0;
var salesJson;
var prdId = 0;
var saleedited = false;
var billedited = false;
var products = [];
/* OnLoad of page load the table withs the bill details and convert to datatable */
/* On Bill Details, load datatable inside modal */
$(document).ready(
		function() {
			TableManageButton.init();
			$.ajax({
				url : "/Aakruth/customer/list",
				success : function(response) {
					var html = '';
					$.each(response, function(index, customer) {
						html += '<option value=' + customer.dealerId + '>'
								+ customer.name + '</option>'
					});
					$('#customerSelect').append(html);
				},
				error : function(response) {
					new PNotify({
						title : 'Failed!',
						text : 'Error while loading Customer List',
						type : 'error',
						styling : 'bootstrap3'
					});
				}
			});
			$.ajax({
				url : "/Aakruth/builder/list",
				success : function(response) {
					//console.log('Builder list: ' + response);
					var html = '';
					$.each(response, function(index, builder) {
						html += '<option value=' + builder.dealerId + '>'
								+ builder.name + '</option>'
					});
					$('#builderSelect').append(html);
				},
				error : function(response) {
					new PNotify({
						title : 'Failed!',
						text : 'Error while loading BuilderList',
						type : 'error',
						styling : 'bootstrap3'
					});
				}
			});
			$('#single_cal1').daterangepicker({
				singleDatePicker : true,
				calender_style : "picker_1"
			});
			/* This JQUERY loads the options to the builder select */
		});
var handleDataTableButton = function() {
	if ($("#datatable-button").length) {
		table = $("#datatable-button")
				.DataTable(
						{
							ajax : "/Aakruth/bill/onLoad",
							responsive : true,
							serverSide : true,
							fixedHeader : true,
							lengthChange: false,
							language : {
								processing : "<img src='assets/images/loading.gif'>"
							},
							processing : true,
							order : [ [ 4, 'desc' ] ],
							columns : [
									{
										data : 'billId'
									},
									{
										data : 'dealer.name'
									},
									{
										data : 'poNum'
									},
									{
										data : 'tax'
									},
									{
										data : 'entryDte'
									},
									{
										data : 'usrTbl.usrnme'
									},
									{
										data : 'Details',
										orderable : false,
										searchable : false,
										render : function(data, type, row) {
											return '<button type="button" name="details" value='
													+ row.billId
													+ ' class="close"><i class="fa fa-info-circle"></i></button>';
										}
									},
									{
										data : 'Edit',
										orderable : false,
										searchable : false,
										render : function(data, type, row) {
											return '<button type="button" name="edit" value='
													+ row.billId
													+ ' class="close"><i class="fa fa-pencil"></i></button>';
										}
									},
									{
										data : 'Delete',
										orderable : false,
										searchable : false,
										render : function(data, type, row) {
											return '<button type="button" name="delete" value='
													+ row.billId
													+ ' class="close"><i class="fa fa-close"></i></button>';
										}
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
$('#datatable-button tbody')
		.on(
				'click',
				'button',
				function() {
					var data = [];

					var html = '';
					var name = $(this).attr('name').trim();
					var value = $(this).val().trim();
					if (name == "edit") {
						if (billedited == false) {
							$(this).parents('tr').find("td").each(
									function(cell, v) {
										data[cell] = $(this).text();
									});
							html += '<tr role="row" class="odd">';
							html += '<form method="post" id="rowForm">';
							html += '<td>' + data[0] + '</td><td>' + data[1]
									+ '</td>';
							html += '<td><input type="text" id="rowFormpoNum" name="poNum" value="'
									+ data[2] + '"></td>';
							html += '<td><input type="number" id="rowFormtax" name="tax" min="1" max="100000" value="'
									+ data[3] + '"></td>';
							html += '<td>' + data[4] + '</td><td>' + data[5]
									+ '</td>';
							html += '<td></td>';
							html += '<td><button type="button" name="save" value ='
									+ value
									+ ' class="close"><i class="fa fa-save"></i></button></td><td></td>';
							html += '</form></tr>'
							$(this).parents('tr').replaceWith(html);
							billedited = true;
						} else {
							new PNotify({
								title : 'Not allowed!',
								text : 'Please save the data!',
								type : 'error',
								styling : 'bootstrap3'
							});
						}

					}
					if (name == "details") {
						if (billedited == false) {
							$('#salesModal').modal('show');
							handleDataTableModal(value);
							modalTable.ajax.reload();
							$('#billIdHidden').val(value);
							$('#billId').val(value);
						} else {
							new PNotify({
								title : 'Not allowed!',
								text : 'Please save the data!',
								type : 'error',
								styling : 'bootstrap3'
							});
						}

					}
					if (name == "delete") {
						new PNotify({
							title : 'Confirmation Needed',
							text : 'Are you sure?',
							styling : 'bootstrap3',
							hide : false,
							confirm : {
								confirm : true
							},
							buttons : {
								closer : false,
								sticker : false
							},
							history : {
								history : false
							}
						})
								.get()
								.on(
										'pnotify.confirm',
										function() {
											$
													.ajax({
														url : "/Aakruth/bill/delete",
														data : {
															billId : value
														},
														success : function(
																response) {
															var msg = response;
															if (msg.trim() == "true") {
																new PNotify(
																		{
																			title : 'Success!',
																			text : 'Bill data deleted successfully!',
																			type : 'success',
																			styling : 'bootstrap3'
																		});
																table.ajax
																		.reload();
															} else {
																new PNotify(
																		{
																			title : 'Failed!',
																			text : 'You cannot delete this Bill right now!',
																			type : 'error',
																			styling : 'bootstrap3'
																		});
															}
														},
														error : function(
																response) {
															new PNotify(
																	{
																		title : 'Failed!',
																		text : 'You cannot delete this Bill right now!',
																		type : 'error',
																		styling : 'bootstrap3'
																	});
														}
													});
										}).on('pnotify.cancel', function() {
									new PNotify({
										title : 'Cancelled!',
										text : 'You cancelled the operation!',
										type : 'info',
										styling : 'bootstrap3'
									});
								});

					}
					if (name == "save") {
						$
								.ajax({
									url : "/Aakruth/bill/edit",
									data : {
										billId : value,
										poNum : $('#rowFormpoNum').val(),
										tax : $('#rowFormtax').val()
									},
									success : function(response) {
										var msg = response;
										if (msg.trim() == "true") {
											new PNotify(
													{
														title : 'Success!',
														text : 'Bill data saved successfully!',
														type : 'success',
														styling : 'bootstrap3'
													});
											table.ajax.reload();
										} else {
											new PNotify(
													{
														title : 'Failed!',
														text : 'You cannot edit this Bill right now!',
														type : 'error',
														styling : 'bootstrap3'
													});
										}
									},
									error : function(response) {
										new PNotify(
												{
													title : 'Failed!',
													text : 'You cannot edit this Bill right now!',
													type : 'error',
													styling : 'bootstrap3'
												});
									}
								});
						billedited = false;
					}
				});
$("#addButton").click(function() {
	$('#single_cal1').val("");
	$('#poNum').val("");
	$('#tax').val(0);
	$('#billNewModal').modal('show');
});
$("#saveBillButton").click(function() {
	$.ajax({
		url : "/Aakruth/bill/save",
		data : {
			dealerId : $('#customerSelect').val(),
			entryDte : $('#single_cal1').val(),
			poNum : $('#poNum').val(),
			tax : $('#tax').val()
		},
		cache : false,
		success : function(response) {
			var msg = response;
			if (msg.trim() == "true") {
				new PNotify({
					title : 'Success!',
					text : 'Bill data entered successfully!',
					type : 'success',
					styling : 'bootstrap3'
				});
				table.ajax.reload();
			} else {
				new PNotify({
					title : 'Failed!',
					text : 'You cannot save this bill right now!',
					type : 'error',
					styling : 'bootstrap3'
				});
			}
		},
		error : function(response) {
			new PNotify({
				title : 'Failed!',
				text : 'You cannot save this Bill right now!',
				type : 'error',
				styling : 'bootstrap3'
			});
		}
	});

});

var handleDataTableModal = function(billNum) {
	if ($("#datatable-modal").length) {
		modalTable = $("#datatable-modal")
				.DataTable(
						{
							ajax : {
								url : "/Aakruth/sale/list",
								type : "POST",
								data : {
									billId : billNum
								},
								dataSrc : function(json) {
									$.each(json, function(index, sale) {
										products[index] = sale.prdTbl.prdId;
									});
									//console.log(products);
									return json;
								}
							},
							
							fixedHeader : true,
							pageLength: 5,
							order : [ [ 0, 'desc' ] ],
							columns : [
									{
										data : "salId"
									},
									{
										data : "prdTbl.prdnme"
									},
									{
										data : "prdTbl.dealer.name"
									},
									{
										data : "cnt"
									},
									{
										data : "amt"
									},
									{
										data : 'Edit',
										orderable : false,
										searchable : false,
										render : function(data, type, row) {
											return '<button type="button" name="saleEdit" value='
													+ row.salId
													+ ' class="close"><i class="fa fa-pencil"></i></button>';
										}
									},
									{
										data : 'Delete',
										orderable : false,
										searchable : false,
										render : function(data, type, row) {
											return '<button type="button" name="saleDelete" value='
													+ row.salId
													+ ' class="close"><i class="fa fa-close"></i></button>';
										}
									} ]
						});

	}
};
$('#datatable-modal tbody')
		.on(
				'click',
				'button',
				function() {
					var data = [];

					var html = '';
					var name = $(this).attr('name').trim();
					var value = $(this).val().trim();
					// alert(nam +' '+valu);
					if (name == "saleEdit") {
						if (saleedited == false) {
							$(this).parents('tr').find("td").each(
									function(cell, v) {
										data[cell] = $(this).text();
									});
							html += '<tr role="row" class="odd">';
							html += '<form method="post" id="rowFormSale">';
							html += '<td>' + data[0] + '</td><td>' + data[1]
									+ '</td><td>' + data[2] + '</td>';
							html += '<td><input type="number" id="rowFormCnt" name="cnt" min="1" max="100000" value="'
									+ data[3] + '"></td>';
							html += '<td><input type="number" id="rowFormAmt" name="amt" min="1" max="100000000" value="'
									+ data[4] + '"></td>';
							html += '<td><button type="button" name="saleSave" value ='
									+ value
									+ ' class="close"><i class="fa fa-save"></i></button></td><td></td>';
							html += '</form></tr>'
							$(this).parents('tr').replaceWith(html);
							saleedited = true;
						} else {
							new PNotify({
								title : 'Not allowed!',
								text : 'Please save the data!',
								type : 'error',
								styling : 'bootstrap3'
							});
						}

					}
					if (name == "saleDelete") {
						new PNotify({
							title : 'Confirmation Needed',
							text : 'Are you sure?',
							styling : 'bootstrap3',
							hide : false,
							confirm : {
								confirm : true
							},
							buttons : {
								closer : false,
								sticker : false
							},
							history : {
								history : false
							}
						})
								.get()
								.on(
										'pnotify.confirm',
										function() {
											$
													.ajax({
														url : "/Aakruth/sale/delete",
														data : {
															salId : value
														},
														success : function(
																response) {
															var msg = response;
															if (msg.trim() == "true") {
																new PNotify(
																		{
																			title : 'Success!',
																			text : 'Sale data deleted successfully!',
																			type : 'success',
																			styling : 'bootstrap3'
																		});
																modalTable.ajax
																		.reload();
															} else {
																new PNotify(
																		{
																			title : 'Failed!',
																			text : 'You cannot delete this Sale right now!',
																			type : 'error',
																			styling : 'bootstrap3'
																		});
															}
														},
														error : function(
																response) {
															new PNotify(
																	{
																		title : 'Failed!',
																		text : 'You cannot delete this Sale right now!',
																		type : 'error',
																		styling : 'bootstrap3'
																	});
														}
													});
										}).on('pnotify.cancel', function() {
									new PNotify({
										title : 'Cancelled!',
										text : 'You cancelled the operation!',
										type : 'info',
										styling : 'bootstrap3'
									});
								});

					}
					if (name == "saleSave") {
						$
								.ajax({
									url : "/Aakruth/sale/edit",
									data : {
										salId : value,
										cnt : $('#rowFormCnt').val(),
										amt : $('#rowFormAmt').val()
									},
									success : function(response) {
										var msg = response;
										if (msg.trim() == "true") {
											modalTable.ajax.reload();
											new PNotify(
													{
														title : 'Success!',
														text : 'Sale data saved successfully!',
														type : 'success',
														styling : 'bootstrap3'
													});
										} else {
											new PNotify(
													{
														title : 'Failed!',
														text : msg,
														type : 'error',
														styling : 'bootstrap3'
													});
										}
									},
									error : function(response) {
										new PNotify(
												{
													title : 'Failed!',
													text : 'You cannot edit this Sale right now!',
													type : 'error',
													styling : 'bootstrap3'
												});
									}
								});
						
						saleedited = false;
					}
				});

$("#addSaleButton").click(function() {
	$('#saleAddModal').modal('show');
	$('#productSelect').val('');
	$('#count').val("");
	$('#price').val("");
});
$('#builderSelect').on(
		'change',
		function() {
			$('#productSelect').empty();
			$.ajax({
				url : "/Aakruth/product/list",
				data : {
					bldId : $('#builderSelect').val()
				},
				success : function(response) {
					var html = '';
					$.each(response, function(index, product) {
						html += '<option value=' + product.prdId + '>'
								+ product.prdnme + '</option>'
					});
					$('#productSelect').append(html);
					$('#productSelect').removeAttr('disabled');
				},
				error : function(response) {
					new PNotify({
						title : 'Failed!',
						text : 'Error while loading Product List',
						type : 'error',
						styling : 'bootstrap3'
					});
				}
			});
		});
$("#saveButton").click(function() {
	var productId = $('#productSelect').val();
	var index=-1;
	$.each(products, function(indx, value) {
		if(value == productId){
			index=indx;
		}
	});
	if (index == -1) {
		$.ajax({
			url : "/Aakruth/sale/save",
			data : {
				prdId : productId,
				cnt : $('#count').val(),
				amt : $('#price').val(),
				billId : $('#billIdHidden').val()
			},
			cache : false,
			success : function(response) {
				var msg = response;
				if (msg.trim() == "true") {
					modalTable.ajax.reload();
					new PNotify({
						title : 'Success!',
						text : 'Sale data entered successfully!',
						type : 'success',
						styling : 'bootstrap3'
					});
				} else {
					new PNotify({
						title : 'Failed!',
						text : msg,
						type : 'error',
						styling : 'bootstrap3'
					});
				}
			},
			error : function(response) {
				new PNotify({
					title : 'Failed!',
					text : 'You cannot save this sale right now!',
					type : 'error',
					styling : 'bootstrap3'
				});
			}
		});
	}else{
		new PNotify({
			title : 'Failed!',
			text : 'Bill already have this item!',
			type : 'warning',
			styling : 'bootstrap3'
		});
	}

});
$("#modalClose").click(function() {
	saleedited = false;
	products = [];
	$('#salesModal').modal('hide');
	$('#billIdHidden').val('');
	$('#billId').val('');
	modalTable.destroy();
	$('#salesTableTBody').empty();
});
