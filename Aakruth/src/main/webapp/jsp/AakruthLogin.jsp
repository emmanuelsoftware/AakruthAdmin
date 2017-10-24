<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>

<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Aakruth Bio-med Inventory Login</title>
<link rel="stylesheet"
	href="assets/vendors/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/styles.css">
<link rel="stylesheet" href="assets/css/Google-Style-Login.css">
<link rel="stylesheet" href="assets/css/Hero-Technology.css">
<link rel="stylesheet" href="assets/css/Pretty-Registration-Form.css">
<link rel="stylesheet" href="assets/css/Bootstrap-Payment-Form.css">
</head>
<!-- PNotify -->
<link href="assets/vendors/pnotify/dist/pnotify.css" rel="stylesheet">
<link href="assets/vendors/pnotify/dist/pnotify.buttons.css"
	rel="stylesheet">
<link href="assets/vendors/pnotify/dist/pnotify.nonblock.css"
	rel="stylesheet">


<body id="page">
	<div class="container-fluid" id="hdr">
		<div class="col-md-12 col-sm-12">
			<nav class="navbar navbar-default">
				<div class="container-fluid">
					<div class="navbar-header">
						<a class="navbar-brand navbar-link" href="#" id="brdnme"><span
							id="fstnme">Aakruth </span><span id="lstnme">Bio-med </span></a>
						<button class="navbar-toggle collapsed" data-toggle="collapse"
							data-target="#navcol-1">
							<span class="sr-only">Toggle navigation</span><span
								class="icon-bar"></span><span class="icon-bar"></span><span
								class="icon-bar"></span>
						</button>
					</div>
				</div>
			</nav>
		</div>
	</div>
	<div class="login-card">
		<img src="assets/images/avatar_2x.png" class="profile-img-card">
		<p class="profile-name-card"></p>
		<form action="/Aakruth/login" class="form-signin" id="login"
			method="post" onsubmit="return checkValue()">
			<span class="reauth-email"> </span> <input class="form-control"
				type="email" onblur="checkValue()"
				pattern="[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$" required
				 placeholder="Email address" autofocus autocomplete="off"
				id="inputEmail" name="email"> <input class="form-control"
				type="password" onblur="checkValue()" required
				placeholder="Password" id="inputPassword" name="password">
			<button class="btn btn-primary btn-block btn-lg btn-signin"
				type="submit" id="submit">Sign in</button>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<h5>
				<a href="/Aakruth/ChangePass">Change password</a>
			</h5>
		</form>
		<c:if test="${param.error ne null}">
			<div class="alert-danger">Invalid Email and password.</div>
		</c:if>
		<c:if test="${param.logout ne null}">
			<div class="alert-normal">You have been logged out.</div>
		</c:if>
	</div>
	<div class="container" id="sal">
		<div class="col-md-12" id="ftr">
			<p>@ copyrigiht - Aakruth Biomed Pvt Ltd | Powered by Emmanual
				software solution</p>
		</div>
	</div>
	<script src="assets/vendors/jquery/dist/jquery.min.js"></script>
	<script src="assets/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- PNotify -->
	<script src="assets/vendors/pnotify/dist/pnotify.js"></script>
	<script src="assets/vendors/pnotify/dist/pnotify.buttons.js"></script>
	<script src="assets/vendors/pnotify/dist/pnotify.nonblock.js"></script>
	<script src="assets/vendors/pnotify/dist/pnotify.animate.js"></script>
	<script src="assets/vendors/pnotify/dist/pnotify.confirm.js"></script>
	<script>
		function checkValue() {
			var password = document.forms["login"]["inputPassword"].value;
			var email = document.forms["login"]["inputEmail"].value;
			var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
			if (password == "Aakruth@123") {
					new PNotify(
						{
							title : 'Failed!',
							text : 'Change default password !!!!',
							type : 'error',
							hide: false,
							 confirm: {
							        confirm: true
							    },
							styling : 'bootstrap3'
						}).get().on('pnotify.confirm', function() {
								window.location.href = "/Aakruth/ChangePass"
				});
				return false;
			}
			if((email).match(mailformat)) 
			{ }
			else{		new PNotify(
						{
							title : 'Failed!',
							text : 'You have entered invalid email address !!!!',
							type : 'error',
							hide: false,
							styling : 'bootstrap3'
						});
			document.forms["login"]["inputEmail"].focus(); 
			return false;   
			} 
					
		} 
		
		  
		
	</script>
</body>

</html>