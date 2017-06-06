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
        document.getElementById('erstellerName').disabled = true;
        document.getElementById('erstellerMail').disabled = true;
        document.getElementById('erstellerName').required = false;
        document.getElementById('erstellerMail').required = false;
    }
}

$(document).ready(function () {
    $('#nav').load("nav.html",setActive);
    function setActive() {
        $("#bearbeiten").addClass("nav-item active");
    }
});