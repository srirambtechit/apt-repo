<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
	<head>
		<title>${title}</title>
	</head>
    <body>
        <h1>Message : ${message}</h1>
        <c:url value="/j_spring_security_logout" var="logoutUrl"/>
        <form action="${logoutUrl}" id="logoutForm" method="post">
            <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
        </form>
        <script>
            function formSubmit() {
                document.getElementById("logoutForm").submit();
            }
        </script>
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <h2>
                Welcome : ${pageContext.request.userPrincipal.name} | <a href="javascript:formSubmit()">Logout</a>
            </h2>
        </c:if>
    </body>
</html>