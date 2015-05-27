<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>User Registration Page</title>
<link rel="stylesheet" href="../../css/style.css">
</head>
<body>
	<h1>User Registration</h1>

	<c:url var="addAction" value="/user/add"></c:url>
	
	<c:if test="${not empty error}">
		<div class="error">${error}</div>
	</c:if>
	
	<form:form action="${addAction}" commandName="user">
		<table>
			<tr>
				<td><form:label path="name">
						<spring:message text="Name" />
					</form:label></td>
				<td><form:input path="name" maxlength="25" /></td>
			</tr>

			<tr>
				<td><form:label path="rollNumber">
						<spring:message text="Roll number" />
					</form:label></td>
				<td><form:input path="rollNumber" maxlength="15"
						onkeyup="javascript: document.getElementById('usernameId').value=this.value" /></td>
			</tr>

			<tr>
				<td><form:label path="username">
						<spring:message text="Username" />
					</form:label></td>
				<td><form:input id="usernameId" path="username" maxlength="15"
						readonly="true" /></td>
			</tr>

			<tr>
				<td><form:label path="password">
						<spring:message text="Password" />
					</form:label></td>
				<td><form:password path="password" maxlength="15" /></td>
			</tr>

			<tr>
				<td><form:label path="cpassword">
						<spring:message text="Confirm password" />
					</form:label></td>
				<td><form:password path="cpassword" maxlength="15" /></td>
			</tr>

			<tr>
				<td><form:label path="email">
						<spring:message text="Email" />
					</form:label></td>
				<td><form:input path="email" maxlength="50" /></td>
			</tr>

			<tr>
				<td><form:label path="mobileNumber">
						<spring:message text="Mobile number" />
					</form:label></td>
				<td><form:input path="mobileNumber" maxlength="10" /></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit"
					value="<spring:message text="Register User"/>" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>
