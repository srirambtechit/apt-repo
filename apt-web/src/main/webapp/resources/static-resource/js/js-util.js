function createQuestionPopup(jsonObj, target) {
	$("<p/>").text(jsonObj.question).appendTo(target);
	
	var ul = $("<ul/>");
	
	for(key in jsonObj.choiceMap) {
		var li = $("<li/>");		
		var radio = $("<input/>", {
			"type":"radio",
			"name": "answer",
			"value": key,
		});
		radio.appendTo(li);
		li.append(jsonObj.choiceMap[key]);
		li.appendTo(ul);
	}
	ul.appendTo(target);
}


