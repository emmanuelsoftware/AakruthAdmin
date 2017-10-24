<!-- page content -->
<div class="right_col" role="main">
	<div class="">
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_title">
						<h2>Product Details</h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<table id="datatable"
							class="table table-striped table-bordered dt-responsive nowrap"
							style="cursor: pointer; width: 100%">
							<thead>
								<tr>
									<th>Name</th>
									<th>Builder</th>
									<th>Purchase Amount</th>
									<th>Sale Amount</th>
									<th>Warranty</th>
									<th>HSN Code</th>
									<th>SGST</th>
									<th>IGST</th>
									<th>CGST</th>
									<th>Other Tax</th>
									<th>Discount</th>
									<th></th>
								</tr>
							</thead>
						</table>
						<div class="btn-group btn-group-sm" role="group">
							<button class="btn btn-success" type="button" id="addButton">Add</button>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
</div>
<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog"
	aria-hidden="true" id="Modal">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">X</span>
				</button>
				<h4 class="modal-title" id="myModalLabel2">Product Details</h4>
			</div>
			<div class="modal-body">
				<div class="container-fluid">
					<div class="" id="form">
						<form method="get" id="prdForm">
							<input type="hidden" name="prdId" id="prdId">
							<div class="form-group">
								<label class="control-label" id="prdlbl"> Name</label> <input
									class="form-control" type="text" required="required"
									placeholder="Product Name" name="prdnme" id="prdnme">
							</div>
							<div class="form-group">
								<label class="control-label" id="clilbl">Builder </label> <select
									class="form-control" id="buildSelect" name="buildSelect">
									<option value=0></option>
								</select>
							</div>
							<div class="form-group">
								<label class="control-label" id="prdlbl"> HSN Code</label> <input
									class="form-control" type="text" required="required"
									placeholder="HSN Code" name="hsnCode" id="hsnCode">
							</div>

							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label" for="No of items" id="clilbl">Purchase
											Amount </label> <input class="form-control" type="number"
											required="required" placeholder="Buying price" min="0"
											name="purchaseAmount" id="purchaseAmount">
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label" for="No of items" id="clilbl">Sale<br>
											Amount
										</label> <input class="form-control" type="number" required="required"
											placeholder="Selling price" min="0" name="saleAmount"
											id="saleAmount">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label" for="No of items" id="clilbl">Warranty
										</label> <input class="form-control" type="number" required="required"
											placeholder="Warranty Period" min="0" name="warranty"
											id="warranty">
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label" for="No of items" id="clilbl">Discount
										</label> <input class="form-control" type="number" required="required"
											placeholder="Discount" min="0" name="discount" id="discount">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label" for="No of items" id="clilbl">SGST
										</label> <input class="form-control" type="number" required="required"
											placeholder="State GST" min="0" name="sgst" id="sgst">
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label" for="No of items" id="clilbl">CGST
										</label> <input class="form-control" type="number" required="required"
											placeholder="Central GST" min="0" name="cgst" id="cgst">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label" for="No of items" id="clilbl">IGST
										</label> <input class="form-control" type="number" required="required"
											placeholder="Integrated GST" min="0" name="igst" id="igst">
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label" for="No of items" id="clilbl">Other
											tax </label> <input class="form-control" type="number"
											required="required" placeholder="Other taxs" min="0"
											name="otherTax" id="otherTax">
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<div class="btn-group btn-group-sm" role="group">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						id="saveButton">Save</button>
				</div>
			</div>

		</div>
	</div>
