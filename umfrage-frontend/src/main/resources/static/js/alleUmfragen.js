function umfrageAuswahl(value) {
    location.href = "/umfrage?umfragetitel=" + value;
}

$(document).ready(function () {
    $('#nav').load("nav.html",setActive);
    function setActive() {
        $("#uebersicht").addClass("nav-item active");
    }
});