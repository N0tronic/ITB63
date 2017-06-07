function minDate(value) {
    var input = document.getElementById("endedatum");
    input.setAttribute("min", value);
}

$(document).ready(function () {
    hideErsteller();
    $('#umfrageForm').formValidation();
});

function hideErsteller() {
    if(edit){
        document.getElementById('erstellerName').readOnly = true;
        document.getElementById('erstellerMail').readOnly = true;
    } else if(exists) {
        alert("Es besteht bereits eine Umfrage mit diesem Titel");
        exists = false;
    }
}

function validateForm() {
    alert("Name must be filled out");
    return false;
}

$(document).ready(function () {
    $('#nav').load("nav.html",setActive);
    function setActive() {
        $("#bearbeiten").addClass("nav-item active");
    }
});