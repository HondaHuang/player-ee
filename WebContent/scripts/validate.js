var message = "";

function isValidUserCredentials() {
	var email = document.getElementById("email").value;
	var password = document.getElementById("password").value;
	var b = false;
	if (email == "" || password == "") {
		message = "Email and Password must be filled";
	} else {
		message = "";
		b = true;
	}
	if (b == true) {
		b = isValidEmail(email);
	}
	if (b == true) {
		b = isValidPassword(password);
	}
	document.getElementById("errMsg").innerText = message;
	return b;
}

function isValidEmail(email) {
	var b1 = false;
	if (message.length == 0 && email.match(/^\w+@\w+\.\w+$/)) {
		b1 = true;
	} else {
		message = "Invalid Email";
	}
	return b1;
}

function isValidPassword(password) {
	var b1 = false;
	if (password.match(/^[a-zA-Z0-9@]{6,20}$/)) {
		b1 = true;
	} else {
		message = "Invalid Password";
	}
	return b1;
}

function clearErr() {
	document.getElementById("errMsg").innerText = "";
	document.getElementById("errorMessage").innerText = "";
}

function clearErr2() {
	document.getElementById("errMsg2").innerText = "";
	document.getElementById("errorMessage2").innerText = "";
}

function isValidPlayerSignUp() {
	var name = document.getElementById("name").value;
	var dob = document.getElementById("dob").value;
	var gender = document.getElementById("gender").value;
	var contact = document.getElementById("contact").value;
	var team = document.getElementById("team").value;
	var email = document.getElementById("email").value;
	var password = document.getElementById("password").value;
	var password2 = document.getElementById("password2").value;
	var b = false;
	if (name == "" || dob == "" || gender == "" || contact == "" || team == ""
			|| email == "" || password == "") {
		message = "All fields must be completed";
	} else {
		message = "";
		b = true;
	}
	if (b == true) {
		b = isValidName(name);
	}
	/*
	 * if (b == true) { b = isValidDob(dob); }
	 */
	/*
	 * if (b == true) { b = isValidGender(gender); }
	 */
	if (b == true) {
		b = isValidContact(contact);
	}
	/*
	 * if (b == true) { b = isValidTeam(team); }
	 */
	if (b == true) {
		b = isValidEmail(email);
	}
	if (b == true) {
		b = isValidPassword(password);
	}
	if (b == true) {
		b = isValidPassword2(password2, password);
	}
	document.getElementById("errMsg").innerText = message;
	return b;
}

function isValidName(name) {
	var b1 = false;
	if (message.length == 0 && name.match(/^[a-zA-Z]{3,20}$/)) {
		b1 = true;
	} else {
		message = "Invalid Name";
	}
	return b1;
}

/*
 * function isValidDob(dob) { if (dob != "") { return true; } else { message =
 * "Date cannot be empty"; } return false; }
 * 
 * function isValidGender(gender) { if (gender != "") { return true; } else {
 * message = "Must pick a gender"; } return false; }
 */

function isValidContact(contact) {
	var b1 = false;
	if (contact.match(/^[0-9]{10}$/)) {
		b1 = true;
	} else {
		message = "Invalid Contact Number";
	}
	return b1;
}

/*
 * function isValidTeam(team) { if (team != "") { return true; } else { message =
 * "Invalid Team"; } return false; }
 */

function isValidPassword2(password2, password) {
	var b1 = false;
	if (password2 == password) {
		b1 = true;
	} else {
		message = "Passwords must be the same";
	}
	return b1;
}

function isValidTeamSignUp() {
	var teamname = document.getElementById("teamname").value;
	var coachname = document.getElementById("coachname").value;
	var b = false;
	if (teamname == "" || coachname == "") {
		message = "Team and coach names are required";
	} else {
		message = "";
		b = true;
	}
	if (b == true) {
		b = isValidTeamname(teamname);
	}
	if (b == true) {
		b = isValidCoachname(coachname);
	}
	document.getElementById("errMsg2").innerText = message;
	return b;
}

function isValidTeamname(teamname) {
	var b1 = false;
	if (teamname.match(/^[a-zA-Z ]{3,50}$/)) {
		b1 = true;
	} else {
		message = "Invalid Team Name";
	}
	return b1;
}

function isValidCoachname(coachname) {
	var b1 = false;
	if (coachname.match(/^[a-zA-Z]{3,50}$/)) {
		b1 = true;
	} else {
		message = "Invalid Coach Name";
	}
	return b1;
}

