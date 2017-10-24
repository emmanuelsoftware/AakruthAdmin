<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Aakruth Builder</title>
<!-- Bootstrap -->
<link href="assets/vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link href="assets/vendors/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- iCheck -->
<link href="assets/vendors/iCheck/skins/flat/green.css" rel="stylesheet">
<!-- Datatables -->
<link href="assets/vendors/datatables.net-bs/css/dataTables.bootstrap.min.css"
	rel="stylesheet">
<link
	href="assets/vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css"
	rel="stylesheet">
<link
	href="assets/vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css"
	rel="stylesheet">
<link
	href="assets/vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css"
	rel="stylesheet">
<!-- PNotify -->
<link href="assets/vendors/pnotify/dist/pnotify.css" rel="stylesheet">
<link href="assets/vendors/pnotify/dist/pnotify.buttons.css" rel="stylesheet">
<link href="assets/vendors/pnotify/dist/pnotify.nonblock.css" rel="stylesheet">


<!-- Custom Theme Style -->
<link href="assets/build/css/custom.min.css" rel="stylesheet">
</head>

<body class="nav-md">
	<div class="container body">
		<div class="main_container">
			<div class="col-md-3 left_col">
				<div class="left_col scroll-view">
					<div class="navbar nav_title" style="border: 0;">
						<a href="/Aakruth/home" class="site_title"><i
							class="fa fa-at"></i> <span>Aakruth BioMed!</span></a>
					</div>

					<div class="clearfix"></div>

					<!-- menu profile quick info -->
					<div class="profile">
						<div class="profile_pic">
							<img src="assets/images/user.png" alt="..."
								class="img-circle profile_img">
						</div>
						<div class="profile_info">
							<span>Welcome,</span>
							<h2>${name}</h2>
						</div>
					</div>
					<!-- /menu profile quick info -->

					<br />

					<!-- sidebar menu -->
					<div id="sidebar-menu"
						class="main_menu_side hidden-print main_menu">
						<div class="menu_section">
							<br /> <br /> <br />
							<ul class="nav side-menu">
								<li><a href="/Aakruth/home"><i class="fa fa-home"></i> Home </a></li>
								<li><a href="/Aakruth/sale"><i class="fa fa-dollar"></i> Sales</a></li>
								<sec:authorize access="hasAuthority('ADMIN')">
								<li><a href="/Aakruth/purchase"><i class="fa fa-rupee"></i>Purchase</a></li>
								<li><a href="/Aakruth/inventory"><i class="fa fa-truck"></i>Inventory </a></li>
								<li><a href="/Aakruth/product"><i class="fa fa-cubes"></i>Products </a></li>
								<li><a href="/Aakruth/builder"><i class="fa fa-user"></i>Builder </a></li>
								<li><a href="/Aakruth/customer"><i class="fa fa-users"></i>Customers
								</a></li>
								<li><a href="/Aakruth/user"><i class="fa fa-male"></i>Users </a></li>
								</sec:authorize>
							</ul>
						</div>


					</div>
					<!-- /sidebar menu -->

					<!-- /menu footer buttons -->
				</div>
			</div>

			<!-- top navigation -->
			<div class="top_nav">
				<div class="nav_menu">
					<nav class="" role="navigation">
						<div class="nav toggle">
							<a id="menu_toggle"><i class="fa fa-bars"></i></a>
						</div>

						<ul class="nav navbar-nav navbar-right">
							<li class=""><a href="javascript:;"
								class="user-profile dropdown-toggle" data-toggle="dropdown"
								aria-expanded="false"> <img src="assets/images/user.png"
									alt="">${name}<span class=" fa fa-angle-down"></span>
							</a>
								<ul class="dropdown-menu dropdown-usermenu pull-right">
									<li><a href="javascript:;">Help</a></li>
									<li><form action="/Aakruth/logout" method="post">
											<input type="submit" class="close" value="Sign Out" /> <input
												type="hidden" name="${_csrf.parameterName}"
												value="${_csrf.token}" />
										</form>
								</ul></li>
						</ul>
					</nav>
				</div>
			</div>
			<!-- /top navigation -->

			<!-- page content -->
			<div class="right_col" role="main">
				<div class="">
					<div class="clearfix"></div>
					<div class="row">
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">
								<div class="x_title">
									<h2>Customer Dashboard</h2>
									<ul class="nav navbar-right panel_toolbox">
										<li><a class="collapse-link"><i
												class="fa fa-chevron-up"></i></a></li>
										<li><a class="close-link"><i class="fa fa-close"></i></a>
										</li>
									</ul>
									<div class="clearfix"></div>
								</div>
								<div class="x_content">
									<table id="datatable-button"
										class="table table-striped table-bordered dt-responsive nowrap">
										<colgroup>
											<col style="width: 5%" />
											<col style="width: 15%" />
											<col style="width: 20%" />
											<col style="width: 20%" />
											<col style="width: 20%" />
											<col style="width: 20%" />
										</colgroup>
										<thead id="dshbrdhed">
											<tr>
												<th>Num</th>
												<th>Customer Name</th>
												<th>Point of contact</th>
												<th>Contact number</th>
												<th>Email</th>
												<th>Address</th>
												<th></th>
												<th></th>
											</tr>
										</thead>
										<tbody id="customerTableTBody">
											<!-- Content Load via JQUERY -->
										</tbody>

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

			<!-- footer content -->
			<footer>
				<div class="pull-right">
					Powered by Emmanual solutions and template by Colorlib</a>
				</div>
				<div class="clearfix"></div>
			</footer>
			<!-- /footer content -->
		</div>
	</div>
	<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog"
		aria-hidden="true" id="customerModal">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">X</span>
					</button>
					<h4 class="modal-title" id="myModalLabel2">Customer Details</h4>
				</div>
				<div class="modal-body">
					<div class="container-fluid">
						<div class="" id="form">
							<form action="CustomerServlet" method="get" id="cliForm">
								<div class="form-group">
									<label class="control-label" id="bldlbl"> Customer Name</label>
									<input class="form-control" type="text" required=""
										placeholder="customer Name" minlength="2" autocomplete="on"
										name="bldnme" id="bldnme">
								</div>
								<div class="form-group">
									<label class="control-label" for="No of items" id="clilbl">Point
										of contact </label> <input class="form-control" type="text"
										required="required" placeholder="Point of contact " minlength="3"
										autocomplete="on" name="bldpoc" id="bldpoc">
								</div>
								<div class="form-group">
									<label class="control-label" for="No of items" id="clilbl">Contact
										number </label> <input class="form-control" type="text" required=""
										placeholder="Contact number" autocomplete="on"
										name="bldphnnbr" id="bldphnnbr">
								</div>
								<div class="form-group">
									<label class="control-label" for="No of items" id="clilbl">E-mail
									</label> <input class="form-control" type="text" required=""
										placeholder="E-mail" minlength="1" autocomplete="on"
										name="bldemail" id="bldemail">
								</div>
								<div class="form-group">
									<label class="control-label" for="No of items" id="clilbl">Address
									</label> <textarea
										name="bldadr" id="bldadr"></textarea>
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
	<script src="assets/vendors/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="assets/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- FastClick -->
	<script src="assets/vendors/fastclick/lib/fastclick.js"></script>
	<!-- NProgress -->
	<script src="assets/vendors/nprogress/nprogress.js"></script>
	<!-- Datatables -->
	<script src="assets/vendors/datatables.net/js/jquery.dataTables.min.js"></script>
	<script src="assets/vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
	<script
		src="assets/vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
	<script src="assets/vendors/datatables.net/js/jquery.spring-friendly.min.js"></script>
	<script
		src="assets/vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
	<script src="assets/vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
	<script src="assets/vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
	<script src="assets/vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
	<script
		src="assets/vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>
	<script
		src="assets/vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
	<script
		src="assets/vendors/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
	<script
		src="assets/vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>
	<script
		src="assets/vendors/datatables.net-scroller/js/datatables.scroller.min.js"></script>
	<script src="assets/vendors/jszip/dist/jszip.min.js"></script>
	<script src="assets/vendors/pdfmake/build/pdfmake.min.js"></script>
	<script src="assets/vendors/pdfmake/build/vfs_fonts.js"></script>
	
	<!-- PNotify -->
	<script src="assets/vendors/pnotify/dist/pnotify.js"></script>
	<script src="assets/vendors/pnotify/dist/pnotify.buttons.js"></script>
	<script src="assets/vendors/pnotify/dist/pnotify.nonblock.js"></script>
	<script src="assets/vendors/pnotify/dist/pnotify.animate.js"></script>
	<script src="assets/vendors/pnotify/dist/pnotify.confirm.js"></script>
	
	<!-- Custom Theme Scripts -->
	<script src="assets/build/js/custom.js"></script>

	<!-- Datatables -->
	<script>
	$(document).ready(function() {
		TableManageButton.init();
	});
	var table;
	var edited = false;
	var handleDataTableButton = function() {
		if ($("#datatable-button").length) {
			table = $("#datatable-button")
					.DataTable(
							{
								ajax : "/Aakruth/customer/onLoad",
								serverSide :true,
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
									processing : "<img src='assets/images/loading.gif'>"
								},
								processing : true,
								columns : [
										{
											data : 'dealerId'
									    },
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
											data : 'Edit',
											orderable : false,
											searchable : false,
											defaultContent : '<button type="button" name="edit" value="edit" class="close"><i class="fa fa-pencil"></i></button>'
										},
										{
											data : 'Delete',
											orderable : false,
											searchable : false,
											defaultContent : '<button type="button" name="delete" value="delete" class="close"><i class="fa fa-close"></i></button>'
										}]
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
	/* This method is the listener for each sales edit/save or delete button */
	$('#datatable-button tbody').on('click','button',
					function() {
						var data = [];

						var html = '';
						if ($(this).val().trim() == "edit") {
							if(edited==false){
								$(this).parents('tr').find("td").each(
									function(cell, v) {
										data[cell] = $(this).text();
								});
								html += '<tr role="row" class="odd">';
								html += '<form method="post" id="rowForm">';
								html += '<td>'+data[0]+'</td>';
								html += '<td><input type="text" id="rowFormbldnme" value="'+ data[1] + '"></td>';
								html += '<td><input type="text" id="rowFormpoc" value="'+ data[2] + '"></td>';
								html += '<td><input type="number" id="rowFormphnnbr" min="1" max="100000" value="'+ data[3] + '"></td>';
								html += '<td><input type="text" id="rowFormemail" value="'+ data[4] + '"></td>';
								html += '<td><input type="text" id="rowFormadr"  value="'+ data[5] + '"></td>';
								html += '<td><button type="button" name="save" value ="save"  class="close"><i class="fa fa-save"></i></button></td><td></td>';
								html += '</form></tr>'
								$(this).parents('tr').replaceWith(html);
								edited = true;
							}else{
								new PNotify(
										{
											title : 'Not allowed!',
											text : 'Please save the data!',
											type : 'error',
											styling : 'bootstrap3'
										});
							}

						}
						if ($(this).val().trim() == "delete") {
							$(this).parents('tr').find("td").each(
									function(cell, v) {
										data[cell] = $(this).text();
							});
							new PNotify({
							    title: 'Confirmation Needed',
							    text: 'Are you sure?',
							    styling : 'bootstrap3',
							    hide: false,
							    confirm: {
							        confirm: true
							    },
							    buttons: {
							        closer: false,
							        sticker: false
							    },
							    history: {
							        history: false
							    }
							}).get().on('pnotify.confirm', function() {
								$.ajax({
									url : "/Aakruth/dealer/delete",
									data : {
										bldId : data[0]
									},
									success : function(response) {
										var msg = response;
										if (msg.trim() == "true") {
											new PNotify(
													{
														title : 'Success!',
														text : 'Customer data deleted successfully!',
														type : 'success',
														styling : 'bootstrap3'
													});
											table.ajax.reload();
										} else {
											new PNotify(
													{
														title : 'Failed!',
														text : 'You cannot delete this Customer right now!',
														type : 'error',
														styling : 'bootstrap3'
													});
										}
									},
									error : function(response) {
										new PNotify(
												{
													title : 'Failed!',
													text : 'You cannot delete this Customer right now!',
													type : 'error',
													styling : 'bootstrap3'
												});
									}
								});
							}).on('pnotify.cancel', function() {
								new PNotify(
										{
											title : 'Cancelled!',
											text : 'You cancelled the operation!',
											type : 'info',
											styling : 'bootstrap3'
										});
							});
							
							
						}
						if ($(this).val().trim() == "save") {
							edited = false;
							$(this).parents('tr').find("td").each(
									function(cell, v) {
										data[cell] = $(this).text();
							});

							$.ajax({
								url : "/Aakruth/dealer/edit",
								data : {
									bldId : data[0],
									bldnme   : $('#rowFormbldnme').val(),
									poc   : $('#rowFormpoc').val(),
									phnnbr   : $('#rowFormphnnbr').val(),
									email   : $('#rowFormemail').val(),
									adr   : $('#rowFormadr').val()
								},
								success : function(response) {
									var msg = response;
									if (msg.trim() == "true") {
										new PNotify(
												{
													title : 'Success!',
													text : 'Customer data saved successfully!',
													type : 'success',
													styling : 'bootstrap3'
												});
										table.ajax.reload();
									} else {
										new PNotify(
												{
													title : 'Failed!',
													text : 'You cannot edit this customer right now!',
													type : 'error',
													styling : 'bootstrap3'
												});
									}
								},
								error : function(response) {
									new PNotify(
											{
												title : 'Failed!',
												text : 'You cannot edit this customer right now!',
												type : 'error',
												styling : 'bootstrap3'
											});
								}
							});
						}
		});

$("#addButton").click(function() {
	$('#bldnme').val("");
	$('#bldpoc').val("");
	$('#bldphnnbr').val("");
	$('#bldemail').val("");
	$('#bldadr').val("");
});

$("#saveButton").click(function() {
		$.ajax({
			url : "/Aakruth/customer/save",
			data : {
				bldnme : $('#bldnme').val(),
				poc : $('#bldpoc').val(),
				phnnbr : $('#bldphnnbr').val(),
				email : $('#bldemail').val(),
				adr : $('#bldadr').val()
			},
			success : function(response) {
				var msg = response;
				if (msg.trim() == "true") {
					new PNotify(
							{
								title : 'Success!',
								text : 'Customer data saved successfully!',
								type : 'success',
								styling : 'bootstrap3'
							});
					table.ajax.reload();
					
				} else {
					new PNotify(
							{
								title : 'Failed!',
								text : 'You cannot save this customer right now!',
								type : 'error',
								styling : 'bootstrap3'
							});
				}
			},
			error : function(response) {
				new PNotify(
						{
							title : 'Failed!',
							text : 'You cannot save this customer right now!',
							type : 'error',
							styling : 'bootstrap3'
						});
			}
		});
});
	</script>
</body>
</html>