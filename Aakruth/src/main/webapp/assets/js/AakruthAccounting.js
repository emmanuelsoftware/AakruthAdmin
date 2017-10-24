var debitTable;
var creditTable;
/* OnLoad of page load the table withs the bill details and convert to datatable */
$(document).ready(
		function() {
			TableManageButton.init();
			$.ajax({
				url : "/Aakruth/debit/listTransaction",
				success : function(response) {
					var html = '';
					$.each(response, function(index, transaction) {
						html += '<option value=' + transaction.tranId + '>'
								+ transaction.detail + '</option>'
					});
					$('#DebitTransactionSelect').append(html);
				},
				error : function(response) {
					new PNotify({
						title : 'Failed!',
						text : 'Error while loading Transaction List',
						type : 'error',
						styling : 'bootstrap3'
					});
				}
			});
			$.ajax({
				url : "/Aakruth/credit/listTransaction",
				success : function(response) {
					var html = '';
					$.each(response, function(index, transaction) {
						html += '<option value=' + transaction.tranId + '>'
								+ transaction.detail + '</option>'
					});
					$('#CreditTransactionSelect').append(html);
				},
				error : function(response) {
					new PNotify({
						title : 'Failed!',
						text : 'Error while loading Transaction List',
						type : 'error',
						styling : 'bootstrap3'
					});
				}
			});
			$.ajax({
				url : "/Aakruth/dealer/list",
				success : function(response) {
					var html = '';
					$.each(response, function(index, dealer) {
						html += '<option value=' + dealer.dealerId + '>'
								+ dealer.name + '</option>'
					});
					$('#DebitDealerSelect').append(html);
					$('#CreditDealerSelect').append(html);
					$('#DealerSelect').append(html);
				},
				error : function(response) {
					new PNotify({
						title : 'Failed!',
						text : 'Error while loading Dealer List',
						type : 'error',
						styling : 'bootstrap3'
					});
				}
			});
			$('#dataRange1').val($('#reportrange').val());
			$('#dataRange2').val($('#reportrange').val());
		});

