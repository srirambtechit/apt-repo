<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="true"%>
<html>
<head>
<title>${title}</title>
<link href="resources/themes/smoothness/jquery-ui.css" rel="stylesheet">
<link rel="stylesheet" href="resources/css/style.css" />
<script src="resources/js/jquery-1.11.3.js"></script>
<script type="text/javascript">
	function goToApplication() {
		var url = "loginPage";
		var title = "Aptikraft Online Exam Application - LoginPage";
		var features = "status=0, toolbar=0, width=500, height=500";
		var winObj = window.open(url, title, features);
		winObj.moveTo(0, 0);
		winObj.resizeTo(screen.availWidth, screen.availHeight);
		
		// Moving the current window login page to alertPage to inform user.
		window.location.replace("alertPage.jsp");
	}
</script>
</head>
<body>
	<div id="page-container"
		class="ui-widget ui-widget-content ui-corner-all no-border">
		<div id="content-body">
			<h4>Aptikraft Online Exam Application</h4>
			<a href="javascript:goToApplication()">Go to Application</a>
		</div>
		<div id="footer">
			<p>&copy; 2015, All rights reserved.</p>
		</div>
	</div>
</body>
</html>