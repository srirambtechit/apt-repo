<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Question - Answer Page</title>
        <link href="resources/themes/smoothness/jquery-ui.css" rel="stylesheet">
        <script src="resources/js/jquery-1.11.3.js"></script>
        <script src="resources/js/jquery-ui.js"></script>
        <script src="resources/js/jquery.countdown.js"></script>
        <script src="resources/js/js-util.js"></script>
        <script src="resources/js/qa-page-onload.js"></script>
        <script src="resources/js/qa-page.js"></script>
        <link href="resources/css/style.css" rel="stylesheet">
        <script type="text/javascript">
        <%-- Globally declared variable csrfToken for spring mvc POST request --%>
        var csrfToken = '<c:out value="${_csrf.parameterName}=${_csrf.token}" />';
        	
        $(function(){
        	// slight update to account for browsers not supporting e.which
    		function disableF5(e) { if ((e.which || e.keyCode) == 116) e.preventDefault(); };
    		$(document).on("keydown", disableF5);
    		
        	$("#submitBtn").click(function () {
                if (confirm("Are you sure want to submit the test?")) {
                	saveDetailsMethod('saveAnswerDetailsFromJSON?'+csrfToken);
                } else {
                    return false;
                }
            });
        	
        	// to restrict user to refersh loaded page
        	window.location.replace("startExamPage#");
        });
        </script>
    </head>
    <body>
        <div id="apt-page-container" style="width: 1070px; margin: auto;">
        	<h4>Aptikraft Online Exam Application</h4>
        	<div style="font-size: 12px; margin-left: 50px; position: relative; top: 45px;">
        		Click on appropriate boxes numbered with question for providing answer.
        	</div>
            <div id="apt-left-container"
                style="position: relative; margin: 50px 1px 50px 50px; padding: 50px; border: 1px
                solid #ddd; display: table; float: left;">
                
                <!-- jQuery will generate grid panel using below div -->
                <div id="grid-panel"></div>
                
                <!-- ui-dialog -->
	  			<div id="form-container" style="display: none;">
	                <div class="ui-overlay">
						<div class="ui-widget-overlay"></div>
						<div class="ui-widget-shadow ui-corner-all"
							style="width: 712px; height: 372px; position: absolute; left: 0px; top: 0px;"></div>
					</div>
					<div class="ui-widget ui-widget-content ui-corner-all"
						style="position: absolute; width: 690px; height: 350px;left: 0px; top: 0px;padding: 10px;">
						<div id="form-panel" class="ui-dialog-content ui-widget-content"
							style="background: none; border: 0;">
							<div id="mycustomscroll">
	                            <form id="questionForm"></form>                           
	                        </div>
							<div style="position: absolute; bottom: 10px;">
								<%--  jQuery purpose only which holds questionId --%>
	                            <input name="popupId" type="hidden" value=""/>
	                            <input id="okBtn" name="ok" type="button" value="OK" disabled="disabled" />
	                            <input id="resetBtn" name="reset" type="button" value="Reset"/>
	                            <input id="reviewBtn" name="review" type="button" value="Review later" disabled="disabled" />
	                         </div>
	                    </div>
	                </div>
       			</div>
                <!-- Notification panel for capturing errors or message  -->
                <div id="ajax-panel"></div>
            </div>
            <div id="apt-right-container"
                style="width: 200px; height: 190px; margin: 50px 0px 0px 0px; padding: 10px;
                display: table; float: left; text-align: right;">
                <c:if test="${pageContext.request.userPrincipal.name != null}">
					<h2>
						User : ${pageContext.request.userPrincipal.name}
					</h2>
				</c:if>
                <div id="clock" class="ui-widget ui-widget-content ui-corner-all" style="border:none;font-size: 15px;font-weight: bolder; color: #00f;" ></div>
                <div style="margin-top: 10px;margin-bottom: 10px;">
                    <span class="default-indicator"></span>Yet to attend
                </div>
                <div style="margin-bottom: 10px;">
                    <span class="green-indicator"></span>Completed
                </div>
                <div style="clear: both;"></div>
                <div style="margin-bottom: 10px;">
                    <span class="yellow-indicator"></span>Need to review
                </div>
                <div style="clear: both;"></div>
                <div style="margin-bottom: 10px;">
                    <span class="red-indicator"></span>Not answered
                </div>
                <div style="margin-bottom: 10px; border: none;" class="ui-widget ui-widget-content ui-corner-all">
                    <button id="submitBtn" class=".ui-widget button">Submit Test</button>
                </div>
            </div>
        </div>
    </body>
</html>