var table;
var modalTable;
var billNum = 0;
var salesJson;
var prdId = 0;
var saleedited = false;
var billedited = false;
var products = [];
var productsDetails;
$(document)
		.ready(
				function() {
					table = $("#datatable-button")
							.DataTable(
									{
										ajax : "saleBill/findAll",
										responsive : true,
										serverSide : true,
										fixedHeader : true,
										lengthChange : false,
										language : {
											processing : "<img src='static/images/loading.gif'>"
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
													data : 'entryDte'
												},
												{
													data : 'user.usrnme'
												},
												{
													data : 'Details',
													orderable : false,
													searchable : false,
													render : function(data,
															type, row) {
														return '<button type="button" name="details" value='
																+ row.billId
																+ ' class="close"><i class="fa fa-info-circle"></i></button>';
													}
												} ]
									});

					$.ajax({
						url : "builder/list",
						success : function(response) {
							var html = '';
							$.each(response, function(index, builder) {
								html += '<option value=' + builder.dealerId
										+ '>' + builder.name + '</option>'
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
				});

$('#datatable-button').on('click', 'button', function() {
	var value = $(this).val().trim();
	console.log(value);
	$('#salesModal').modal('show');
	handleDataTableModal(value);
	modalTable.ajax.reload();
	$('#billIdHidden').val(value);
	$('#billId').val(value);

});

$('#datatable-modal').on('click','button',function() {
					var value = $(this).val().trim();
					var buttonType = $(this).attr('id');
					if (buttonType == 'edit') {

						$.ajax({
							url : "sale/search",
							data : {
								salId : value
							},
							success : function(sale) {
								$('#productSelect').val(sale.product.prdId);
								$('#count').val(sale.cnt);
								$('#price').val(sale.saleAmt);
								$('#discount').val(sale.discount);
								$('#saleAddModal').modal('show');
							},
							error : function(response) {
								init_PNotify('Failed', 'Sale Search failed',
										'error');
							}
						});
					} else if (buttonType == 'delete') {
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
														url : "sale/delete",
														data : {
															salId : value
														},
														success : function(
																response) {
															var msg = response;
															if (msg.trim() == "true") {
																init_PNotify(
																		'Success!',
																		'Sale deleted successfully!',
																		'success');
																table.ajax
																		.reload();
															} else {
																init_PNotify(
																		'Failed',
																		'Operation failed',
																		'error');
															}
														},
														error : function(
																response) {
															init_PNotify(
																	'Failed',
																	'Operation failed',
																	'error');
														}
													});
										})
								.on(
										'pnotify.cancel',
										function() {
											init_PNotify(
													'Cancelled!',
													'You cancelled the operation!',
													'info');
										});
					} else {

						init_PNotify('Failed', 'Wrong operation', 'error');
					}

				});
