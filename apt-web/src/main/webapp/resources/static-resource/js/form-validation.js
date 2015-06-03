/**
 * function to validate login form
 * 
 * @returns {Boolean}
 */
function validateLoginForm() {
	var formError = $(".form-error");
	var isValid = true;
	formError.hide();
	
	// hide if already spring error/msg block is displayed
	$(".error, .msg").hide();	

	var inputs = $("form[name='loginForm']").find('input');

	for (i = 0; i < inputs.length; i++) {
		element = inputs[i];
		isValid = false;
		if (element.name == "username") {
			if (element.value == "") {
				formError.text("Username can't be blank.");
				break;
			}
		} else if (element.name == "password") {
			if (element.value == "") {
				formError.text("Password can't be blank.");
				break;
			}
		}
		isValid = true;
	}
	if (!isValid) {
		formError.show();
	}
	return isValid;
}

/**
 * function to validate registration form
 * 
 * @returns {Boolean}
 */
function validateRegistrationForm() {
	var formError = $(".form-error");
	var isValid = true;
	formError.hide();
	
	// hide if already spring error/msg block is displayed
	$(".error").hide();

	var inputs = $("form[name='registerForm']").find('input');

	var passwordValue = "";
	var confirmPasswordValue = "";

	for (i = 0; i < inputs.length; i++) {
		element = inputs[i];
		isValid = false;
		if (element.name == "name") {
			if (element.value == "") {
				formError.text("Name can't be blank.");
				break;
			}
			var re = /^[A-Za-z ]+$/;
			if (!re.test(element.value)) {
				formError.text("Name is invalid and mustn't contain number, blank, space or any special characters");
				break;
			}
		} else if (element.name == "rollNumber") {
			if (element.value == "") {
				formError.text("Roll number can't be blank.");
				break;
			}
		} else if (element.name == "password") {
			passwordValue = element.value;
			if (element.value == "") {
				formError.text("Password can't be blank.");
				break;
			}
		} else if (element.name == "cpassword") {
			confirmPasswordValue = element.value;
			if (element.value == "") {
				formError.text("Confirm password can't be blank.");
				break;
			}
		} else if (passwordValue != confirmPasswordValue) {
			formError.text("Password mismatch.");
			break;
		} else if (element.name == "email") {
			if (element.value == "") {
				formError.text("Email can't be blank.");
				break;
			}
			var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
			if (!re.test(element.value)) {
				formError.text("Email is invalid.");
				break;
			}
		} else if (element.name == "mobileNumber") {
			if (element.value == "") {
				formError.text("Mobile number can't be blank.");
				break;
			}
			var re = /^\d{10}$/;
			if (!re.test(element.value)) {
				formError.text("Mobile number is invalid.");
				break;
			}
		}
		isValid = true;
	}

	if (!isValid) {
		formError.show();
	}
	return isValid;

}
