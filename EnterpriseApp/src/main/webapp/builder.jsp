
<!-- page content -->
<div class="right_col" role="main">
	<div class="">
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_title">
						<h2>Builder Details</h2>
						<ul class="nav navbar-right panel_toolbox">
							<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
							<li><a class="close-link"><i class="fa fa-close"></i></a></li>
						</ul>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<table id="datatable"
							class="table table-striped table-bordered dt-responsive nowrap"
							style="cursor: pointer; width: 100%">
							<thead id="dshbrdhed">
								<tr>
									<th>Builder Name</th>
									<th>Point of contact</th>
									<th>Contact number</th>
									<th>Email</th>
									<th>Address</th>
									<th>GSTIN number</th>
									<th></th>
								</tr>
							</thead>
						</table>
						<div class="btn-group btn-group-sm" role="group">
							<button class="btn btn-success" type="button" id="addButton"
								data-toggle="modal" data-target=".bs-example-modal-sm">Add</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- /page content -->

<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog"
	aria-hidden="true" id="Modal">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">X</span>
				</button>
				<h4 class="modal-title" id="myModalLabel2">Builder Details</h4>
			</div>
			<div class="modal-body">
				<div class="container-fluid">
					<div class="" id="form">
						<form method="get" id="bldForm">
							<input type="hidden" name="dealerId" id="dealerId">
							<div class="form-group">
								<label class="control-label" id="bldlbl"> Builder Name</label> <input
									class="form-control" type="text" required=""
									placeholder="Builder Name" minlength="2" autocomplete="on"
									name="bldnme" id="bldnme">
							</div>
							<div class="form-group">
								<label class="control-label" for="No of items" id="clilbl">Point
									of contact </label> <input class="form-control" type="text"
									required="required" placeholder="Point of contact "
									minlength="3" autocomplete="on" name="bldpoc" id="bldpoc">
							</div>
							<div class="form-group">
								<label class="control-label" for="No of items" id="clilbl">Contact
									number </label> <input class="form-control" type="text" required=""
									placeholder="Contact number" autocomplete="on" name="bldphnnbr"
									id="bldphnnbr">
							</div>
							<div class="form-group">
								<label class="control-label" for="No of items" id="clilbl">E-mail
								</label> <input class="form-control" type="text" required=""
									placeholder="E-mail" minlength="1" autocomplete="on"
									name="bldemail" id="bldemail">
							</div>
							<div class="form-group">
								<label class="control-label" for="No of items" id="clilbl">Address
								</label>
								<textarea name="bldadr" id="bldadr"></textarea>
							</div>
							<div class="form-group">
								<label class="control-label" id="prdlbl"> GSTIN Number</label> <input
									class="form-control" type="text" required="required"
									placeholder="GSTIN Number" name="bldgstin" id="bldgstin">
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
<!-- jQuery -->
<script src="static/vendors/jquery/dist/jquery.min.js"></script>

<!-- Datatables -->
<script>
	$(document).ready(function() {
		init_DataTables();
	});
	var table;
	function init_DataTables() {
		table = $('#datatable')
				.DataTable(
						{
							ajax : "builder/findAll",
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
										data : 'name'
									},
									{
										data : 'poc'
									},
									{
										data : 'phnnbr'
									},
									{
										data : 'email'
									},
									{
										data : 'adr'
									},
									{
										data : 'gstin'
									},
									{
										data : '',
										orderable : false,
										searchable : false,
										render : function(data, type, row) {
											return ' <button type="button" name="delete" id="delete" value='
														+ row.dealerId
														+ ' class="close"><i class="fa fa-close"></i></button>'
													+ '<button type="button" name="edit" id="edit" value='
														+ row.dealerId
														+ ' class="close"><i class="fa fa-pencil"></i></button>';
										}
									} ]
						});
	}
	/* This method is the listener for each sales edit/save or delete button */

	$('#datatable')
			.on(
					'click',
					'button',
					function() {
						var dealerId = $(this).val().trim();
						var buttonType = $(this).attr('id');
						if (buttonType == 'edit') {
							$.ajax({
								url : "dealer/search",
								data : {
									dealerId : dealerId
								},
								success : function(builder) {
									$('#dealerId').val(builder.dealerId);
									$('#bldnme').val(builder.name);
									$('#bldpoc').val(builder.poc);
									$('#bldphnnbr').val(builder.phnnbr);
									$('#bldemail').val(builder.email);
									$('#bldadr').val(builder.adr);
									$('#bldgstin').val(builder.gstin);
									$('#Modal').modal('show');
								},
								error : function(response) {
									init_PNotify('Failed',
											'Builder Search failed', 'error');
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
															url : "dealer/delete",
															data : {
																dealerId : dealerId
															},
															success : function(
																	response) {
																var msg = response;
																if (msg.trim() == "true") {
																	init_PNotify(
																			'Success!',
																			'Builder deleted successfully!',
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
		$('#dealerId').val(0);
		$('#bldnme').val("");
		$('#bldpoc').val("");
		$('#bldphnnbr').val("");
		$('#bldemail').val("");
		$('#bldadr').val("");
		$('#bldgstin').val(0);
	});

	$("#saveButton")
			.click(
					function() {
						$
								.ajax({
									url : "builder/save",
									data : {
										dealerId : $('#dealerId').val(),
										bldnme : $('#bldnme').val(),
										poc : $('#bldpoc').val(),
										phnnbr : $('#bldphnnbr').val(),
										email : $('#bldemail').val(),
										adr : $('#bldadr').val(),
										gstin : $('#bldgstin').val()
									},
									success : function(response) {
										var msg = response;
										if (msg.trim() == "true") {
											init_PNotify(
													'Success!',
													'Builder data entered successfully!',
													'success');
											table.ajax.reload();

										} else {
											init_PNotify(
													'Failed',
													'You cannot save/edit this Buider right now!',
													'error');
										}
									},
									error : function(response) {
										init_PNotify(
												'Failed',
												'You cannot save/edit this Buider right now!',
												'error');
									}
								});
					});
</script>