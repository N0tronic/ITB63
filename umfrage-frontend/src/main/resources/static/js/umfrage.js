var aktiv = false;

$(document).ready(function () {
    $('input:radio').prop('checked', false);

    var aktuellesDatum = new Date();
    aktuellesDatum.setHours(0, 0, 0, 0);
    aktuellesDatum = aktuellesDatum.getTime();

    var startdatum = new Date(start);
    startdatum.setHours(0, 0, 0, 0);
    startdatum = startdatum.getTime();

    var endedatum = new Date(ende);
    endedatum.setHours(0, 0, 0, 0);
    endedatum = endedatum.getTime();

    if (startdatum <= aktuellesDatum && aktuellesDatum <= endedatum) {
        aktiv = true;
    }
    var before = false;
    if (startdatum > aktuellesDatum) {
        before = true;
    }

    if (!aktiv) {
        document.getElementById('checkButton').innerHTML = "Nicht im Abstimmungszeitraum";
        $("#dateFooter").addClass("card-outline-danger");
        $("#checkButton").addClass("btn-outline-danger");
        if (!before) {
            document.getElementById('editButton').disabled = true;
        } else {
            document.getElementById('editButton').style = "cursor: pointer";
        }
    } else {
        document.getElementById('editButton').style = "cursor: pointer";
    }

    $('#nav').load("nav.html");
});

function check() {
    if (aktiv) {
        document.getElementById('checkButton').disabled = false;
        document.getElementById('checkButton').style = "cursor: pointer";
    }
}