var handleDataTableModal = function(billNum) {
	if ($("#datatable-modal").length) {
		modalTable = $("#datatable-modal")
				.DataTable(
						{
							ajax : {
								url : "sale/list",
								data : {
									billId : billNum
								},
								dataSrc : function(sales) {
									var TotalData = 0.0;
									var DiscountedAmountData = 0.0;
									var cgstData = 0.0;
									var sgstData = 0.0
									var IgstData = 0.0;
									var GrandTotalData = 0.0;
									var afterDiscount = 0.0;
									$('#TotalData').text();
									$('#DiscountedAmountData').text();
									$('#cgstData').text();
									$('#sgstData').text();
									$('#IgstData').text();
									$('#GrandTotalData').text();

									$
											.each(
													sales,
													function(index, sale) {
														products[index] = sale.product.prdId;
														TotalData = TotalData
																+ sale.saleAmt
																* sale.cnt;
														afterDiscount = (sale.saleAmt - ((sale.saleAmt * sale.discount) / 100))
																* sale.cnt;
														DiscountedAmountData = DiscountedAmountData
																+ afterDiscount;
														cgstData = cgstData
																+ (afterDiscount * sale.cgst)
																/ 100;
														sgstData = sgstData
																+ (afterDiscount * sale.sgst)
																/ 100;
														IgstData = IgstData
																+ (afterDiscount * sale.igst)
																/ 100;
														GrandTotalData = GrandTotalData
																+ (afterDiscount * (sale.cgst + sale.sgst))
																/ 100;
													});
									$('#TotalData').text(TotalData);
									$('#DiscountedAmountData').text(
											DiscountedAmountData);
									$('#cgstData').text(cgstData);
									$('#sgstData').text(sgstData);
									$('#IgstData').text(IgstData);
									$('#GrandTotalData').text(GrandTotalData);
									return sales;
								}
							},

							fixedHeader : true,
							pageLength : 5,
							order : [ [ 0, 'desc' ] ],
							lengthChange : false,
							searching : false,
							columns : [
									{
										data : "product.prdnme"
									},
									{
										data : "product.dealer.name"
									},
									{
										data : "cnt"
									},
									{
										data : "saleAmt"
									},
									{
										data : 'Details',
										orderable : false,
										searchable : false,
										render : function(data, type, row) {
											return '<button type="button" name="delete" id="delete" value='
													+ row.salId
													+ ' class="close"><i class="fa fa-close"></i></button>'
													+ '<button type="button" name="edit" id="edit" value='
													+ row.salId
													+ ' class="close"><i class="fa fa-pencil"></i></button>';
										}
									} ]
						});

	}

	$.ajax({
		url : "bill/findOne",
		data : {
			billId : billNum
		},
		success : function(bill) {
			$('#search-box').val(bill.dealer.name);
			$('#single_cal1').val(bill.entryDte);
			$('#poNum').val(bill.poNum);
			$('#taxSelect').val(bill.taxType);
		},
		error : function(response) {
			init_PNotify('Failed', 'Bill search failed', 'error');
		}
	});
};

$("#addButton").click(function() {
	$('#search-box').val();
	$('#single_cal1').val();
	$('#poNum').val();
	$('#taxSelect').val('S');
	$('#salesModal').modal('show');
	$('#billIdHidden').val();
	$('#billId').val();
});

$("#addSaleButton").click(function() {
	$('#saleAddModal').modal('show');
	$('#productSelect').val('');
	$('#count').val("");
	$('#price').val("");
	$('#discount').val("");
});

var saveBill = function(billNum) {
	$.ajax({
		url : "bill/save",
		data : {
			billId : billNum,
			dealerId : $('#customerSelect').val(),
			entryDte : $('#single_cal1').val(),
			poNum : $('#poNum').val(),
			taxType : $('#taxSelect').val()
		},
		cache : false,
		success : function(response) {
			var msg = response;
			if (msg.trim() == "true") {
				init_PNotify('Success!', 'Bill data entered successfully!',
						'success');
				table.ajax.reload();
			} else {
				init_PNotify('Failed', 'You cannot save this bill right now!',
						'error');
			}
		},
		error : function(response) {
			init_PNotify('Failed', 'You cannot save this bill right now!',
					'error');
		}
	});

}

$("#modalSave").click(function() {
	saleedited = false;
	products = [];
	saveBill($('#billIdHidden').val());
	$('#salesModal').modal('hide');
	$('#billIdHidden').val('');
	$('#billId').val('');
	modalTable.destroy();
});

$("#modalClose").click(function() {
	saleedited = false;
	products = [];
	$('#salesModal').modal('hide');
	$('#billIdHidden').val('');
	$('#billId').val('');
	modalTable.destroy();
});

$("#modalDelete").click(
		function() {
			$.ajax({
				url : "bill/delete",
				data : {
					billId : $('#billIdHidden').val()
				},
				cache : false,
				success : function(response) {
					var msg = response;
					if (msg.trim() == "true") {
						init_PNotify('Success!',
								'Bill data deleted successfully!', 'success');
						table.ajax.reload();
					} else {
						init_PNotify('Failed',
								'You cannot delete this bill right now!',
								'error');
					}
				},
				error : function(response) {
					init_PNotify('Failed',
							'You cannot delete this bill right now!', 'error');
				}
			});
			saleedited = false;
			products = [];
			$('#salesModal').modal('hide');
			$('#billIdHidden').val('');
			$('#billId').val('');
			modalTable.destroy();
		});

