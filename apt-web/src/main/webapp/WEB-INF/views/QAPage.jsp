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
        <script src="resources/js/jquery.countdown.js"></script>
        <script src="resources/js/js-util.js"></script>
        <link href="resources/css/style.css" rel="stylesheet">
        <script>
            /*----------------------------------------------------------------
	 - 1. On loading page, fetch question details by ajax GET request
	 - 2. Generating gird panel with question boxes.
	 - 3. Registering and starting timer as soon as page loaded completely.
	 */
            // Global variable for storing and accessing json data
            var globalJsonObj = null;
            var questionJsonIndex = null;
            $.ajax({
                beforeSend: function () {
                    // this is where we append a loading image
                    /* $('#ajax-panel').html('<div class="loading"><img src="/images/loading.gif" alt="Loading..."/></div>'); */},
                error     : function () {
                    // failed request; give feedback to user
                    $('#ajax-panel').html('<p class="error"><strong>Oops!</strong> Try that again in a few moments.</p>');
                },
                success   : function (data) {
                    // question details fetched from DB as JSON object
                    globalJsonObj = data;

                    noOfQuestion  = parseInt(globalJsonObj.noOfQuestion);
                    
                    minutesToAdd = parseInt(globalJsonObj.duration);

                    // question grid generation
                    createQuestionGridPanel(noOfQuestion, $("#grid-panel"));

                    // display popup with form data and register click event handler
                    $("#grid-panel").on("click", ".newbox", newBoxClickHandler);
                    
                    // adding countdown timer
                    var dateObj = new Date();
                    dateObj.setMinutes(dateObj.getMinutes()+minutesToAdd);
                    $('#clock').countdown(dateObj).on('update.countdown', function(event) {
                    	   var $this = $(this).html(event.strftime(''
                    	     + '<span>%H</span> hr '
                    	     + '<span>%M</span> min '
                    	     + '<span>%S</span> sec'));
                    	 }).on('finish.countdown', function(event){
                    		 // automatically save details when given time elapsed.
                    		 saveDetailsMethod();
                    	 });
                },
                type      : 'GET',
                url       : 'getExamDetailsInJSON'
            });

            /*----------------------------------------------------------------*/
            $(function () {
                // popup button to take action
                $("#cancelBtn, #okBtn, #reviewBtn").click(function () {

                    var btnName = this.name;
                    var popupId = $("input[name='popupId']").val();
                    var box = $("#newBox" + popupId);

                    // reomve if already class added
                    box.removeClass("mark-noanswer mark-complete mark-review");

                    if (btnName == "ok") {
                        // check any one of radio button selected.
                        if ($("input[name='answer']").is(":checked")) {
                            answer                                               = $("input[name='answer']:checked").val();
                            globalJsonObj.questionList[questionJsonIndex].answer = answer;
                            box.addClass("mark-complete");
                        } else {
                            box.addClass("mark-noanswer");
                        }
                    } else if (btnName == "cancel") {
                        if ($("input[name='answer']").is(":checked")) {
                            box.addClass("mark-complete");
                        } else {
                            box.addClass("mark-noanswer");
                        }
                    } else if (btnName == "review") {
                        box.addClass("mark-review");
                    }
                    $("#form-container").hide(500);
                    return false;
                });

                // on-submit confirmation

                function saveDetailsMethod() {
                	$.ajax({
                        async      : false,
                        cache      : false,
                        contentType: "application/json; charset=utf-8",
                        data       : JSON.stringify(globalJsonObj),
                        error      : function () {
                            // failed request; give feedback to user
                            $('#ajax-panel').html(JSON.stringify(globalJsonObj) + '<p class="error"><strong>Oops!</strong> Try that again in a few moments.</p>');
                        },
                        processData: false,
                        success    : function (data) {
                            if (data.status == 'OK') {
                                alert('Congratulation!!! You have successfully completed exam.');
                                // similar behavior as an HTTP redirect
                                //window.location.replace("index.jsp");
                                window.close();

                                // similar behavior as clicking on a link
                                // window.location.href = "http://stackoverflow.com";
                            } else {
                                $('#ajax-panel').html('<p class="error"><strong>Oops!</strong> Failed : ' + data.status + ', ' + data.errorMessage + '</p>');
                            }
                        },
                        type       : 'POST',
                        // spring security expectes when csrf enabled, hence appending with http POST request
                        url        : 'saveAnswerDetailsFromJSON?${_csrf.parameterName}=${_csrf.token}'
                    });
                }

                $("#submitBtn").click(function () {
                    if (confirm("Are you sure want to submit the test?")) {
                    	saveDetailsMethod();
                    } else {
                        return false;
                    }
                });
            });

            newBoxClickHandler = function () {
                var qFormObj = $("#questionForm");
                qFormObj.empty();

                qId               = parseInt($.trim($(this).text()));
                questionJsonIndex = qId - 1;
                var qJsonObj = globalJsonObj.questionList[questionJsonIndex];

                // Updating qId as popupId for later use in okBtn, cancelBtn, reviewBtn 
                $("input[name='popupId']").val(qId);

                createQuestionPopup(qJsonObj, qFormObj);

                var answer = globalJsonObj.questionList[questionJsonIndex].answer;
                if (answer != null) {
                    $("input[name='answer'][value=" + answer + "]").attr("checked", true);
                }
                $("#form-container").show();
            }
        </script>
    </head>
    <body>
    	
        <div id="apt-page-container" style="width: 1070px; margin: auto;">
        	<h4>Aptikraft Online Exam Application</h4>
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
                            style="width: 702px; height: 232px; position: absolute; left: 50px; top: 50px;"></div>
                    </div>
                    <div class="ui-widget ui-widget-content ui-corner-all"
                        style="position: absolute; width: 680px; height: 210px; left: 50px; top: 50px;
                        padding: 10px;">
                        <div class="ui-dialog-content ui-widget-content" id="form-panel"
                            style="background: none; border: 0;">
                            <form id="questionForm"></form>
                            <!-- jQuery purpose only -->
                            <input name="popupId" type="hidden" value=""/>
                            <input id="okBtn" name="ok" type="button" value="OK"/>
                            <input id="cancelBtn" name="cancel" type="button" value="Cancel"/>
                            <input id="reviewBtn" name="review" type="button" value="Review later"/>
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
                <div id="clock"></div>
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
                <div style="margin-bottom: 10px;">
                    <button id="submitBtn">Submit Test</button>
                </div>
            </div>
        </div>
    </body>
</html>