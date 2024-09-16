<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/btweb/register" method="post">
		<c:if test="${alert != null}">
			<h3 class="alert alert danger">${alert}</h3>
		</c:if>
		<div class="container">
			<h1>Register</h1>
			<p>Please fill in this form to create an account.</p>
			<hr>

			<label for="uname"><b>Username</b></label> <input type="text"
				placeholder="Enter Username" name="uname" id="uname" required>

			<label for="fullname"><b>Fullname</b></label> <input type="text"
				placeholder="Enter Fullname" name="fullname" required> <label
				for="email"><b>Email</b></label> <input type="text"
				placeholder="Enter Email" name="email" required> <label
				for="phone"><b>Phone</b></label> <input type="text"
				placeholder="Enter Phone" name="phone" required> <label
				for="psw"><b>Password</b></label> <input type="password"
				placeholder="Enter Password" name="psw" id="psw" required> <label
				for="psw-repeat"><b>Repeat Password</b></label> <input
				type="password" placeholder="Repeat Password" name="psw-repeat"
				id="psw-repeat" required>
			<hr>
			<button type="submit">Register</button>
		</div>

		<div class="container signin">
			<p>
				Already have an account? <a href="/HelloWorldProject/views/login.jsp">Sign in</a>.
			</p>
		</div>
	</form>
</body>
</html>