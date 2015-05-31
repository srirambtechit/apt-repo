<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
	<title>Aptikraft Online Exam Application - RegisterPage</title>
	<link rel="stylesheet" href="resources/css/style.css" />
	<style type="text/css">
		form p span { width: 150px; }
	</style>
</head>
<body>
	<div id="page-container">
		<div id="content-body">
			<h4>Aptikraft Online Exam Application</h4>

			<c:url var="addAction" value="/addNewUser"></c:url>
		
			<c:if test="${not empty error}">
				<div class="error">${error}</div>
			</c:if>
		
			<form:form action="${addAction}" commandName="user">
				<p class="heading-style1">New User Registration</p>
				
				<p>
					<span>
						<form:label path="name">
							<spring:message text="Name" />
						</form:label>
					</span>
					<form:input path="name" maxlength="25" />
				</p>
				
				<p>
					<span>
						<form:label path="rollNumber">
							<spring:message text="Roll number" />
						</form:label>
					</span>
					<form:input path="rollNumber" maxlength="15" onkeyup="javascript: document.getElementById('usernameId').value=this.value" />
				</p>
				
				<p>
					<span>
						<form:label path="username">
							<spring:message text="Username" />
						</form:label>
					</span>
					<form:input id="usernameId" path="username" maxlength="15" readonly="true" />
				</p>
				
				<p>
					<span>
						<form:label path="password">
							<spring:message text="Password" />
						</form:label>
					</span>
					<form:password path="password" maxlength="15" />
				</p>
				
				<p>
					<span>
						<form:label path="cpassword">
							<spring:message text="Confirm password" />
						</form:label>
					</span>
					<form:password path="cpassword" maxlength="15" />
				</p>
				
				<p>
					<span>
						<form:label path="email">
							<spring:message text="Email" />
						</form:label>
					</span>
					<form:input path="email" maxlength="50" />
				</p>
				
				<p>
					<span>
						<form:label path="mobileNumber">
							<spring:message text="Mobile number" />
						</form:label>
					</span>
					<form:input path="mobileNumber" maxlength="10" />
				</p>
				
				<p>
					<input type="submit" value="<spring:message text="Register User"/>" />				
				</p>
		
			</form:form>
			<p>Already registered? <a class="link" href="<c:url value='loginPage' />">login now.</a></p>	
		</div>
		<div id="footer">
			<p>&copy; 2015, All rights reserved.</p>
		</div>
	</div>
</body>
</html>
