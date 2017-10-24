<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>ERP Application</title>

<!-- Bootstrap -->
<link href="static/vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link href="static/vendors/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- NProgress -->
<link href="static/vendors/nprogress/nprogress.css" rel="stylesheet">
<!-- Animate.css -->
<link href="static/vendors/animate.css/animate.min.css" rel="stylesheet">

<!-- Custom Theme Style -->
<link href="static/build/css/custom.css" rel="stylesheet">
</head>

<body class="login">
	<div>
		<a class="hiddenanchor" id="signup"></a> <a class="hiddenanchor"
			id="signin"></a> <a class="hiddenanchor" id="changepass"></a>
		<div class="login_wrapper">
			<div class="animate form login_form">
				<section class="login_content">
					<form action="login" method="post">
						<h1>Login Form</h1>
						<div>
							<input type="email" class="form-control" placeholder="Email"
								name="email" required />
						</div>
						<div>
							<input type="password" class="form-control"
								placeholder="Password" required name="password" />
						</div>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						<div>
							<button type="submit" class="btn btn-default submit">Log
								in</button>
							<a class="reset_pass" href="#changepass">Lost your password?</a>
						</div>

						<div class="clearfix"></div>

						<div class="separator">
							<p class="change_link">
								New to site? <a href="#signup" class="to_register"> Create
									Account </a>
							</p>

							<div class="clearfix"></div>
							<br />

							<div>
								<h1>ERP Application!</h1>
								<p>All Rights Reserved. Emmanual Software solutions ltd</p>
							</div>
						</div>
					</form>
					<c:if test="${param.error ne null}">
						<div class="alert-danger">Invalid Email and password.</div>
					</c:if>
					<c:if test="${param.logout ne null}">
						<div class="alert-normal">You have been logged out.</div>
					</c:if>
				</section>
			</div>

			<div id="register" class="animate form registration_form">
				<section class="login_content">
					<form action="save" method="post" onsubmit="return validate()">
						<h1>Create Account</h1>
						<div>
							<input type="text" class="form-control" name="usrnme"
								placeholder="Name"   />
						</div>
						<div>
							<input type="text" class="form-control" name="phnnbr"
								placeholder="Phone Nummber" required />
						</div>
						<div>
							<textarea class="form-control" rows="4" name="email"
								placeholder="Address" required></textarea>
						</div>
						<div>
							<input type="email" class="form-control" name="adr"
								placeholder="Email" required />
						</div>
						<div>
							<input type="password" class="form-control" name="pswd"
								placeholder="Password" required />
						</div>
						<div>
							<input type="password" class="form-control" name="confirmPswd"
								placeholder="Confirm Password" required />
						</div>
						<div>
							<button class="btn btn-default" type="submit">Submit</button>
						</div>

						<div class="clearfix"></div>

						<div class="separator">
							<p class="change_link">
								Already a member ? <a href="#signin" class="to_register">
									Log in </a>
							</p>

							<div class="clearfix"></div>
							<br />

							<div>
								<h1>ERP Application!</h1>
								<p>All Rights Reserved. Emmanual Software solutions ltd</p>
							</div>
						</div>
					</form>
				</section>
			</div>

			<div id="changePass" class="animate form change_pass_form">
				<section class="login_content">
					<form>
						<h1>Change Password</h1>
						<div>
							<input type="email" class="form-control" placeholder="Email"
								required />
						</div>
						<div>
							<input type="password" class="form-control"
								placeholder="Password" required />
						</div>
						<div>
							<input type="password" class="form-control"
								placeholder="Retype Password" required />
						</div>
						<div>
							<a class="btn btn-default submit" href="index.html">Submit</a>
						</div>

						<div class="clearfix"></div>

						<div class="separator">
							<p class="change_link">
								Already a member ? <a href="#signin" class="to_register">
									Log in </a>
							</p>

							<div class="clearfix"></div>
							<br />

							<div>
								<h1>ERP Application!</h1>
								<p>All Rights Reserved. Emmanual Software solutions ltd</p>
							</div>
						</div>
					</form>
				</section>
			</div>
		</div>
	</div>
</body>
<script>
function validate(){
	alert("submit clicked");
	return false;
}
</script>
</html>
