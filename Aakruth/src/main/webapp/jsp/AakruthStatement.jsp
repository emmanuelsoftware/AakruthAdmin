<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Aakruth Statement! </title>

<!-- Bootstrap -->
<link href="assets/vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- style ledger -->
<link href="assets/css/AakruthLedger.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link href="assets/vendors/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- NProgress -->
<link href="assets/vendors/nprogress/nprogress.css" rel="stylesheet">

<!-- Custom styling plus plugins -->
<link href="assets/build/css/custom.min.css" rel="stylesheet">

<!-- bootstrap-daterangepicker -->
<link
	href="assets/vendors/bootstrap-daterangepicker/daterangepicker.css"
	rel="stylesheet">
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
				<div class="nav_menu hidden-print">
					<nav class="" role="navigation">
						<div class="nav toggle">
							<a id="menu_toggle"><i class="fa fa-bars"></i></a>
						</div>

						<ul class="nav navbar-nav navbar-right ">
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

					<div class="row">
						<div class="col-md-12 ">
							<div class="x_panel">
								<div class="x_content" id="statementPrint">

									<div class="content statement"> <!-- title row -->
									<div class="row">
										<div class="col-xs-12 stmt-header">
												<h1 style="color: blue; " align="center"><b>Aakruth Bio-Med Systems</b></h1>
												<h5 style="color: blue; " align="center"><b>44, Durga Parameswari Nilaya, 5th cross, Vinayakanagar</b></h5>
												<h5 style="color: blue; " align="center"><b>Whitefield, Bangalore - 560066, Karnataka</b></h5>
										</div>
										<!-- /.col -->
									</div> 
									<div class="row">
										<div class="col-xs-12 stmt-header">
												<h2 style="color: white;background-color:blue; font-size: 24px;" align="center"><b>${dealerName} Financial Statement From : ${start} To : ${end}</b></h2><br>
										</div>
										<!-- /.col -->
									</div>
									<!-- /.row --> <!-- Table row -->
									<div class="row">
										<div class="col-xs-12 table">
											<br>
											<table class="table table-striped" width="100%">
												<thead>
													<tr>
														 <th style="width: 60%;font-size: 16px;font-weight: bold;"align="center">Description</th>
														 <th style="width: 20%;font-size: 16px;font-weight: bold;"align="right">Amount</th>
														 <th style="width: 20%;font-size: 16px;font-weight: bold;"align="right">Total</th>
													</tr>
												</thead>
												<tbody>
													<tr>
															<td></td>
															<td style="font-size: 16px;font-weight: bold;"align="left">Carry Forward:</td>
															<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${startBal}"/></td>
													</tr>
													<tr>
															<td style="font-size: 16px;font-weight: bold;"align="left">Income</td>
															<td></td>
															<td></td>
													</tr>
													<c:forEach var="debit" items="${debits}">
														<tr>
															<td>${debit.transaction}</td>
															<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${debit.amount}" /></td>
															<td></td>
															<c:set var="totalDebit"
															value="${totalDebit+(debit.amount)}" />
														</tr>
													</c:forEach>
													<tr>
															<td></td>
															<td style="font-size: 16px;font-weight: bold;"align="left">Total Income</td>
															<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${totalDebit}"/></td>
													</tr>
													<tr>
															<td style="font-size: 16px;font-weight: bold;"align="left">Expence</td>
															<td></td>
															<td></td>
													</tr>
													<c:forEach var="credit" items="${credits}">
														<tr>
															<td>${credit.transaction}</td>
															<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${credit.amount}"/></td>
															<td></td>
															<c:set var="totalCredit"
															value="${totalCredit-(credit.amount)}" />
														</tr>
													</c:forEach>
													<tr>
															<td></td>
															<td style="font-size: 16px;font-weight: bold;"align="left">Total Expence</td>
															<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${totalCredit}"/></td>
													</tr>
													<tr>
															<td></td>
															<td style="font-size: 16px;font-weight: bold;"align="left">Grand Total</td>
															<c:set var="Total"
															value="${startBal + (totalDebit-(totalCredit))}" />
															<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${Total}"/></td>
													</tr>
												</tbody>
											</table>
										</div>
				,						<!-- /.col -->
									</div>
									<div class="row invoice-info">
										<div class="col-sm-12 invoice-col" align="right">
											<address>
												<b>Address</b>: 44, Durga Parameswari Nilaya, 5th cross, Vinayakanagar, 
												Whitefield, Bangalore - 560066, Karnataka
												<br><b>Phone</b>: 
												<b>Email</b>: info@aakruthbiomed.com
												<b>Website</b>: www.aakruthbiomed.com
											</address>
										</div>
									</div>
									</div>
									<!-- /.row --> </div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<!-- this row will not appear when printing -->
							<div class="row hidden-print">
								<div class="col-xs-12">
									<button class="btn btn-default" onclick="window.print();">
										<i class="fa fa-print"></i> Print
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /page content -->
			
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

	<!-- Custom Theme Scripts -->
		<!-- bootstrap-daterangepicker -->
	<script src="assets/js/moment/moment.min.js"></script>
	<script
		src="assets/vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
	<script src="assets/build/js/custom.js"></script>
	<script>
	$(document).ready(
			function() {
				if ($BODY.hasClass('nav-md')) {
					$SIDEBAR_MENU.find('li.active ul').hide();
					$SIDEBAR_MENU.find('li.active').addClass('active-sm')
							.removeClass('active');
				} else {
					$SIDEBAR_MENU.find('li.active-sm ul').show();
					$SIDEBAR_MENU.find('li.active-sm').addClass('active')
							.removeClass('active-sm');
				}

				$BODY.toggleClass('nav-md nav-sm');
			});
	</script>
</body>
</html>