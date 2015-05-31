<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>jQuery UI Effects - Effect demo</title>
        <link href="resources/themes/smoothness/jquery-ui.css" rel="stylesheet">
        <script src="resources/js/jquery-1.11.3.js"></script>
        <script src="resources/js/jquery-ui.js"></script>
        <link href="resources/css/style.css" rel="stylesheet">     
        <script>
            $ (function () {                
				// display popup with form data
				$(".newbox").click(function () {
					questionId = $.trim($(this).text());
					$("#form-panel").show();
				});
				
				// popup button to take action
				$("#cancelBtn, #okBtn, #reviewBtn").click(function() {
					$("#form-panel").hide(500);					
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
		<style type="text/css">
			p.para-question, ul.choices-list li input { letter-spacing: 1px; }
			ul.choices-list { list-style: none; padding: 0px; margin: 0px; padding-bottom: 5px; }
			ul.choices-list li { padding-bottom: 10px;	}
			
			#mycustomscroll {
				height: 310px;
				overflow: auto;
				position: relative;
			}
		</style>
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
			<div id="form-panel" style="display: none;">
				<div class="ui-overlay">
					<div class="ui-widget-overlay"></div>
					<div class="ui-widget-shadow ui-corner-all"
						style="width: 712px; height: 372px; position: absolute; left: 0px; top: 0px;"></div>
				</div>
				<div class="ui-widget ui-widget-content ui-corner-all"
					style="position: absolute; width: 690px; height: 350px;left: 0px; top: 0px;padding: 10px;">
					<div class="ui-dialog-content ui-widget-content"
						style="background: none; border: 0;">
						<div id="mycustomscroll">
							<p class="para-question">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
								tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
								quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
								consequat?Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
								tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
								quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
								consequat?Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
								tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
								quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
								consequat?Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
								tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
								quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
								consequat?Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
								tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
								quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
								consequat?Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
								tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
								quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
								consequat?Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
								tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
								quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
								consequat?Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
								tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
								quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
								consequat?Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
								tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
								quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
								consequat?</p>
							<ul class="choices-list" >
								<li><input type="radio" name="answer" id="answer1" /><label for="answer1">The best option will be a</label></li>
								<li><input type="radio" name="answer" id="answer2" /><label for="answer2">The best option will be b</label></li>
								<li><input type="radio" name="answer" id="answer3" /><label for="answer3">The best option will be c</label></li>
								<li><input type="radio" name="answer" id="answer4" /><label for="answer4">The best option will be d</label></li>
							</ul>
						</div>
						<div style="position: absolute; bottom: 10px;">
							<input id="okBtn" type="button" value="OK" />
							<input id="cancelBtn" type="button" value="Cancel" />
							<input id="reviewBtn" type="button" value="Review later" />
						</div>
					</div>
				</div>
			</div>
			
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
			
			<div style="margin-bottom: 10px;"> <button id="submitBtn">Submit Test</button> </div>
		</div>		
</div>
    </body>
</html>