// Global variable for storing and accessing json data
var globalJsonObj = null;
var questionJsonIndex = null;

/*----------------------------------------------------------------
- 1. On loading page, fetch question details by ajax GET request
- 2. Generating gird panel with question boxes.
- 3. Registering and starting timer as soon as page loaded completely.
*/
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
        
        $("#mycustomscroll").on("change", "input[name='answer']:radio", answerRadioButtonClickHandler);

        // display popup with form data and register click event handler
        $("#grid-panel").on("click", ".newbox", newBoxClickHandler);
        
        // adding countdown timer
        var dateObj = new Date();
        dateObj.setMinutes(dateObj.getMinutes()+minutesToAdd);
        $('#clock').countdown(dateObj).on('update.countdown', function(event) {
        	str = "";
        	if(minutesToAdd >=60) {
        		str = '<span>%H</span> hr '
       	     + '<span>%M</span> min '
       	     + '<span>%S</span> sec';
        	} else {
        		str = '<span>%M</span> min '
              	     + '<span>%S</span> sec';
        	}
       	   var $this = $(this).html(event.strftime(str));
        	 }).on('finish.countdown', function(event){
        		 // automatically save details when given time elapsed.
        		 saveDetailsMethod();
        	 });
    },
    type      : 'GET',
    url       : 'getExamDetailsInJSON'
});

newBoxClickHandler = function () {
    var qFormObj = $("#questionForm");
    qFormObj.empty();

    qId               = parseInt($.trim($(this).text()));
    questionJsonIndex = qId - 1;
    var qJsonObj = globalJsonObj.questionList[questionJsonIndex];

    // Updating qId as popupId for later use in okBtn, resetBtn, reviewBtn 
    $("input[name='popupId']").val(qId);
    
    //Disable okBtn, reviewBtn when no radio button selected
    if(getSelectedAnswer() == null) {
    	$("#okBtn, #reviewBtn").attr("disabled", true);    	
    }

    // create question form with question details and its choices.
    createQuestionPopup(qJsonObj, qFormObj);

    var answer = getSelectedAnswer();
    if (answer != null) {
        $("input[name='answer'][value=" + answer + "]").attr("checked", true);
    }
    $("#form-container").show();
}

answerRadioButtonClickHandler = function() {
	$("#okBtn, #reviewBtn").removeAttr("disabled");
}

function setSelectedAnswer(answer) {
    globalJsonObj.questionList[questionJsonIndex].answer = answer;
}

function getSelectedAnswer() {
	return globalJsonObj.questionList[questionJsonIndex].answer;
}