</div>
<script src="static/vendors/jquery/dist/jquery.min.js"></script>
<script>
	$(document).ready(
			function() {
				init_DataTables();
				$.ajax({
					url : "builder/list",
					success : function(response) {
						var html = '';
						$.each(response, function(index, builder) {
							html += '<option value=' + builder.dealerId + '>'
									+ builder.name + '</option>'
						});
						$('#buildSelect').append(html);
					},
					error : function(response) {
						init_PNotify('Failed', 'Builder load failed', 'error');
					}
				});
			});
	var table;
	function init_DataTables() {
		table = $('#datatable')
				.DataTable(
						{
							ajax : "product/findAll",
							serverSide : true,
							dom : "Bfrtip",
							buttons : [ {
								extend : "copy",
								className : "btn-sm"
							}, {
								extend : "csv",
								className : "btn-sm"
							}, {
								extend : "excel",
								className : "btn-sm"
							}, {
								extend : "pdfHtml5",
								className : "btn-sm"
							}, {
								extend : "print",
								className : "btn-sm"
							} ],
							responsive : true,
							fixedHeader : true,
							language : {
								processing : "<img src='static/images/loading.gif'>"
							},
							processing : true,
							columns : [
									{
										data : 'prdnme'
									},
									{
										data : 'dealer.name'
									},
									{
										data : 'purAmt'
									},
									{
										data : 'saleAmt'
									},
									{
										data : 'warranty'
									},
									{
										data : 'hsnCode'
									},
									{
										data : 'sgst'
									},
									{
										data : 'igst'
									},
									{
										data : 'cgst'
									},
									{
										data : 'otherTax'
									},
									{
										data : 'discount'
									},
									{
										data : '',
										orderable : false,
										searchable : false,
										render : function(data, type, row) {
											return ' <button type="button" name="delete" id="delete" value='
													+ row.prdId
													+ ' class="close"><i class="fa fa-close"></i></button>'
													+ '<button type="button" name="edit" id="edit" value='
													+ row.prdId
													+ ' class="close"><i class="fa fa-pencil"></i></button>';
										}
									} ]

						});
	}

	$('#datatable')
			.on(
					'click',
					'button',
					function() {
						var prdid = $(this).val().trim();
						var buttonType = $(this).attr('id');
						if (buttonType == 'edit') {
							$.ajax({
								url : "product/search",
								data : {
									prdId : prdid
								},
								success : function(product) {
									$('#prdId').val(product.prdId);
									$('#buildSelect').val(
											product.dealer.dealerId);
									$('#prdnme').val(product.prdnme);
									$('#purchaseAmount').val(product.purAmt);
									$('#saleAmount').val(product.saleAmt);
									$('#warranty').val(product.warranty);
									$('#sgst').val(product.sgst);
									$('#igst').val(product.igst);
									$('#cgst').val(product.cgst);
									$('#otherTax').val(product.otherTax);
									$('#discount').val(product.discount);
									$('#hsnCode').val(product.hsnCode);
									$('#Modal').modal('show');
								},
								error : function(response) {
									init_PNotify('Failed',
											'Product Search failed', 'error');
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
															url : "product/delete",
															data : {
																prdId : prdid
															},
															success : function(
																	response) {
																var msg = response;
																if (msg.trim() == "true") {
																	init_PNotify(
																			'Success!',
																			'Product deleted successfully!',
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

	$("#addButton").click(function() {
		$('#prdId').val(0);
		$('#buildSelect').val(0);
		$('#prdnme').val("");
		$('#purchaseAmount').val(0);
		$('#saleAmount').val(0);
		$('#warranty').val(0);
		$('#sgst').val(0);
		$('#igst').val(0);
		$('#cgst').val(0);
		$('#otherTax').val(0);
		$('#discount').val(0);
		$('#hsnCode').val("");
		$('#Modal').modal('show');
	});
	$("#saveButton")
			.click(
					function() {
						$
								.ajax({
									url : "product/save",
									data : {
										prdId : $('#prdId').val(),
										dealerId : $('#buildSelect').val(),
										prdnme : $('#prdnme').val(),
										purAmt : $('#purchaseAmount').val(),
										saleAmt : $('#saleAmount').val(),
										warranty : $('#warranty').val(),
										sgst : $('#sgst').val(),
										igst : $('#igst').val(),
										cgst : $('#cgst').val(),
										otherTax : $('#otherTax').val(),
										discount : $('#discount').val(),
										hsnCode : $('#hsnCode').val()
									},
									success : function(response) {
										var msg = response;
										if (msg.trim() == "true") {
											init_PNotify(
													'Success!',
													'Product data entered successfully!',
													'success');
											table.ajax.reload();

										} else {
											init_PNotify(
													'Failed',
													'You cannot save/edit this product right now!',
													'error');
										}
									},
									error : function(response) {
										init_PNotify(
												'Failed',
												'You cannot save/edit this product right now!',
												'error');
									}
								});
					});
</script>