<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Question - Answer Page</title>
        <link href="resources/themes/smoothness/jquery-ui.css" rel="stylesheet">
        <script src="resources/js/jquery-1.11.3.js"></script>
        <script src="resources/js/jquery-ui.js"></script>
        <script src="resources/js/jquery.dform-1.1.0.js"></script>
        <script src="resources/js/js-util.js"></script>
        <link href="resources/css/style.css" rel="stylesheet">        
        <script>
        	/*----------------------------------------------------------------
        		- 1. On loading page, fetch question details by ajax GET request
	    		- 2. Generating gird panel with question boxes.
        	*/
	        // Global variable for storing and accessing json data
        	var globalJsonObj = null;
        	var questionJsonIndex = null;
	    	$.ajax({
			  type: 'GET',
			  url: 'getExamDetailsInJSON',
			  beforeSend:function(){
			    // this is where we append a loading image
			    /* $('#ajax-panel').html('<div class="loading"><img src="/images/loading.gif" alt="Loading..." /></div>'); */
			  },
			  success:function(data){
			    // question details fetched from DB as JSON object
			    globalJsonObj = data;
			    
			    noOfQuestion = parseInt(globalJsonObj.noOfQuestion);
			    
			    // question grid generation
	        	createQuestionGridPanel(noOfQuestion, $("#grid-panel"));
	        	
	        	// display popup with form data and register click event handler
	        	$("#grid-panel").on("click", ".newbox", newBoxClickHandler);
			  },
			  error:function() {
			    // failed request; give feedback to user
			    $('#ajax-panel').html('<p class="error"><strong>Oops!</strong> Try that again in a few moments.</p>');
			  }
			});
	    	
	    	/*----------------------------------------------------------------*/
            $ (function () {
				// popup button to take action
				$("#cancelBtn, #okBtn, #reviewBtn").click(function() {
					
					var btnName = this.name;
					var popupId = $("input[name='popupId']").val();
					var box = $("#newBox"+popupId);
					
					// reomve if already class added
					box.removeClass("mark-noanswer mark-complete mark-review");
					
					if(btnName == "ok") {
						// check any one of radio button selected.
						if($("input[name='answer']").is(":checked")) {
							answer = $("input[name='answer']:checked").val();
 							globalJsonObj.questionList[questionJsonIndex].answer = answer;
							box.addClass("mark-complete");	
						} else {
							box.addClass("mark-noanswer");	
						}
					} else if(btnName == "cancel") {
						if($("input[name='answer']").is(":checked")) {
							box.addClass("mark-complete");
						} else {
							box.addClass("mark-noanswer");							
						}
					} else if(btnName == "review") {
						box.addClass("mark-review");							
					}
					$("#form-container").hide(500);						
                    return false;
				});
				
				// on-submit confirmation
				$("#submitBtn").click(function() {				
					if(confirm("Are you sure want to submit the test?"))
						; // proceed for form submittion action
					else
						return false;
				});
            });
	    	
            newBoxClickHandler = function() {
				var qFormObj = $("#questionForm");
				qFormObj.empty();
				
				qId = parseInt($.trim($(this).text()));
				questionJsonIndex = qId - 1;
				var qJsonObj = globalJsonObj.questionList[questionJsonIndex];
				
				// Updating qId as popupId for later use in okBtn, cancelBtn, reviewBtn 
				$("input[name='popupId']").val(qId);
				
				createQuestionPopup(qJsonObj, qFormObj);
				
				var answer = globalJsonObj.questionList[questionJsonIndex].answer;
				if(answer != null) {
					$("input[name='answer'][value="+answer+"]").attr("checked", true);	
				}
				$("#form-container").show();
			}
        </script>
		
    </head>
    <body>
	<div id="apt-page-container" style="width: 1070px; margin: auto;">
		<div id="apt-left-container" style="position: relative; margin: 50px 1px 50px 50px;padding: 50px;border: 1px solid #ddd;display: table;float: left;">
			
			<!-- jQuery will generate grid panel using below div -->
			<div id="grid-panel"></div>
			
			<!-- ui-dialog -->
			<div id="form-container" style="display: none;">
				<div class="ui-overlay">
					<div class="ui-widget-overlay"></div>
					<div class="ui-widget-shadow ui-corner-all"
						style="width: 702px; height: 232px; position: absolute; left: 50px; top: 50px;"></div>
				</div>
				<div class="ui-widget ui-widget-content ui-corner-all"
					style="position: absolute; width: 680px; height: 210px;left: 50px; top: 50px;padding: 10px;">
					<div id="form-panel" class="ui-dialog-content ui-widget-content"
						style="background: none; border: 0;">
						
						<form id="questionForm"></form>
						
						<!-- jQuery purpose only -->
						<input type="hidden" name="popupId" value="" />
						
						<input id="okBtn" type="button" name="ok" value="OK" />
						<input id="cancelBtn" type="button" name="cancel" value="Cancel" />
						<input id="reviewBtn" type="button" name="review" value="Review later" />
					</div>
				</div>
			</div>
			
			<!-- Notification panel for capturing errors or message  -->
			<div id="ajax-panel"></div>
			
		</div>
		
		<div id="apt-right-container" style="width: 200px; height: 190px; margin: 50px 0px 0px 0px;padding: 10px;isplay: table;float: left; text-align: right;">
			<p>Hi Akash</p>
			<p>120:59</p>
						
			<div style="margin-bottom: 10px;"> 
				<span class="default-indicator"></span>Yet to attend
			</div>
			
			
			<div style="margin-bottom: 10px;"> 
				<span class="green-indicator"></span>Completed
			</div>
			
			<div style="clear:both;"></div>
			
			<div style="margin-bottom: 10px;"> 
				<span class="yellow-indicator"></span>Need to review
			</div>
			
			<div style="clear:both;"></div>
			
			<div style="margin-bottom: 10px;">
				<span class="red-indicator"></span>Not answered
			</div>
			
			
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
				</table>
				<div style="margin-bottom: 10px;"> <button id="submitBtn">Submit Test</button> </div>		
			</form:form>
		</div>		
	</div>
    </body>
</html>