var delay = (function() {
	var timer = 0;
	return function(callback, ms) {
		clearTimeout(timer);
		timer = setTimeout(callback, ms);
	};
})();

$("#search-box").keyup(
		function() {
			delay(function() {
				$('#customerSelect').empty();
				$('#customerSelect').prop('disabled', 'disabled');
				var keyword = $("#search-box").val();
				if (keyword != '') {
					$.ajax({
						url : "customer/search",
						data : {
							value : keyword
						},
						success : function(customers) {
							if (customers.length > 0) {
								var html = '';
								$.each(customers, function(index, customer) {
									html += '<option value='
											+ customer.dealerId + '>'
											+ customer.name + '</option>'
								});
								$('#customerSelect').append(html);
								$('#customerSelect').prop('disabled', false);
							}
						},
						error : function(response) {
							init_PNotify('Failed',
									'Error while loading Customer List!',
									'error');
						}
					});

				} else {
					console.log("Keyword null")
				}
			}, 700);
		});
$("#search-product").keyup(
		function() {
			delay(function() {
				$('#productSelect').empty();
				var keyword = $("#search-product").val();
				if (keyword != '') {
					$.ajax({
						url : "product/search",
						data : {
							value : keyword
						},
						success : function(products) {
							if (products.length > 0) {
								var html = '';
								$.each(products, function(index, product) {
									html += '<option value='
											+ product.prdId + '>'
											+ product.prdnme + '</option>'
								});
								$('#productSelect').append(html);
							}
						},
						error : function(response) {
							init_PNotify('Failed',
									'Error while loading Product List!',
									'error');
						}
					});

				} else {
					console.log("Keyword null")
				}
			}, 700);
		});

$('#builderSelect').on(
		'change',
		function() {
			$('#productSelect').empty();
			$.ajax({
				url : "product/list",
				data : {
					bldId : $('#builderSelect').val()
				},
				success : function(products) {
					productsDetails = products;
					var html = '';
					var count = 0;
					$.each(products, function(index, product) {
						count++;
						html += '<option value=' + product.prdId + '>'
								+ product.prdnme + '</option>'
					});
					$('#productSelect').append(html);
					if (count > 0) {
						$('#productSelect').removeAttr('disabled');
					} else {
						$('#productSelect').prop('disabled', 'disabled');
					}

				},
				error : function(response) {
					init_PNotify('Failed', 'Error while loading Product List!',
							'error');
				}
			});
		});

$('#productSelect').on('change', function() {
	var prdId = $('#productSelect').val();
	$.each(productsDetails, function(index, product) {
		if (prdId == product.prdId) {
			$('#price').val(product.saleAmt);
			$('#discount').val(product.discount);
		}
	});
});

$("#saveButton")
		.click(
				function() {
					var productId = $('#productSelect').val();
					var index = -1;
					$.each(products, function(indx, value) {
						if (value == productId) {
							index = indx;
						}
					});
					if (index == -1) {
						$.ajax({
							url : "sale/save",
							data : {
								prdId : productId,
								cnt : $('#count').val(),
								amt : $('#price').val(),
								discount : $('#discount').val(),
								billId : $('#billIdHidden').val()
							},
							cache : false,
							success : function(response) {
								var msg = response;
								if (msg.trim() == "true") {
									modalTable.ajax.reload();
									init_PNotify('Success!',
											'Sale data entered successfully!',
											'success');
								} else {
									init_PNotify('Failed', msg, 'error');
								}
							},
							error : function(response) {
								init_PNotify('Failed',
										'You cannot save this sale right now!',
										'error');
							}
						});
					} else {
						init_PNotify('Failed', 'Bill already have this item!',
								'error');
					}

				});
