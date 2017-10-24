<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<!-- Meta, title, CSS, favicons, etc. -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Aakruth Sales</title>

<!-- Bootstrap -->
<link href="assets/vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link href="assets/vendors/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- iCheck -->
<link href="assets/vendors/iCheck/skins/flat/green.css" rel="stylesheet">
<!-- Datatables -->
<link
	href="assets/vendors/datatables.net-bs/css/dataTables.bootstrap.min.css"
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
<link href="assets/vendors/pnotify/dist/pnotify.buttons.css"
	rel="stylesheet">
<link href="assets/vendors/pnotify/dist/pnotify.nonblock.css"
	rel="stylesheet">


<!-- Custom Theme Style -->
<link href="assets/build/css/custom.min.css" rel="stylesheet">
</head>

<body class="nav-md">
	<div class="container body">
		<div class="main_container">
			<div class="col-md-3 left_col">
				<div class="left_col scroll-view toggled">
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
									<h2>Sales Dashboard</h2>
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
										class="table table-striped table-bordered dt-responsive nowrap"
										width="100%">
										<colgroup>
											<col style="width: 5%" />
											<col style="width: 20%" />
											<col style="width: 20%" />
											<col style="width: 5%" />
											<col style="width: 20%" />
											<col style="width: 15%" />
											<col style="width: 5%" />
										</colgroup>
										<thead id="dshbrdhed">
											<tr>
												<th>Bill</th>
												<th>Customer</th>
												<th>Purchase Order</th>
												<th>Tax</th>
												<th>Date of sale</th>
												<th>Sold by</th>
												<th></th>
												<th></th>
												<th></th>
											</tr>
										</thead>
										<tbody id="billTableTBody">
											<!-- Content Load via JQUERY -->
										</tbody>

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
	<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog"
		aria-hidden="true" id="salesModal" data-keyboard="false"
		data-backdrop="static">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">Bill Detail</h4>
				</div>
				<div class="modal-body">
					<div class="container-fluid">
						<div class="x_content">
							<table id="datatable-modal" class="table dt-responsive nowrap">
								<colgroup>
									<col style="width: 5%" />
									<col style="width: 25%" />
									<col style="width: 25%" />
									<col style="width: 25%" />
									<col style="width: 10%" />
									<col style="width: 10%" />
								</colgroup>
								<thead id="dshbrdhed">
									<tr>
										<th>No</th>
										<th>Product</th>
										<th>Builder</th>
										<th>No of items</th>
										<th>Price</th>
										<th></th>
										<th></th>
									</tr>
								</thead>
								<tbody id="salesTableTBody">
									<!-- Content Load via JQUERY -->
								</tbody>
							</table>
							<div class="row">
								<div class="col-md-12">
									<div class="pull-left">
										<form action="/Aakruth/bill/print">
											<input type="hidden" id="billId" name="billId" value="0">
											<button class="btn btn-success btn-lg close" type="submit">
												<i class="glyphicon glyphicon-download-alt fa-2x"></i>
											</button>
										</form>
									</div>
									<div class="pull-right">
										<button class="btn btn-success btn-lg close" type="button"
											id="addSaleButton">
											<i class="fa fa-plus fa-2x"></i>
										</button>
									</div>
								</div>
							</div>


						</div>

					</div>
					<div class="modal-footer">
						<div class="btn-group btn-group-sm" role="group">
							<button type="button" class="btn btn-default" id="modalClose">Close</button>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
	<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog"
		aria-hidden="true" id="saleAddModal">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">X</span>
					</button>
					<h4 class="modal-title" id="myModalLabel2">Sales Details</h4>
				</div>
				<div class="modal-body">
					<div class="container-fluid">
						<input type="hidden" name="billIdHidden" id="billIdHidden" />
						<div class="form-group">
							<label class="control-label" id="clilbl">Builder </label> <select
								class="form-control" id="builderSelect" name="builderSelect">
								<!-- Options load via JQUERY -->
								<option value=""></option>
							</select>
						</div>
						<div class="form-group">
							<label class="control-label" id="clilbl">Product </label> <select
								class="form-control" id="productSelect" name="productSelect"
								disabled="disabled">
								<!-- Options load via JQUERY -->
							</select>
						</div>

						<div class="form-group">
							<label class="control-label" for="No of items" id="clilbl">No
								of items </label> <input class="form-control" type="number"
								required="required" placeholder="no of items" min="1"
								name="count" id="count">
						</div>
						<div class="form-group">
							<label class="control-label" for="No of items" id="clilbl">Price
							</label> <input class="form-control" type="number" required="required"
								placeholder="Price" min="1" name="price" id="price">
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
	<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog"
		aria-hidden="true" id="billNewModal">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">X</span>
					</button>
					<h4 class="modal-title" id="myModalLabel2">Bill Details</h4>
				</div>
				<div class="modal-body">
					<div class="container-fluid">
						<div class="" id="form">
							<form action="PurchaseServlet" method="get" id="purchaseForm">
								<div class="form-group">
									<label class="control-label" id="clilbl">Customer </label> <select
										class="form-control" id="customerSelect" name="customerSelect">
									</select>
								</div>
								<div class="form-group">
									<label class="control-label" for="Date of sale" id="clilbl">Date
										of sale</label>
									<fieldset>
										<div class="control-group">
											<div class="controls">
												<div
													class="col-md-11 xdisplay_inputx form-group has-feedback">
													<input type="text" class="form-control has-feedback-left"
														id="single_cal1" placeholder="Date of sale"
														aria-describedby="inputSuccess2Status3"> <span
														class="fa fa-calendar-o form-control-feedback left"
														aria-hidden="true"></span> <span id="inputSuccess2Status3"
														class="sr-only">(success)</span>
												</div>
											</div>
										</div>
									</fieldset>
								</div>
								<div class="form-group">
									<label class="control-label" for="Purchase Order" id="clilbl">Purchase
										Order</label> <input class="form-control" type="text" required=""
										placeholder="Purchase Order" autocomplete="on" name="poNum"
										id="poNum">
								</div>
								<div class="form-group">
									<label class="control-label" for="Tax" id="clilbl">Tax</label>
									<input class="form-control" type="number" required=""
										placeholder="No of items" min="1" max="1000" autocomplete="on"
										name="tax" id="tax">
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<div class="btn-group btn-group-sm" role="group">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal"
							id="saveBillButton">Save</button>
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
	<script
		src="assets/vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
	<script
		src="assets/vendors/datatables.net/js/jquery.spring-friendly.min.js"></script>
	<script
		src="assets/vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
	<script
		src="assets/vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
	<script
		src="assets/vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
	<script
		src="assets/vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
	<script
		src="assets/vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
	<script
		src="assets/vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>
	<script
		src="assets/vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
	<script
		src="assets/vendors/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
	<script
		src="assets/vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>
	<script
		src="assets/vendors/datatables.net-scroller/js/dataTables.scroller.min.js"></script>
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
	<!-- Main Script -->
	<!-- bootstrap-daterangepicker -->
	<script src="assets/js/moment/moment.min.js"></script>
	<script src="assets/js/datepicker/daterangepicker.js"></script>

	<script src="assets/js/AakruthSales.js"></script>
	<script>
		/*
		 * OnLoad of page load the table withs the bill details and convert to datatable
		 */
	</script>
</body>
</html>
