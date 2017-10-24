<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Aakruth Ledger!</title>

<!-- Bootstrap -->
<link href="assets/vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link href="assets/vendors/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- NProgress -->
<link href="assets/vendors/nprogress/nprogress.css" rel="stylesheet">

<!-- Custom styling plus plugins -->
<link href="assets/build/css/custom.min.css" rel="stylesheet">
<link href="assets/css/AakruthLedger.css" rel="stylesheet">
<!-- bootstrap-daterangepicker -->
<link
	href="assets/vendors/bootstrap-daterangepicker/daterangepicker.css"
	rel="stylesheet">
</head>
<body class="nav-md">
	<c:set var="startBalance" scope="session" value="${startBal}" />
	<div class="container body">
		<div class="main_container">
			<div class="col-md-3 left_col hidden-print">
				<div class="left_col scroll-view toggled">
					<div class="navbar nav_title" style="border: 0;">
						<a href="/Aakruth/home" class="site_title"><i class="fa fa-at"></i>
							<span>Aakruth BioMed!</span></a>
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
					<div id="sidebar-menu" class="main_menu_side main_menu">
						<div class="menu_section">
							<br /> <br /> <br />
							<ul class="nav side-menu">
								<li><a href="/Aakruth/home"><i class="fa fa-home"></i>
										Home </a></li>
								<li><a href="/Aakruth/sale"><i class="fa fa-dollar"></i>
										Sales</a></li>
								<sec:authorize access="hasAuthority('ADMIN')">
									<li><a href="/Aakruth/purchase"><i class="fa fa-rupee"></i>Purchase</a></li>
									<li><a href="/Aakruth/inventory"><i
											class="fa fa-truck"></i>Inventory </a></li>
									<li><a href="/Aakruth/product"><i class="fa fa-cubes"></i>Products
									</a></li>
									<li><a href="/Aakruth/builder"><i class="fa fa-user"></i>Builder
									</a></li>
									<li><a href="/Aakruth/customer"><i class="fa fa-users"></i>Customers
									</a></li>
									<li><a href="/Aakruth/user"><i class="fa fa-male"></i>Users
									</a></li>
								</sec:authorize>
							</ul>
						</div>


					</div>
					<!-- /sidebar menu -->

					<!-- /menu footer buttons -->
				</div>
			</div>

			<!-- top navigation -->
			<div class="top_nav hidden-print">
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

					<div class="row">
										<div class="col-xs-12 invoice-header">
												<h1 style="color: blue; " align="center"><b>Aakruth Bio-Med Systems</b></h1>
												<h5 style="color: blue; " align="center"><b>44, Durga Parameswari Nilaya, 5th cross, Vinayakanagar</b></h5>
												<h5 style="color: blue; " align="center"><b>Whitefield, Bangalore - 560066, Karnataka</b></h5>
										</div>
										<!-- /.col -->
									</div> 
									<div class="row">
										<div class="col-xs-12 invoice-header">
												<h2 style="color: white;background-color:blue; font-size: 24px;" align="center"><b>${dealerName} Ledger Sheet From : ${start} To : ${end}</b></h2><br>
										</div>
										<!-- /.col -->
									</div>
				<div class="row">
					<div class="col-xs-12 ">
						<div class="x_panel">
							<div class="x_content" id="invoicePrint">
								<table id="datatable-debit" class="table">
									<thead>
										<tr>
											<th style="width: 20%">Date</th>
											<th style="width: 20%">Description</th>
											<th style="width: 20%">Debit</th>
											<th style="width: 20%">Credit</th>
											<th style="width: 20%">Balance</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td></td>
											<td></td>
											<td></td>
											<td><b>Carry forward -</b></td>
											<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${startBalance}"/></td>
										</tr>
										<c:forEach var="ledger" items="${ledgers}">
											<tr>
												<td>${ledger.date}</td>
												<td>${ledger.transaction}</td>
												<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${ledger.debit+ledger.debit*100/ledger.vat}"/></td>
												<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${ledger.credit+ledger.credit*100/ledger.vat}"/></td>
												<c:if
													test="${ledger.debit+ledger.debit*100/ledger.vat > 0.00}">
													<c:set var="startBalance"
														value="${startBalance+(ledger.debit+ledger.debit*100/ledger.vat)}" />
													<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${startBalance}"/></td>
												</c:if>
												<c:if
													test="${ledger.credit+ledger.credit*100/ledger.vat > 0.00}">
													<c:set var="startBalance"
														value="${startBalance-(ledger.credit+ledger.credit*100/ledger.vat)}" />
													<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${startBalance}"/></td>
												</c:if>
											</tr>
										</c:forEach>
										<tr>
											<td></td>
											<td></td>
											<td></td>
											<td><b>Balance -</b></td>
											<td><b><fmt:formatNumber type="number" maxFractionDigits="2" value="${startBalance}"/></b></td>
										</tr>
									</tbody>
								</table>

							</div>
						</div>
					</div>
				</div>
				<div class="row hidden-print">
					<div class="col-md-12">
						<!-- this row will not appear when printing -->
						<div class="row">
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
		$(document).ready(function() {

		});
	</script>
</body>
</html>