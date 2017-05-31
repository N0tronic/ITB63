$(document).ready(function() {

    $("#umfrage2").hide();
    $(".successarea").hide();

    $("#back").on("click",function () {
        $("#umfrage1").show();
        $("#umfrage2").hide();
        $(".successarea").hide();
    });

    $("#next").on("click",function () {
        $("#umfrage1").hide();
        $("#umfrage2").show();
        $(".successarea").hide();
    });

    $("#finish").on("click",function () {

        var data = {
            email:$("#ersteller").val(),
            umfragen:[{
                titel:$("#titel").val(),
                startdatum:$("#startdatum").val(),
                endedatum:$("#endedatum").val(),
                umfrageergebnis:{
                    teilnehmerzahl:0
                },
                fragen:[{
                    fragetext:$("#fragetext").val(),
                    erläuterung:$("#erlauterung").val(),
                    antwortmöglichkeiten:[
                        {antworttext:$("#antwortA").val()},
                        {antworttext:$("#antwortB").val()},
                        {antworttext:$("#antwortC").val()},
                        {antworttext:$("#antwortD").val()}
                    ]
                }]
            }]
        };

        $.ajax({
            url:"/online-umfrage/erstelleUmfrage/",
            method:"PUT",
            contentType:"application/json",
            data:JSON.stringify(data),
            success:function(data) {
                $("#umfrage1").hide();
                $("#umfrage2").hide();
                $(".successarea").show();
            },
            error:function(data) {
                alert("Umfrageerstellung fehlgeschlagen!");
            }
        });
    });
});