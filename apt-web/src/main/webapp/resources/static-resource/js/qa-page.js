$(function () {
	
    // popup button to take action
    $("#resetBtn, #okBtn, #reviewBtn").click(function () {

        var btnName = this.name;
        var popupId = $("input[name='popupId']").val();
        var box = $("#newBox" + popupId);

        // reomve if already class added
        box.removeClass("mark-noanswer mark-complete mark-review");
        
        if (btnName == "ok") {
            // check any one of radio button selected.
            if ($("input[name='answer']").is(":checked")) {
                answer = $("input[name='answer']:checked").val();
                setSelectedAnswer(answer);                            
                box.addClass("mark-complete");
            } else {
                box.addClass("mark-noanswer");
            }
        } else if (btnName == "reset") {
            setSelectedAnswer(null);
            $("input[name='answer']").removeAttr("checked");
            $("#okBtn, #reviewBtn").attr("disabled", true);
            box.addClass("mark-noanswer");
        } else if (btnName == "review") {
        	answer = $("input[name='answer']:checked").val();
            setSelectedAnswer(answer);
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