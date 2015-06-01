<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="true"%>
<html>
<head>
	<title>Aptikraft Online Exam Application - LoginPage</title>
	<script src="resources/js/jquery-1.11.3.js"></script>
	<script src="resources/js/form-validation.js"></script>
	<link rel="stylesheet" href="resources/css/style.css" />
</head>
<body onload='document.loginForm.username.focus();'>
	<div id="page-container">
		<div id="content-body">
			<h4>Aptikraft Online Exam Application</h4>
		
			<c:if test="${not empty error}">
				<div class="error">${error}</div>
			</c:if>
			
			<div class="form-error"></div>
			
			<c:if test="${not empty msg}">
				<div class="msg">${msg}</div>
			</c:if>
			
			<form name='loginForm' action="<c:url value='/j_spring_security_check' />" method='POST' onsubmit="return validateLoginForm();">
				<p class="heading-style1">User Login</p>
				<p><span>Username : </span><input type="text" name="username" /></p>
				<p><span>Password : </span><input type="password" name="password" /></p>
				<p>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<input name="submit" type="submit" value="Login" />
				</p>
			</form>
			<p>New user? <a href="<c:url value='registerPage' />">register now.</a></p>	
		</div>
		<div id="footer">
			<p>&copy; 2015, All rights reserved.</p>
		</div>
	</div>
</body>
</html>