var handleDataTableButton = function() {
	if ($("#datatable-debit").length) {
		debitTable = $("#datatable-debit")
				.DataTable(
						{
							ajax : {
								contentType : 'application/json',
								url : '/Aakruth/debit/onLoad',
								type : 'POST',
								data : function(d) {
									return JSON.stringify(d);
								}
							},
							serverSide : true,
							responsive : true,
							searching : false,
							lengthChange : false,
							pageLength : 10,
							language : {
								processing : "<img src='assets/images/loading.gif'>"
							},
							processing : true,
							order : [ [ 7, 'desc' ] ],
							columns : [
									{
										data : 'voucherNo'
									},
									{
										data : 'transaction.detail'
									},
									{
										data : 'amount'
									},
									{
										data : 'vat'
									},
									{
										data : 'dealer.name'
									},
									{
										data : 'particular'
									},
									{
										data : 'remarks'
									},
									{
										data : 'strdte'
									},
									{
										data : 'Details',
										orderable : false,
										searchable : false,
										render : function(data, type, row) {
											return '<button type="button" name="debitEdit" value='
													+ row.debId
													+ ' class="close"><i class="fa fa-info-circle"></i></button>';
										}
									} ]
						});
	}
	if ($("#datatable-credit").length) {
		creditTable = $("#datatable-credit")
				.DataTable(
						{
							ajax : {
								contentType : 'application/json',
								url : '/Aakruth/credit/onLoad',
								type : 'POST',
								data : function(d) {
									return JSON.stringify(d);
								}
							},
							serverSide : true,
							responsive : true,
							searching : false,
							lengthChange : false,
							pageLength : 5,
							language : {
								processing : "<img src='assets/images/loading.gif'>"
							},
							processing : true,
							order : [ [ 7, 'desc' ] ],
							columns : [
									{
										data : 'voucherNo'
									},
									{
										data : 'transaction.detail'
									},
									{
										data : 'amount'
									},
									{
										data : 'vat'
									},
									{
										data : 'dealer.name'
									},
									{
										data : 'particulars'
									},
									{
										data : 'remarks'
									},
									{
										data : 'strdte'
									},
									{
										data : 'Details',
										orderable : false,
										searchable : false,
										render : function(data, type, row) {
											return '<button type="button" name="creditEdit" value='
													+ row.credId
													+ ' class="close"><i class="fa fa-info-circle"></i></button>';
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

/*
 * This method is the listener for each details button and load the modal with
 * sales
 */
$('#datatable-debit tbody')
		.on(
				'click',
				'button',
				function() {
					var debId = $(this).val().trim();
					$
							.ajax({
								url : "/Aakruth/debit/search",
								data : {
									debId : debId
								},
								success : function(debit) {
									$('#debId').val(debit.debId);
									$('#deleteDebitButton').val(debit.debId);
									$('#DebitvoucherNo').val(debit.voucherNo);
									$('#DebitTransactionSelect').val(
											debit.transaction.tranId);
									$('#Debitamt').val(debit.amount);
									$('#Debittax').val(debit.vat);
									$('#Debitparticular').val(debit.particular);
									$('#Debitremarks').val(debit.remarks);
									$('#DebitTypeOfPayment').val(
											debit.typeOfPayment);
									$('#DebitDealerSelect').val(
											debit.dealer.dealerId);
									$('#DebitModal').modal('show');
								},
								error : function(response) {
									$('#DebitModal').modal('show');
									new PNotify(
											{
												title : 'Failed!',
												text : 'Unable to retrieve this transaction details right now!',
												type : 'error',
												styling : 'bootstrap3'
											});
								}
							});
				});

$('#datatable-credit tbody')
		.on(
				'click',
				'button',
				function() {
					var credId = $(this).val().trim();
					$
							.ajax({
								url : "/Aakruth/credit/search",
								data : {
									credId : credId
								},
								success : function(credit) {
									// console.log(credit);
									$('#credId').val(credit.credId);
									$('#deleteCreditButton').val(credit.credId);
									$('#CreditvoucherNo').val(credit.voucherNo);
									$('#CreditTransactionSelect').val(
											credit.transaction.tranId);
									$('#Creditamt').val(credit.amount);
									$('#Credittax').val(credit.vat);
									$('#Creditparticular').val(
											credit.particulars);
									$('#Creditremarks').val(credit.remarks);
									$('#CreditTypeOfPayment').val(
											credit.typeOfPayment);
									$('#CreditDealerSelect').val(
											credit.dealer.dealerId);
									$('#CreditModal').modal('show');
								},
								error : function(response) {
									$('#CreditModal').modal('show');
									new PNotify(
											{
												title : 'Failed!',
												text : 'Unable to retrieve this transaction details right now!',
												type : 'error',
												styling : 'bootstrap3'
											});
								}
							});
				});
$("#addDebitButton").click(function() {
	$('#debId').val("");
	$('#DebitvoucherNo').val("");
	$('#Debitamt').val("");
	$('#Debittax').val("");
	$('#Debitparticular').val("");
	$('#Debitremarks').val("");
	$('#DebitModal').modal('show');
});
$("#addCreditButton").click(function() {
	$('#credId').val("");
	$('#CreditvoucherNo').val("");
	$('#Creditamt').val("");
	$('#Credittax').val("");
	$('#Creditparticular').val("");
	$('#Creditremarks').val("");
	$('#CreditModal').modal('show');
});
$("#saveDebitButton").click(function() {
	$.ajax({
		url : "/Aakruth/debit/save",
		data : {
			debId : $('#debId').val(),
			voucherNo : $('#DebitvoucherNo').val(),
			amount : $('#Debitamt').val(),
			vat : $('#Debittax').val(),
			particular : $('#Debitparticular').val(),
			remarks : $('#Debitremarks').val(),
			typeOfPayment : $('#DebitTypeOfPayment').val(),
			transaction : $('#DebitTransactionSelect').val(),
			dealerId : $('#DebitDealerSelect').val()
		},
		success : function(response) {
			var msg = response;
			if (msg.trim() == "true") {
				new PNotify({
					title : 'Success!',
					text : 'Debit data entered successfully!',
					type : 'success',
					styling : 'bootstrap3'
				});
				debitTable.ajax.reload();
			} else {
				new PNotify({
					title : 'Failed!',
					text : 'You cannot save/edit this debit right now!',
					type : 'error',
					styling : 'bootstrap3'
				});
			}
		},
		error : function(response) {
			new PNotify({
				title : 'Failed!',
				text : 'You cannot save/edit this debit right now!',
				type : 'error',
				styling : 'bootstrap3'
			});
		}
	});
});
$("#saveCreditButton").click(function() {
	$.ajax({
		url : "/Aakruth/credit/save",
		data : {
			credId : $('#credId').val(),
			voucherNo : $('#CreditvoucherNo').val(),
			amount : $('#Creditamt').val(),
			vat : $('#Credittax').val(),
			particular : $('#Creditparticular').val(),
			remarks : $('#Creditremarks').val(),
			typeOfPayment : $('#CreditTypeOfPayment').val(),
			transaction : $('#CreditTransactionSelect').val(),
			dealerId : $('#CreditDealerSelect').val()
		},
		success : function(response) {
			var msg = response;
			if (msg.trim() == "true") {
				new PNotify({
					title : 'Success!',
					text : 'Credit data entered successfully!',
					type : 'success',
					styling : 'bootstrap3'
				});
				creditTable.ajax.reload();
			} else {
				new PNotify({
					title : 'Failed!',
					text : 'You cannot save/edit this credit right now!',
					type : 'error',
					styling : 'bootstrap3'
				});
			}
		},
		error : function(response) {
			new PNotify({
				title : 'Failed!',
				text : 'You cannot save/edit this credit right now!',
				type : 'error',
				styling : 'bootstrap3'
			});
		}
	});
});

$('#deleteDebitButton').click(function() {
	var value = $('#deleteDebitButton').val();
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
	}).get().on('pnotify.confirm', function() {
		$.ajax({
			url : "/Aakruth/debit/delete",
			data : {
				debId : value
			},
			success : function(response) {
				var msg = response;
				if (msg.trim() == "true") {
					new PNotify({
						title : 'Success!',
						text : 'Debit deleted successfully!',
						type : 'success',
						styling : 'bootstrap3'
					});
					debitTable.ajax.reload();
				} else {
					new PNotify({
						title : 'Failed!',
						text : 'You cannot delete this debit right now!',
						type : 'error',
						styling : 'bootstrap3'
					});
				}
			},
			error : function(response) {
				new PNotify({
					title : 'Failed!',
					text : 'You cannot delete this debit right now!',
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

});

$('#deleteCreditButton').click(function() {
	var value = $('#deleteCreditButton').val();
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
	}).get().on('pnotify.confirm', function() {
		$.ajax({
			url : "/Aakruth/credit/delete",
			data : {
				credId : value
			},
			success : function(response) {
				var msg = response;
				if (msg.trim() == "true") {
					new PNotify({
						title : 'Success!',
						text : 'Credit deleted successfully!',
						type : 'success',
						styling : 'bootstrap3'
					});
					creditTable.ajax.reload();
				} else {
					new PNotify({
						title : 'Failed!',
						text : 'You cannot delete this credit right now!',
						type : 'error',
						styling : 'bootstrap3'
					});
				}
			},
			error : function(response) {
				new PNotify({
					title : 'Failed!',
					text : 'You cannot delete this credit right now!',
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

});
$('#dateRange')
		.click(
				function() {
					debitTable.destroy();
					creditTable.destroy();
					$('#dealerId1').val($('#DealerSelect').val());
					$('#dealerId2').val($('#DealerSelect').val());
					$('#dataRange1').val($('#reportrange').val());
					$('#dataRange2').val($('#reportrange').val());
					if ($("#datatable-debit").length) {
						debitTable = $("#datatable-debit")
								.DataTable(
										{
											ajax : {
												contentType : 'application/json',
												url : '/Aakruth/debit/searchDate',
												data : {
													dealerId : $(
															'#DealerSelect')
															.val(),
													dateRange : $(
															'#reportrange')
															.val()
												},
												dataSrc : function(json) {
													return json;
												}
											},
											/* serverSide : true, */
											responsive : true,
											searching : false,
											lengthChange : false,
											language : {
												processing : "<img src='assets/images/loading.gif'>"
											},
											processing : true,
											order : [ [ 7, 'desc' ] ],
											columns : [
													{
														data : 'voucherNo'
													},
													{
														data : 'transaction.detail'
													},
													{
														data : 'amount'
													},
													{
														data : 'vat'
													},
													{
														data : 'dealer.name'
													},
													{
														data : 'particular'
													},
													{
														data : 'remarks'
													},
													{
														data : 'strdte'
													},
													{
														data : 'Details',
														orderable : false,
														searchable : false,
														render : function(data,
																type, row) {
															return '<button type="button" name="debitEdit" value='
																	+ row.debId
																	+ ' class="close"><i class="fa fa-info-circle"></i></button>';
														}
													} ]
										});
					}
					if ($("#datatable-credit").length) {
						creditTable = $("#datatable-credit")
								.DataTable(
										{
											ajax : {
												contentType : 'application/json',
												url : '/Aakruth/credit/searchDate',
												data : {
													dealerId : $(
															'#DealerSelect')
															.val(),
													dateRange : $(
															'#reportrange')
															.val()
												},
												dataSrc : function(json) {
													return json;
												}
											},
											responsive : true,
											searching : false,
											lengthChange : false,
											language : {
												processing : "<img src='assets/images/loading.gif'>"
											},
											processing : true,
											order : [ [ 7, 'desc' ] ],
											columns : [
													{
														data : 'voucherNo'
													},
													{
														data : 'transaction.detail'
													},
													{
														data : 'amount'
													},
													{
														data : 'vat'
													},
													{
														data : 'dealer.name'
													},
													{
														data : 'particulars'
													},
													{
														data : 'remarks'
													},
													{
														data : 'strdte'
													},
													{
														data : 'Details',
														orderable : false,
														searchable : false,
														render : function(data,
																type, row) {
															return '<button type="button" name="creditEdit" value='
																	+ row.credId
																	+ ' class="close"><i class="fa fa-info-circle"></i></button>';
														}
													} ]
										});
					}

				});
