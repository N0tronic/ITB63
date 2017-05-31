$(document).ready(function () {
    var refresh = function () {
        $("#content").empty();

        $.get("/online-umfrage/umfragenDarstellung/", function (data) {

            var tr = $("<tr id='test' />");
            tr.append($("<th>Umfragetitel</th>"));
            tr.append($("<th>Anlagedatum</th>"));
            tr.append($("<th>Umfragestart</th>"));
            tr.append($("<th>Umfrageende</th>"));
            $("#content").append(tr);

            for(var i=0; i<data.length; i++){
                var tr = $("<tr />");
                tr.append($("<td><a href='umfrage.html' class='btn btn-default'> </a></td>").text(data[i].titel));
                tr.append($("<td><div class='button' /></td>").val(data[i].erstellungsdatum));
                tr.append($("<td><div /></td>").text(data[i].startdatum));
                tr.append($("<td><div /></td>").text(data[i].endedatum));
                $("#content").append(tr);
            }
        });
    };

    refresh();

    $("#refresh").on("click", refresh);
});