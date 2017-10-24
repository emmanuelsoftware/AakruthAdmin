<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title><tiles:getAsString name="title" /></title>

<!-- Bootstrap -->
<link href="static/vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link href="static/vendors/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- NProgress -->
<link href="static/vendors/nprogress/nprogress.css" rel="stylesheet">
<!-- iCheck -->
<link href="static/vendors/iCheck/skins/flat/green.css"
	rel="stylesheet">

<!-- bootstrap-progressbar -->
<link
	href="static/vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css"
	rel="stylesheet">
<!-- JQVMap -->
<link href="static/vendors/jqvmap/dist/jqvmap.min.css" rel="stylesheet" />
<!-- bootstrap-daterangepicker -->
<link
	href="static/vendors/bootstrap-daterangepicker/daterangepicker.css"
	rel="stylesheet">

<!-- Custom Theme Style -->
<link href="static/build/css/custom.min.css" rel="stylesheet">
    <!-- Datatables -->
    <link href="static/vendors/datatables.net-bs/css/dataTables.bootstrap.min.css" rel="stylesheet">
    <link href="static/vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css" rel="stylesheet">
    <link href="static/vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css" rel="stylesheet">
    <link href="static/vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css" rel="stylesheet">
    <link href="static/vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css" rel="stylesheet">

    <!-- PNotify -->
    <link href="static/vendors/pnotify/dist/pnotify.css" rel="stylesheet">
    <link href="static/vendors/pnotify/dist/pnotify.buttons.css" rel="stylesheet">
    <link href="static/vendors/pnotify/dist/pnotify.nonblock.css" rel="stylesheet">

</head>

<body class="nav-md">
	<div class="container body">
		<div class="main_container">
			<!-- side - menu -->
			<tiles:insertAttribute name="sidebar" />
			<!-- side - menu  -->
			<!-- top navigation -->
			<tiles:insertAttribute name="header" />
			<!-- /top navigation -->

			<!-- page content -->
			<tiles:insertAttribute name="body" />
			<!-- /page content -->

			<!-- footer content -->
			<tiles:insertAttribute name="footer" />
			<!-- /footer content -->
		</div>
	</div>

	<!-- jQuery -->
	<script src="static/vendors/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="static/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- FastClick -->
	<script src="static/vendors/fastclick/lib/fastclick.js"></script>
	<!-- NProgress -->
	<script src="static/vendors/nprogress/nprogress.js"></script>
	<!-- Chart.js -->
	<script src="static/vendors/Chart.js/dist/Chart.min.js"></script>
	<!-- gauge.js -->
	<script src="static/vendors/gauge.js/dist/gauge.min.js"></script>
	<!-- bootstrap-progressbar -->
	<script
		src="static/vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
	<!-- iCheck -->
	<script src="static/vendors/iCheck/icheck.min.js"></script>
	<!-- Skycons -->
	<script src="static/vendors/skycons/skycons.js"></script>
	<!-- Flot -->
	<script src="static/vendors/Flot/jquery.flot.js"></script>
	<script src="static/vendors/Flot/jquery.flot.pie.js"></script>
	<script src="static/vendors/Flot/jquery.flot.time.js"></script>
	<script src="static/vendors/Flot/jquery.flot.stack.js"></script>
	<script src="static/vendors/Flot/jquery.flot.resize.js"></script>
	<!-- Flot plugins -->
	<script
		src="static/vendors/flot.orderbars/js/jquery.flot.orderBars.js"></script>
	<script src="static/vendors/flot-spline/js/jquery.flot.spline.min.js"></script>
	<script src="static/vendors/flot.curvedlines/curvedLines.js"></script>
	<!-- DateJS -->
	<script src="static/vendors/DateJS/build/date.js"></script>
	<!-- JQVMap -->
	<script src="static/vendors/jqvmap/dist/jquery.vmap.js"></script>
	<script src="static/vendors/jqvmap/dist/maps/jquery.vmap.world.js"></script>
	<script
		src="static/vendors/jqvmap/examples/js/jquery.vmap.sampledata.js"></script>
	<!-- bootstrap-daterangepicker -->
	<script src="static/vendors/moment/min/moment.min.js"></script>
	<script
		src="static/vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
	<script src="static/vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
	    <!-- Datatables -->
    <script src="static/vendors/datatables.net/js/jquery.dataTables.min.js"></script>
    <script src="static/vendors/datatables.net/js/jquery.spring-friendly.js"></script>
    <script src="static/vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
    <script src="static/vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
    <script src="static/vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
    <script src="static/vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
    <script src="static/vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
    <script src="static/vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
    <script src="static/vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>
    <script src="static/vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
    <script src="static/vendors/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
    <script src="static/vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>
    <script src="static/vendors/datatables.net-scroller/js/dataTables.scroller.min.js"></script>
    <script src="static/vendors/jszip/dist/jszip.min.js"></script>
    <script src="static/vendors/pdfmake/build/pdfmake.min.js"></script>
    <script src="static/vendors/pdfmake/build/vfs_fonts.js"></script>
    
        <!-- PNotify -->
	<!-- PNotify -->
	<script src="static/vendors/pnotify/dist/pnotify.js"></script>
	<script src="static/vendors/pnotify/dist/pnotify.buttons.js"></script>
	<script src="static/vendors/pnotify/dist/pnotify.animate.js"></script>
	<script src="static/vendors/pnotify/dist/pnotify.confirm.js"></script>
	<script src="static/vendors/pnotify/dist/pnotify.nonblock.js"></script>
	<!-- Custom Theme Scripts -->
	<script src="static/ERPApp.js"></script>

</body>
</html>
