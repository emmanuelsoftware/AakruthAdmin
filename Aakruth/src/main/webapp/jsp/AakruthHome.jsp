<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- Meta, title, CSS, favicons, etc. -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Aakruth Home</title>

<!-- Bootstrap -->
<link href="assets/vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link href="assets/vendors/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- iCheck -->
<link href="assets/vendors/iCheck/skins/flat/green.css" rel="stylesheet">
<!-- bootstrap-progressbar -->
<link
	href="assets/vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css"
	rel="stylesheet">
<!-- PNotify -->
<link href="assets/vendors/pnotify/dist/pnotify.css" rel="stylesheet">
<link href="assets/vendors/pnotify/dist/pnotify.buttons.css"
	rel="stylesheet">
<link href="assets/vendors/pnotify/dist/pnotify.nonblock.css"
	rel="stylesheet">

<!-- Custom Theme Style -->
<link href="assets/build/css/custom.css" rel="stylesheet">
</head>

<body class="nav-md">
	<div class="container body">
		<div class="main_container">
			<div class="col-md-3 left_col">
				<div class="left_col scroll-view">
					<div class="navbar nav_title" style="border: 0;">
						<a href="#" class="site_title"><i class="fa fa-at"></i> <span>Aakruth
								BioMed!</span></a>
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
							<sec:authorize access="hasAnyAuthority('ADMIN','ACCOUNTANT')">
							<li><a href="/Aakruth/account"><i class="fa fa-cubes"></i>
									Accounting </a></li>
							</sec:authorize>
						</ul>
					</nav>
				</div>
			</div>
			<!-- /top navigation -->

			<!-- page content -->
			<div class="right_col" role="main">
				<!-- top tiles -->
				<div class="row tile_count">
					<div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
						<span class="count_top"><i class="fa fa-user"></i> Total
							Items sold</span>
						<div class="count">
							${saleCount}
						</div>
						<span class="count_bottom"> this year</span>
					</div>
					<div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
						<span class="count_top"><i class="fa fa-user"></i> Total
							Sales</span>
						<div class="count">
							${sale}
						</div>
						<span class="count_bottom"> this year</span>
					</div>
					<sec:authorize access="hasAuthority('ADMIN')">
					<div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
						<span class="count_top"><i class="fa fa-user"></i> Total
							Items in inventory</span>
						<div class="count">
							${purchaseCount}
						</div>
						<span class="count_bottom">this year</span>
					</div>
					<div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
						<span class="count_top"><i class="fa fa-clock-o"></i> Total
							purchase</span>
						<div class="count">
							${purchase}
						</div>
						<span class="count_bottom">this year</span>
					</div>

					<div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
						<span class="count_top"><i class="fa fa-user"></i> Total
							Customers</span>
						<div class="count green">
							${customers}
						</div>
						<span class="count_bottom">this year</span>
					</div>
					<div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
						<span class="count_top"><i class="fa fa-user"></i> Total
							Builders</span>
						<div class="count">
							${builders}
						</div>
						<span class="count_bottom">this year</span>
					</div>
					</sec:authorize>
				</div>
				<!-- /top tiles -->

				<div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="dashboard_graph">

							<div class="row x_title">
								<div class="col-md-6">
									<h3>
										Sales trends <small>sales/purchase</small>
									</h3>
								</div>
								<!-- <div class="col-md-6">
									<div id="reportrange" class="pull-right"
										style="background: #fff; cursor: pointer; padding: 5px 10px; border: 1px solid #ccc">
										<i class="glyphicon glyphicon-calendar fa fa-calendar"></i> <span>December
											30, 2014 - January 28, 2015</span> <b class="caret"></b>
									</div>
								</div> -->
							</div>

							<div class="col-md-12 col-sm-12 col-xs-12">
								<div id="placeholder33" style="height: 260px; display: none"
									class="demo-placeholder"></div>
								<div style="width: 100%;">
									<div id="canvas_dahs" class="demo-placeholder"
										style="width: 100%; height: 270px;"></div>
								</div>
							</div>


						</div>

						<div class="clearfix"></div>
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col-md-4 col-sm-4 col-xs-12">
						<div class="x_panel tile fixed_height_320">
							<div class="x_title">
								<h2>Product sales</h2>
								<ul class="nav navbar-right panel_toolbox">
									<li><a class="collapse-link"><i
											class="fa fa-chevron-up"></i></a></li>
									
									<li><a class="close-link"><i class="fa fa-close"></i></a></li>
								</ul>
								<div class="clearfix"></div>
							</div>
							<div class="x_content">
									<div style="width:100%; height:300px;">
										<canvas id="canvas1"></canvas>
									</div>
							</div>
						</div>
					</div>

					<div class="col-md-4 col-sm-4 col-xs-12">
						<div class="x_panel tile fixed_height_320">
							<div class="x_title">
								<h2>Product purchase</h2>
								<ul class="nav navbar-right panel_toolbox">
									<li><a class="collapse-link"><i
											class="fa fa-chevron-up"></i></a></li>
									
									<li><a class="close-link"><i class="fa fa-close"></i></a></li>
								</ul>
								<div class="clearfix"></div>
							</div>
							<div class="x_content">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<canvas id="canvas2"></canvas>
									</div>
							</div>
						</div>
					</div>

					 <!-- Start to do list -->
                <div class="col-md-4 col-sm-4 col-xs-12">
                  <div class="x_panel">
                    <div class="x_title">
                      <h2>Notifications<small>Recent activities</small></h2>
                      <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                        </li>
                        <li><a class="close-link"><i class="fa fa-close"></i></a>
                        </li>
                      </ul>
                      <div class="clearfix"></div>
                    </div>
                    <div class="x_content">

                      <div class="">
                        <ul class="to_do" id="notificationList">
                         
                        </ul>
                      </div>
                    </div>
                  </div>
                </div>
                <!-- End to do list -->

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

	<!-- jQuery -->
	<script src="assets/vendors/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="assets/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- FastClick -->
	<script src="assets/vendors/fastclick/lib/fastclick.js"></script>
	<!-- NProgress -->
	<script src="assets/vendors/nprogress/nprogress.js"></script>
	<!-- Chart.js -->
	<script src="assets/vendors/Chart.js/dist/Chart.min.js"></script>
	<!-- gauge.js -->
	<script src="assets/vendors/bernii/gauge.js/dist/gauge.min.js"></script>
	<!-- bootstrap-progressbar -->
	<script
		src="assets/vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
	<!-- iCheck -->
	<script src="assets/vendors/iCheck/icheck.min.js"></script>
	<!-- Flot -->
	<script src="assets/vendors/Flot/jquery.flot.js"></script>
	<script src="assets/vendors/Flot/jquery.flot.pie.js"></script>
	<script src="assets/vendors/Flot/jquery.flot.time.js"></script>
	<script src="assets/vendors/Flot/jquery.flot.stack.js"></script>
	<script src="assets/vendors/Flot/jquery.flot.resize.js"></script>
	<!-- Flot plugins -->
	<script src="assets/js/flot/jquery.flot.orderBars.js"></script>
	<script src="assets/js/flot/date.js"></script>
	<script src="assets/js/flot/jquery.flot.spline.js"></script>
	<script src="assets/js/flot/curvedLines.js"></script>
	<!-- jVectorMap -->
	<script src="assets/js/maps/jquery-jvectormap-2.0.3.min.js"></script>
	<!-- bootstrap-daterangepicker -->
	<script src="assets/js/moment/moment.min.js"></script>
	<script src="assets/js/datepicker/daterangepicker.js"></script>

	<!-- Custom Theme Scripts -->
	<script src="assets/build/js/custom.js"></script>
	<script src="assets/js/AakruthHome.js"></script>
	
	
	<!-- PNotify -->
	<script src="assets/vendors/pnotify/dist/pnotify.js"></script>
	<script src="assets/vendors/pnotify/dist/pnotify.buttons.js"></script>
	<script src="assets/vendors/pnotify/dist/pnotify.nonblock.js"></script>
	
	<!-- Flot -->
	<script>
		$(document).ready(function() {
			getSale();
			//plotChart();
		});
	</script>
	<!-- /Flot -->


	<!-- Doughnut Chart -->
	<script>
		var latestNotId=0;
		$(document).ready(
				function() {
					var options = {
						legend : false,
						responsive : false
					};
					var names = [];
					var values = [];
					$.ajax({
				        url : "/Aakruth/sale/productChart",
				        success : function(responseJson) {
				        	$.each(responseJson, function(index, productChart) {
				    			names[index] = productChart.name;
				    			values[index] = productChart.cnt;
				        	});
				        	new Chart(document.getElementById("canvas1"), {
								type : 'doughnut',
								tooltipFillColor : "rgba(51, 51, 51, 0.55)",
								data : {
									labels : names,
									datasets : [ {
										data : values,
										backgroundColor : [ "#BDC3C7", "#9B59B6",
												"#E74C3C", "#26B99A", "#3498DB" ],
										hoverBackgroundColor : [ "#CFD4D8", "#B370CF",
												"#E95E4F", "#36CAAB", "#49A9EA" ]
									} ]
								},
								options : options
							});
				        },
				        error : function(response) {
							new PNotify(
									{
										title : 'Failed!',
										text : 'Error while loading Product chart',
										type : 'error',
										styling : 'bootstrap3'
									});
						}
				     });
				    var pnames = [];
				    var pvalues = [];
					$.ajax({
				        url : "/Aakruth/purchase/productChart",
				        success : function(responseJson) {
				        	$.each(responseJson, function(index, productChart) {
				    			pnames[index] = productChart.name;
				    			pvalues[index] = productChart.cnt;
				        	});
				        	new Chart(document.getElementById("canvas2"), {
								type : 'doughnut',
								tooltipFillColor : "rgba(51, 51, 51, 0.55)",
								data : {
									labels : pnames,
									datasets : [ {
										data : pvalues,
										backgroundColor : [ "#BDC3C7", "#9B59B6",
												"#E74C3C", "#26B99A", "#3498DB" ],
										hoverBackgroundColor : [ "#CFD4D8", "#B370CF",
												"#E95E4F", "#36CAAB", "#49A9EA" ]
									} ]
								},
								options : options
							});
				        },
				        error : function(response) {
							new PNotify(
									{
										title : 'Failed!',
										text : 'Error while loading Product chart',
										type : 'error',
										styling : 'bootstrap3'
									});
						}
				     });
					$.ajax({
				        url : '/Aakruth/notification',
				        success : function(notifications) {
				        	var htmlList = '';
				        	$.each(notifications, function(index, notification) {
				    			htmlList=htmlList+'<li><p> '+notification.usrTbl.usrnme+' '+notification.action+' </p> </li>';
				    			if(notification.notId>latestNotId){
				    				latestNotId=notification.notId;
						    		}
				    			
				        	});
				        	$('#notificationList').append(htmlList);
				        },
				        error : function(response) {
							new PNotify(
									{
										title : 'Failed!',
										text : 'Error while loading Notifications',
										type : 'error',
										styling : 'bootstrap3'
									});
						}
				     });
					setTimeout(notificationDisplay, 30000);
				});

				function notificationDisplay(){
					$.ajax({
						url : "/Aakruth/notificationLatest",
						async : false,
						data : {
							notId : latestNotId
						},
						success : function(notifications) {
				        	$.each(notifications, function(index, notification) {
				        		new PNotify(
										{
											title : 'Notification',
											text : notification.usrTbl.usrnme+' performed '+notification.action,
											type : 'info',
											styling : 'bootstrap3'
										});
				        		if(notification.notId>latestNotId){
				    				latestNotId=notification.notId;
						    		}
				        	});
				        }
					});
					setTimeout(notificationDisplay, 30000);
					
				};
	</script>
	<!-- /Doughnut Chart -->

	<!-- bootstrap-daterangepicker -->
	<script>
	</script>
	<!-- /bootstrap-daterangepicker -->

	<!-- gauge.js -->
	<script>
		var opts = {
			lines : 12,
			angle : 0,
			lineWidth : 0.4,
			pointer : {
				length : 0.75,
				strokeWidth : 0.042,
				color : '#1D212A'
			},
			limitMax : 'false',
			colorStart : '#1ABC9C',
			colorStop : '#1ABC9C',
			strokeColor : '#F0F3F3',
			generateGradient : true
		};
		var target = document.getElementById('foo'), gauge = new Gauge(target)
				.setOptions(opts);

		gauge.maxValue = 6000;
		gauge.animationSpeed = 32;
		gauge.set(3200);
		gauge.setTextField(document.getElementById("gauge-text"));
	</script>
	<!-- /gauge.js -->
</body>
</html>