var option = document.getElementById("option"), search = document
		.getElementById("search");

option.onchange = function() {
	searchChange();
	document.getElementById("errMsg").innerText = "";
}

var teamSearch = document.getElementById("teamsearch");

function teamSearchShow(){
		teamSearch.style.display = "block";
		search.style.display = "none";
}

function teamSearchHide(){
		  teamSearch.style.display = "none";
		  search.style.display = "block";
}

function searchChange() {
	switch (parseInt(option.value)) {
	case 1:
		teamSearchHide();
		search.innerHTML = "<label for='id'>ID: </label><br> <input type='text' id='id' name='searchValue'>";
		break;
	case 2:
		teamSearchHide();
		search.innerHTML = "<label for='name'>Name: </label><br> <input type='text' id='name' name='searchValue'>";
		break;
	case 3:
		teamSearchHide();
		search.innerHTML = "<label for='email'>Email: </label><br> <input type='text' id='email' name='searchValue'>"
		break;
	case 4:
		teamSearchHide();
		search.innerHTML = "<label for='dob'>DOB: </label><br> <input type='date' id='dob' name='searchValue'>";
		break;
	case 5:
		teamSearchHide();
		search.innerHTML = "<label for='contact'>Contact: </label><br> <input type='text' id='contact' name='searchValue'>";
		break;
	case 6:
		teamSearchHide();
		search.innerHTML = "<label for='gender'>Gender: </label><br> <select id='gender' name='searchValue'><option></option><option value=M>Male</option><option value=F>Female</option></select>"
		break;
	case 7:
		teamSearchShow();
		break;
	}
}


function isPlayerSearchValid() {
	if (option.value == 1) {
		var id = document.getElementById("id").value;
	}
	if (option.value == 2) {
		var name = document.getElementById("name").value;
	}
	if (option.value == 3) {
		var email = document.getElementById("email").value;
	}
	if (option.value == 4) {
		var dob = document.getElementById("dob").value;
	}
	if (option.value == 5) {
		var contact = document.getElementById("contact").value;
	}
	if (option.value == 6) {
		var gender = document.getElementById("gender").value;
	}
	if (option.value == 7) {
		var team = document.getElementById("team").value;
	}
	var b = false;
	if ((id == "" && option.value == 1) || (name == "" && option.value == 2)
			|| (email == "" && option.value == 3)
			|| (dob == "" && option.value == 4)
			|| (contact == "" && option.value == 4)
			|| (gender == "" && option.value == 6)
			|| (team == "" && option.value == 7)) {
		message = "Field cannot be empty";
	} else {
		message = "";
		b = true;
	}
	if (b == true && option.value == 1) {
		b = isValidPlayerId(id);
	}
	if (b == true && option.value == 2) {
		b = isValidName(name);
	}
	if (b == true && option.value == 3) {
		b = isValidEmail(email);
	}
	/*
	 * if (b == true && option.value == 4) { b = isValidDob(dob); }
	 */
	if (b == true && option.value == 5) {
		b = isValidContact(contact);
	}
	/*
	 * if (b == true && option.value == 6) { b = isValidGender(gender); } if (b ==
	 * true && option.value == 7) { b = isValidTeam(team); }
	 */
	document.getElementById("errMsg").innerText = message;
	return b;
}

function isValidPlayerId(id) {
	if (id.match(/^[Pp]{1}[a-zA-Z]{2}[0-9]{7}$/)) {
		return true;
	} else {
		message = "Invalid Player ID";
	}
	return false;
}

function isPlayerUpdateValid() {
	var id = document.getElementById("id").value;
	var contact = document.getElementById("contact").value;
	var b = false;
	if (id == "" || contact == "") {
		message = "ID and Contact Number required";
	} else {
		message = "";
		b = true;
	}
	if (b == true) {
		b = isValidPlayerId(id);
	}
	if (b == true) {
		b = isValidContact(contact);
	}
	document.getElementById("errMsg").innerText = message;
	return b;
}

function isPlayerDeleteValid() {
	var id = document.getElementById("id").value;
	var b = false;
	if (id == "") {
		message = "Must enter Player ID";
	} else {
		message = "";
		b = true;
	}
	if (b == true) {
		b = isValidPlayerId(id);
	}
	document.getElementById("errMsg").innerText = message;
	return b;
}