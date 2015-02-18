$('a#entrar').click(function () {
    var loginfo = $("form").serialize();
    alert(loginfo.toString());
    $.ajax({
        type: 'POST',
        url: '../PHP/pasarela/validacionPasarela.php',
        data: "loginfo="+loginfo,
        success: function (datos) {
            alert(datos);
            $('div#dinamic-info').html(datos);
        },
        error: function (data){
            alert("Error");
        }
    });
});

