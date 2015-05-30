function createQuestionGridPanel(noOfQuestion, target) {

	var qCount = parseInt(noOfQuestion);

	// Calculating rows to form Grid Panel
	var rows = 0;
	if (qCount % 4 == 0)
		rows = parseInt(qCount / 4);
	else
		rows = parseInt(qCount / 4) + 1;

	var counter = 1;
	// Constructing first row
	if (qCount != 0 && rows >= 1) {
		if (qCount != 0) {
			newTopLeftCornerCell(counter++).appendTo(target);
			qCount--;
		}
		if (qCount != 0) {
			newTopMiddleEdgeCell(counter++).appendTo(target);
			qCount--;
		}
		if (qCount != 0) {
			newTopMiddleEdgeCell(counter++).appendTo(target);
			qCount--;
		}
		if (qCount != 0) {
			newTopRightCornerCell(counter++).appendTo(target);
			qCount--;
		}
		newClearDiv().appendTo(target);
	}
	// Constructing all middle rows
	if (qCount != 0 && rows >= 2) {

		var midRows = rows - 2; // first and last were taken care separately

		while (midRows != 0) {
			if (qCount != 0) {
				newMiddleLeftEdgeCell(counter++).appendTo(target);
				qCount--;
			}
			if (qCount != 0) {
				newInnerCell(counter++).appendTo(target);
				qCount--;
			}
			if (qCount != 0) {
				newInnerCell(counter++).appendTo(target);
				qCount--;
			}
			if (qCount != 0) {
				newMiddleRightEdgeCell(counter++).appendTo(target);
				qCount--;
			}
			newClearDiv().appendTo(target);
			midRows--;
		}
	}
	// Constructing last row
	if (qCount != 0 && rows >= 3) {
		if (qCount != 0) {
			newBottomLeftCornerCell(counter++).appendTo(target);
			qCount--;
		}
		if (qCount != 0) {
			newBottomMiddleEdgeCell(counter++).appendTo(target);
			qCount--;
		}
		if (qCount != 0) {
			newBottomMiddleEdgeCell(counter++).appendTo(target);
			qCount--;
		}
		if (qCount != 0) {
			newBottomRightCornerCell(counter).appendTo(target);
			qCount--;
		}
		newClearDiv().appendTo(target);
	}
}

function newClearDiv() {
	return $("<div/>", {
		"style" : "clear:both;"
	});
}

function newTopLeftCornerCell(data) {
	return $("<div/>", {
		"id" : "newBox" + data,
		"class" : "newbox top-left-corner-cell"
	}).text(data);
}

function newTopMiddleEdgeCell(data) {
	return $("<div/>", {
		"id" : "newBox" + data,
		"class" : "newbox top-mid-edge-cell"
	}).text(data);
}

function newTopRightCornerCell(data) {
	return $("<div/>", {
		"id" : "newBox" + data,
		"class" : "newbox top-right-corner-cell"
	}).text(data);
}

function newBottomLeftCornerCell(data) {
	return $("<div/>", {
		"id" : "newBox" + data,
		"class" : "newbox bottom-left-corner-cell"
	}).text(data);
}

function newBottomMiddleEdgeCell(data) {
	return $("<div/>", {
		"id" : "newBox" + data,
		"class" : "newbox bottom-mid-edge-cell"
	}).text(data);
}

function newBottomRightCornerCell(data) {
	return $("<div/>", {
		"id" : "newBox" + data,
		"class" : "newbox bottom-right-corner-cell"
	}).text(data);
}

function newMiddleLeftEdgeCell(data) {
	return $("<div/>", {
		"id" : "newBox" + data,
		"class" : "newbox left-mid-edge-cell"
	}).text(data);
}

function newInnerCell(data) {
	return $("<div/>", {
		"id" : "newBox" + data,
		"class" : "newbox inner-cell"
	}).text(data);
}

function newMiddleRightEdgeCell(data) {
	return $("<div/>", {
		"id" : "newBox" + data,
		"class" : "newbox right-mid-edge-cell"
	}).text(data);
}

function createQuestionPopup(jsonObj, target) {
	$("<p/>").text(jsonObj.question + "; ans = " + jsonObj.answer).appendTo(
			target);

	var ul = $("<ul/>");

	for (key in jsonObj.choiceMap) {
		var li = $("<li/>");
		var radio = $("<input/>", {
			"type" : "radio",
			"name" : "answer",
			"value" : key,
		});
		radio.appendTo(li);
		li.append(jsonObj.choiceMap[key]);
		li.appendTo(ul);
	}
	ul.appendTo(target);
}
