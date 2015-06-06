<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="true"%>
<html>
<head>
	<title>Aptikraft Online Exam Application - LoginPage</title>
	<link href="resources/themes/smoothness/jquery-ui.css" rel="stylesheet">
	<link rel="stylesheet" href="resources/css/style.css" />
	<script src="resources/js/jquery-1.11.3.js"></script>
	<script src="resources/js/jquery-ui.js"></script>
	<script src="resources/js/js-util.js"></script>
	<script src="resources/js/form-validation.js"></script>
	<script type="text/javascript">
		$(function(){
			
			$("form[name='loginForm']").submit(function(event){
				
				// Stopping form being submitted when click submit button
				event.preventDefault();
				
				// Form validation
				if(!validateLoginForm()) {
					return false;
				}

				// Get form and call POST request using jQuery.ajax()
				var form = $(this);
				$.ajax({
					type: "POST",
					url: form.attr("action"),
					data: form.serialize(),
					success: function(data) {
						
						if(data.status == "success") {
							var url = "";
							if(data.data[0].name == "url") {
								url = data.data[0].value + '?<c:out value="${_csrf.parameterName}=${_csrf.token}"/>';
								url = data.data[0].value;
							}
							// Opening up chhild window to start examiner session.
							// var winObj = openChildWindow(url, "Aptikraft Online Exam Application", "width=550,height=170,0,status=0,location=no,resizable=no,toolbar=no");
							// winObj.locationbar.visible = false;
							
							window.location.replace(url);
						} else if( data.status == "error") {
							if(data.data[0].name == "errorMessage") {
								$(".form-error").text(data.data[0].value).show();
							}
						}
						
					},
					error: function(err) {
						alert("LoginPage :: Server error : " + err);
					}
				});
			});		
		});
	</script>
</head>
<body onload='document.loginForm.username.focus();'>
	<div id="page-container" class="ui-widget ui-widget-content ui-corner-all no-border">
		<div id="content-body">
			<h4>Aptikraft Online Exam Application</h4>
		
			<c:if test="${not empty error}">
				<div class="error">${error}</div>
			</c:if>
			
			<div class="form-error"></div>
			
			<c:if test="${not empty msg}">
				<div class="msg">${msg}</div>
			</c:if>
			<form name='loginForm' action="<c:url value='/j_spring_security_check' />">
				<p class="heading-style1">User Login</p>
				<p><span>Username : </span><input type="text" name="username" /></p>
				<p><span>Password : </span><input type="password" name="password" /></p>
				<p>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<input name="submit" type="submit" value="Login" class=".ui-widget button" />
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
