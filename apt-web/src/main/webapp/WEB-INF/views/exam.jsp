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
            $ (function () {
            	var questionJSON = null;
            	// on loading page, fetch question details by ajax GET request
            	$.ajax({
				  type: 'GET',
				  url: 'getExamDetailsInJSON',
				  /* data: { postVar1: 'theValue1', postVar2: 'theValue2' }, */
				  beforeSend:function(){
				    // this is where we append a loading image
				    /* $('#ajax-panel').html('<div class="loading"><img src="/images/loading.gif" alt="Loading..." /></div>'); */
				  },
				  success:function(data){
				    // successful request; do something with the data
				    questionJSON = data; /* JSON.stringify(data); */
				  },
				  error:function() {
				    // failed request; give feedback to user
				    $('#ajax-panel').html('<p class="error"><strong>Oops!</strong> Try that again in a few moments.</p>');
				  }
				});
            	
				// display popup with form data
				$(".newbox").click(function () {
					
					var qFormObj = $("#questionForm");
					qFormObj.empty();
					
					qId = parseInt($.trim($(this).text()));
					qObj = questionJSON.questionList[qId - 1];
					
					createQuestionPopup(qObj, qFormObj);
					
// 					$("#ajax-panel").text(JSON.stringify(qObj));
					$("#form-container").show();
				});
				
				// popup button to take action
				$("#cancelBtn, #okBtn, #reviewBtn").click(function() {
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
			
        </script>
		
    </head>
    <body>
<div style=" width: 1070px; margin: auto; ">
		<div style="position: relative; margin: 50px 1px 50px 50px;padding: 50px;border: 1px solid #ddd;display: table;float: left;">
		
			<div class="mark-complete newbox top-left-corner-cell">1</div>
			<div class="newbox top-mid-edge-cell">2</div>
			<div class="mark-review newbox top-mid-edge-cell">3</div>
			<div class="newbox top-right-corner-cell">4</div>
			
			<div style="clear:both;"></div>
			
			<div class="newbox left-mid-edge-cell">5</div>
			<div class="mark-unattend newbox inner-cell">6</div>
			<div class="newbox inner-cell">7</div>
			<div class="mark-noanswer newbox right-mid-edge-cell">8</div>
			
			<div style="clear:both;"></div>
			
			<div class="newbox left-mid-edge-cell">9</div>
			<div class="newbox inner-cell">10</div>
			<div class="newbox inner-cell">11</div>
			<div class="newbox right-mid-edge-cell">12</div>
			
			<div style="clear:both;"></div>
			
			<div class="newbox bottom-left-corner-cell">13</div>
			<div class="newbox bottom-mid-edge-cell">14</div>
			<div class="newbox bottom-mid-edge-cell">15</div>
			<div class="newbox bottom-right-corner-cell">16</div>
			
			<div style="clear:both;"></div>
			
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
						
						<input id="okBtn" type="button" value="OK" />
						<input id="cancelBtn" type="button" value="Cancel" />
						<input id="reviewBtn" type="button" value="Review later" />
					</div>
				</div>
			</div>
			
			<div id="ajax-panel"></div>
			
		</div>
		
		<div style="width: 200px; height: 190px; margin: 50px 0px 0px 0px;padding: 10px;isplay: table;float: left; text-align: right;">
			<p>Hi Akash</p>
			<p>120:59</p>
						
			<div style="margin-bottom: 10px;"> 
				<span class="default-indicator"></span>
				<!-- <img src="tick.png" width="50px;" height="20px" alt="Completed" /> -->Yet to attend
			</div>
			
			
			<div style="margin-bottom: 10px;"> 
				<span class="green-indicator"></span>
				<!-- <img src="tick.png" width="50px;" height="20px" alt="Completed" /> -->Completed
			</div>
			
			<div style="clear:both;"></div>
			
			<div style="margin-bottom: 10px;"> 
				<span class="yellow-indicator"></span>
				<!-- <img src="review.png" width="50px;" height="20px" alt="Need to review" /> -->Need to review
			</div>
			
			<div style="clear:both;"></div>
			
			<div style="margin-bottom: 10px;">
				<span class="red-indicator"></span>
				<!-- <img src="xmark.png" width="50px;" height="20px" alt="Not attended" /> -->Not answered
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