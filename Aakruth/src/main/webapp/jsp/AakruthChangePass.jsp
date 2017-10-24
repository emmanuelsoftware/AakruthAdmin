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
		<form class="form-signin" method="get" onsubmit="return checkValue();" id ="changePass"
		>
		<input class="form-control"
				type="email"
				pattern="[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$" required
				 placeholder="Email address" autofocus autocomplete="off"
				id="inputEmail" name="email"> 
			<input
				class="form-control" type="password" required placeholder="Password"
				id="inputPassword" name="password">
				<input
				class="form-control" type="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" required placeholder="New Password"
				id="newPassword" name="newPassword">
				<input
				class="form-control" type="password" onblur="checkValue()" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" required placeholder="Confirm Password"
				id="newConfirmPassword" name="newConfirmPassword">
			<button class="btn btn-primary btn-block btn-lg btn-signin" 
				type="button" id="changePswd">Change Password</button>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<h5><a href="/Aakruth/login">Back to Login</a></h5>
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
			var newpassword = document.forms["changePass"]["newPassword"].value;
			var newConfirmPassword = document.forms["changePass"]["newConfirmPassword"].value;
			var pswdformat = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}$/; 
			var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/; 
			if (newpassword != newConfirmPassword) {
				new PNotify(
						{
							title : 'Failed!',
							text : 'Password do not match!!!!',
							type : 'error',
							hide: false,
							styling : 'bootstrap3'
						});
				return false;
			}
			if((newpassword).match(pswdformat)) 
			{ }
			else{		new PNotify(
						{
							title : 'Failed!',
							text : 'Input Password and Submit [6 to 20 characters which contain at least one numeric digit, one uppercase and one lowercase letter] !!!!',
							type : 'error',
							hide: false,
							styling : 'bootstrap3'
						});
			document.forms["login"]["inputEmail"].focus(); 
			return false; 
			}
			if (newpassword == "Aakruth@123") {
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
							text : 'You have entered invalid email adress !!!!',
							type : 'error',
							hide: false,
							styling : 'bootstrap3'
						});
			document.forms["login"]["inputEmail"].focus(); 
			return false;   
			} 
            return true;	
		}
		$("#changePwd").click(function() {
			alert("Clicked");
			$.ajax({
				url : "/Aakruth/PassUpdate",
				type : "GET",
				data : {
					inputEmail  : $('#inputEmail').val()
				},
					success : function(response) {
				   alert("Success");
					var msg = response;
					if (msg.trim() == "true") {
						new PNotify(
								{
									title : 'Success!',
									text : 'Password change successfully!',
									type : 'success',
									styling : 'bootstrap3'
								});
												
					} else {
						new PNotify(
								{
									title : 'Failed!',
									text : 'password cannot change!',
									type : 'error',
									styling : 'bootstrap3'
								});
					}
				},
				error : function(response) {
					alert("error 	");
					new PNotify(
							{
								title : 'Failed!',
								text : 'password not change!',
								type : 'error',
								styling : 'bootstrap3'
							});
				}
			});
			alert("outside");
	});

		$("#changePswd").click(function() {
			$.ajax({
				url : "/Aakruth/user/passChange",
				data : {
					inputMail  : $('#inputEmail').val()
				},
				success : function(response) {
					var msg = response;
					if (msg.trim() == "true") {
						new PNotify(
								{
									title : 'Success!',
									text : 'Builder data saved successfully!',
									type : 'success',
									styling : 'bootstrap3'
								});
						table.ajax.reload();
						
					} else {
						new PNotify(
								{
									title : 'Failed!',
									text : 'You cannot save this builder right now!',
									type : 'error',
									styling : 'bootstrap3'
								});
					}
				},
				error : function(response) {
					new PNotify(
							{
								title : 'Failed!',
								text : 'You cannot chage pass right now!',
								type : 'error',
								styling : 'bootstrap3'
							});
				}
			});
	});
	</script>
</body>

</html>