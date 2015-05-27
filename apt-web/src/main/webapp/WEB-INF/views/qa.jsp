<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<html>
<head>
<title>Question - Answer Page</title>
</head>
<body>
	<h1>Examination</h1>
	<c:url var="saveForm" value="/saveAnswer" />

	<form:form id="saveForm" action="${saveForm}" method="POST"
		modelAttribute="questionWrapper">

		<table>
			<c:set var="no" value="0"></c:set>
			<c:forEach items="${questionWrapper.questionList}" varStatus="status">
				<tr>
					<c:set var="qID" value="${questionWrapper.questionList[no].id}" />
					
					<td align="center">${no}</td>
					<td><c:out value="${questionWrapper.questionList[no].question}"></c:out></td>
					<td><form:radiobuttons path="questionList[${status.index}].answer" items="${choiceMap.get(qID)}" /></td>
					
					<form:hidden path="questionList[${status.index}].id" />
					<form:hidden path="questionList[${status.index}].question" />
					<c:forEach items="${choiceMap.get(qID)}" varStatus="chs">
						<input type="hidden" name="questionList[${status.index}].choiceList[${chs.index}]" value="${choiceMap.get(qID).get(chs.index)}" />
					</c:forEach>
					
					<c:set var="no" value="${no+1}"></c:set>
				</tr>
			</c:forEach>
			<tr>
				<td><button>submit</button></td>
			</tr>
		</table>

	</form:form>
</body>
</html>