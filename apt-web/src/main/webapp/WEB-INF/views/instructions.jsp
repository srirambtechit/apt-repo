<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%-- <%@ page import="com.aptikraft.common.utils.CurrentUser;" %> --%>
<%@ page session="true"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Aptikraft Online Exam Application - InstructionPage</title>
	<link rel="stylesheet" href="resources/css/style.css" />
	<script type="text/javascript">
		// opening new child window to load QA Page in it
		//url : startExamPage
		function startExam() {
			
		}
	</script>
</head>
<body>
	<div id="page-container">
		<div id="content-body">
			<h4>Aptikraft Online Exam Application</h4>
			
			<sec:authorize access="hasRole('ROLE_USER')">
				<!-- For login user -->
				<c:url value="/j_spring_security_logout" var="logoutUrl" />
				<form action="${logoutUrl}" method="post" id="logoutForm">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
				<script>
					function formSubmit() {
						document.getElementById("logoutForm").submit();
					}
				</script>
		
				<c:if test="${pageContext.request.userPrincipal.name != null}">
					<h2>
<%-- 						<c:out value="${ CurrentUser.getNameOfLoggedInUser() }" /> --%>
						User : ${pageContext.request.userPrincipal.name} | <a
							href="javascript:formSubmit()"> Logout</a>
					</h2>
				</c:if>
		
			</sec:authorize>
			<ul>
				<li>Lorem Ipsum is simply dummy text of the printing and
					typesetting industry.</li>
				<li>Lorem Ipsum has been the industry's standard dummy text ever
					since the 1500s, when an unknown printer took a galley of type and
					scrambled it to make a type specimen book.</li>
				<li>It has survived not only five centuries, but also the leap
					into electronic typesetting, remaining essentially unchanged.</li>
				<li>It was popularised in the 1960s with the release of Letraset
					sheets containing Lorem Ipsum passages, and more recently with
					desktop publishing software like Aldus PageMaker including versions
					of Lorem Ipsum.</li>
			</ul>
			<sec:authorize access="hasRole('ROLE_USER')">
				<p><a href="javascript:startExam();">Start Exam</a></p>
			</sec:authorize>	
		</div>
		<div id="footer">
			<p>&copy; 2015, All rights reserved.</p>
		</div>
	</div>
</body>
</html>