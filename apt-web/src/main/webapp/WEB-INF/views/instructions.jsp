<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Common Instructions</title>
</head>
<body>
	<h2>Examination Instructions</h2>
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
	<table>
		<tr>
			<td colspan='2'><a href="<c:url value='startExamPage' />">Start Exam</a></td>
		</tr>
	</table>
</body>
</html>