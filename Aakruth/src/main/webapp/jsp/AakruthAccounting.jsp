<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Aakruth Accounting</title>

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

<!-- bootstrap-daterangepicker -->
<link href="assets/vendors/bootstrap-daterangepicker/daterangepicker.css"
	rel="stylesheet">
<link href="assets/css/AakruthLedger.css" rel="stylesheet">	
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
					<div class="row x_title">				
						<div class="col-md-12">
							<div class="pull-right"
								style="background: #fff; cursor: pointer; padding: 5px 10px; border: 1px solid #ccc">
								<i class="glyphicon glyphicon-calendar fa fa-calendar"></i> <input
									type="text" id="reportrange"
									value="December
									30, 2014 - January 28, 2015"/>
									<button id="dateRange"><i class="glyphicon glyphicon-refresh"></i></button>
							</div>
							<div class="pull-right">
									<select
										class="form-control" id="DealerSelect"
										name="DealerSelect">
										<option value="-1" selected="selected">All Dealers and Transactions</option>
										<!-- Options load via JQUERY -->
									</select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6 col-sm-12 col-xs-12">
							<div class="x_panel">
								<div class="x_title">
									<h2>Debits</h2>
									<ul class="nav navbar-right panel_toolbox">
										<li><a class="collapse-link"><i
												class="fa fa-chevron-up"></i></a></li>
										<li><a class="close-link"><i class="fa fa-close"></i></a>
										</li>
									</ul>
									<div></div>
									<div class="clearfix"></div>
								</div>
								<div class="x_content">
									<table id="datatable-debit"
										class="table table-striped table-bordered dt-responsive nowrap"
										width="100%">
										<thead id="dshbrdhed">
											<tr>
												<th>Voucher</th>
												<th>Type</th>
												<th>Amount</th>
												<th>VAT%</th>
												<th>Dealer</th>
												<th>Particular</th>
												<th>Remarks</th>
												<th>Date</th>
												<th></th>
											</tr>
										</thead>
										<tbody id="debitTableTBody">
											<!-- Content Load via JQUERY -->
										</tbody>

									</table>
									<div class="row">
										<div class="col-md-6">
											<button class="btn btn-success" type="button"
												id="addDebitButton">Add</button>
										</div>
									</div>

								</div>
							</div>
						</div>
						<div class="col-md-6 col-sm-12 col-xs-12">
							<div class="x_panel">
								<div class="x_title">
									<h2>Credits</h2>
									<ul class="nav navbar-right panel_toolbox">
										<li><a class="collapse-link"><i
												class="fa fa-chevron-up"></i></a></li>
										<li><a class="close-link"><i class="fa fa-close"></i></a>
										</li>
									</ul>
									<div class="clearfix"></div>
								</div>
								<div class="x_content">
									<table id="datatable-credit"
										class="table table-striped table-bordered dt-responsive nowrap"
										width="100%">
										<thead id="dshbrdhed">
											<tr>
												<th>Voucher</th>
												<th>Type</th>
												<th>Amount</th>
												<th>VAT%</th>
												<th>Dealer</th>
												<th>Particular</th>
												<th>Remarks</th>
												<th>Date</th>
												<th></th>
											</tr>
										</thead>
										<tbody id="creditTableTBody">
											<!-- Content Load via JQUERY -->
										</tbody>

									</table>
									<button class="btn btn-success" type="button"
										id="addCreditButton">Add</button>
								</div>
							</div>
						</div>
						                      
					</div>
					<div class="row">
						<div class="col-md-6 col-sm-6 col-xs-6">
                       		 		<form action="/Aakruth/ledger" method="POST" id="ledger">
											<input type="hidden" id="dealerId1" name="dealerId1" value="-1">
											<input type="hidden" id="dataRange1" name="dataRange1">
											<button class="btn btn-primary" type="submit" id="ledgerButton">Print Ledger</button>
									</form>
                        </div>
                        <div class="col-md-6 col-sm-6 col-xs-6">
                         	 		<form action="/Aakruth/statement" method="POST">
											<input type="hidden" id="dealerId2" name="dealerId2" value="-1">
											<input type="hidden" id="dataRange2" name="dataRange2">
											<button type="submit" class="btn btn-success" id="statementButton">Print Statement</button>
									</form>
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
		aria-hidden="true" id="DebitModal" data-keyboard="false"
		data-backdrop="static">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">X—</span>
					</button>
					<h4 class="modal-title" id="myModalLabel2">Debit Details</h4>
				</div>
				<div class="modal-body">
					<div class="container-fluid">
						<div class="row">
							<div class="col-md-4 col-sm-12 col-xs-12">
								<div class="form-group">
									<label class="control-label" id="clilbl">Dealer</label> <select
										class="form-control" id="DebitDealerSelect"
										name="DebitDealerSelect">
										<!-- Options load via JQUERY -->
									</select>
								</div>
							</div>
							<div class="col-md-4 col-sm-12 col-xs-12">
								<div class="form-group">
									<label class="control-label" id="clilbl">Type of
										Transaction </label> <select class="form-control"
										id="DebitTransactionSelect" name="DebitTransactionSelect">
										<!-- Options load via JQUERY -->
									</select>
								</div>
							</div>
							<div class="col-md-4 col-sm-12 col-xs-12">
								<div class="form-group">
									<label class="control-label" id="clilbl">Mode of
										payment</label> <select class="form-control" id="DebitTypeOfPayment"
										name="DebitTypeOfPayment">
										<option value="1">Cash</option>
										<option value="2">Bill</option>
									</select>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4 col-sm-12 col-xs-12">
								<div class="form-group">
									<input type="hidden" id="credId"> <label
										class="control-label" for="Voucher number" id="clilbl">Voucher
										number</label> <input class="form-control" type="text"
										required="required" placeholder="voucher number"
										id="DebitvoucherNo">
								</div>
							</div>
							<div class="col-md-4 col-sm-12 col-xs-12">
								<div class="form-group">
									<label class="control-label" for="Amount" id="clilbl">Amount</label>
									<input class="form-control" type="number" required="required"
										placeholder="amount" min="1" name="Debitamt" id="Debitamt">
								</div>
							</div>
							<div class="col-md-4 col-sm-12 col-xs-12">
								<div class="form-group">
									<label class="control-label" for="Tax" id="clilbl">Tax
										involved</label> <input class="form-control" type="number"
										required="required" placeholder="Tax involved" name="Debittax"
										id="Debittax">
								</div>
							</div>
						</div>
						<div class="row">

							<div class="col-md-6 col-sm-12 col-xs-12">
								<div class="form-group">
									<label class="control-label" for="Particular" id="clilbl">Particulars</label>
									<input class="form-control" type="text" required="required"
										placeholder="Particulars" name="Debitparticular"
										id="Debitparticular">
								</div>
							</div>
							<div class="col-md-6 col-sm-12 col-xs-12">
								<div class="form-group">
									<label class="control-label" for="Tax" id="clilbl">Remarks</label>
									<input class="form-control" type="text" required="required"
										placeholder="Remarks" name="Debitremarks" id="Debitremarks">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<div class="btn-group btn-group-sm" role="group">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						id="deleteDebitButton" value="">Delete</button>
					<button type="button" class="btn btn-success" data-dismiss="modal"
						id="saveDebitButton">Save</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog"
		aria-hidden="true" id="CreditModal" data-keyboard="false"
		data-backdrop="static">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">X—</span>
					</button>
					<h4 class="modal-title" id="myModalLabel2">Credit Details</h4>
				</div>
				<div class="modal-body">
					<div class="container-fluid">
						<div class="row">
							<div class="col-md-4 col-sm-12 col-xs-12">
								<div class="form-group">
									<input type="hidden" id="credId" /> <label
										class="control-label" id="clilbl">Dealer</label> <select
										class="form-control" id="CreditDealerSelect"
										name="CreditDealerSelect">
										<!-- Options load via JQUERY -->
									</select>
								</div>
							</div>
							<div class="col-md-4 col-sm-12 col-xs-12">
								<div class="form-group">
									<label class="control-label" id="clilbl">Type of
										Transaction </label> <select class="form-control"
										id="CreditTransactionSelect" name="CreditTransactionSelect">
										<!-- Options load via JQUERY -->
									</select>
								</div>
							</div>
							<div class="col-md-4 col-sm-12 col-xs-12">
								<div class="form-group">
									<label class="control-label" id="clilbl">Mode of
										payment</label> <select class="form-control" id="CreditTypeOfPayment"
										name="CreditTypeOfPayment">
										<option value="1">Cash</option>
										<option value="2">Bill</option>
									</select>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4 col-sm-12 col-xs-12">
								<div class="form-group">
									<input type="hidden" id="debId"> <label
										class="control-label" for="Voucher number" id="clilbl">Voucher
										number</label> <input class="form-control" type="text"
										required="required" placeholder="voucher number"
										id="CreditvoucherNo">
								</div>
							</div>
							<div class="col-md-4 col-sm-12 col-xs-12">
								<div class="form-group">
									<label class="control-label" for="Amount" id="clilbl">Amount</label>
									<input class="form-control" type="number" required="required"
										placeholder="amount" min="1" name="Creditamt" id="Creditamt">
								</div>
							</div>
							<div class="col-md-4 col-sm-12 col-xs-12">
								<div class="form-group">
									<label class="control-label" for="Tax" id="clilbl">Tax
										involved</label> <input class="form-control" type="number"
										required="required" placeholder="Tax involved"
										name="Credittax" id="Credittax">
								</div>
							</div>
						</div>
						<div class="row">

							<div class="col-md-6 col-sm-12 col-xs-12">
								<div class="form-group">
									<label class="control-label" for="Particular" id="clilbl">Particulars</label>
									<input class="form-control" type="text" required="required"
										placeholder="Particulars" name="Creditparticular"
										id="Creditparticular">
								</div>
							</div>
							<div class="col-md-6 col-sm-12 col-xs-12">
								<div class="form-group">
									<label class="control-label" for="Tax" id="clilbl">Remarks</label>
									<input class="form-control" type="text" required="required"
										placeholder="Remarks" name="Creditremarks" id="Creditremarks">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<div class="btn-group btn-group-sm" role="group">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						id="deleteCreditButton" value="">Delete</button>
					<button type="button" class="btn btn-success" data-dismiss="modal"
						id="saveCreditButton">Save</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog"
		aria-hidden="true" id="LedgerModal"  data-keyboard="false"
		data-backdrop="static">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">X</span>
					</button>
					<h4 class="modal-title" id="myModalLabel2">Ledger details</h4>
				</div>
				<div class="modal-body">
					<div class="container-fluid">
						<div class="" id="form">
							<form action="" method="get" id="cliForm">

								<div class="form-group"><label
										class="control-label" id="clilbl">Dealer</label> <select
										class="form-control" id="DealerSelect"
										name="DealerSelect">
										<!-- Options load via JQUERY -->
									</select>
								</div>
								<div class="form-group">
								<label class="control-label" id="clilbl">Select Range</label>
									<i class="glyphicon glyphicon-calendar fa fa-calendar"></i> <input
									type="text" class="form-control" id="reportrange_right"
									value="December
									30, 2014 - January 28, 2015"/>
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
	<!-- Chart.js -->
	<script src="assets/vendors/Chart.js/dist/Chart.min.js"></script>
	<!-- gauge.js -->
	<script src="assets/vendors/gauge.js/dist/gauge.min.js"></script>
	<!-- bootstrap-progressbar -->
	<script
		src="assets/vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>

	<!-- DateJS -->
	<script src="assets/vendors/DateJS/build/date.js"></script>

	<!-- PNotify -->
	<script src="assets/vendors/pnotify/dist/pnotify.js"></script>
	<script src="assets/vendors/pnotify/dist/pnotify.buttons.js"></script>
	<script src="assets/vendors/pnotify/dist/pnotify.nonblock.js"></script>
	<script src="assets/vendors/pnotify/dist/pnotify.animate.js"></script>
	<script src="assets/vendors/pnotify/dist/pnotify.confirm.js"></script>

	<!-- bootstrap-daterangepicker -->
	<script src="assets/js/moment/moment.min.js"></script>
	<script src="assets/vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
	<script src="assets/build/js/custom.js"></script>


	<script src="assets/js/AakruthAccounting.js"></script>
	<script>
	$("#ledger").submit(function(e){
		if(document.forms["ledger"]["dealerId1"].value ==-1){
		new PNotify(
				{
					title : 'Failed!',
					text : 'Please select a Dealer for ledger !!!!',
					type : 'error',
					hide: false,
					 confirm: {
					        confirm: true
					    },
					styling : 'bootstrap3'
				}).get().on('pnotify.confirm', function() {
					$("DealerSelect").simulate("click");
		});
		return false;
		}
		else {
			return true;
			} 
	});

	</script>
	
</body>
</html>
