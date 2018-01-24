
var tipoExamen = null;
var respuestas = [];

$(function () {
    $("#botones").hide();
    
    $(".notas").click(function () {
        $("#content").css("min-height:275px");
        ocultarIns();
        var url = "ServletMongoDBCorregir";
        var emess = "Error desconocido";
        var n = 1;
        $.ajax({
            type: "get",
            url: "ServletMongoDBCorregir",
            dataType: "json",
            success: function (jsn) {
                
                document.getElementById("select1").innerHTML = "";
                document.getElementById("radio1").innerHTML = "";
                document.getElementById("text1").innerHTML = "";
                document.getElementById("checkbox1").innerHTML = "";
                document.getElementById("multiple1").innerHTML = "";
                $("#select1").append("<table class='table table-bordered'><thead><tr><th>DNI</th><th>Tipo Examen</th><th>Nota</th></tr></thead>"+
                        '<tbody id="tablaNotas"></tbody></table>'
                            );
                    
                $("#botones").hide();
                $("#content").css("min-height:400px");
                $.getJSON(url, function (jsn){
                    
                    for (var i = 0; i < jsn.notas.length; i++) {
                        var tipoExamen = parseInt(jsn.notas[i].tipoExamen) +1;
                        $("#tablaNotas").append("<tr><td>**"+ jsn.notas[i].dni.toUpperCase().substr(2)+
                                "</td><td>Examen "+tipoExamen +
                                "</td><td>"+jsn.notas[i].nota+"</td></tr>");
                        
                    }
                });
            }
        });
    });

    $("#instrucciones").click(function () {
        mostrarIns();
    });
    $("#boton1").click(function () {

        var url = "ServletMongoDB";
        var emess = "Error desconocido";
        var n = 0;
        tipoExamen = 0;
        $.ajax({
            method: "POST",
            url: url,
            data: {exam: n},
            success: function (u) {
                alert(u["mess"]);
            },
            error: function (e) {
                if (e["responseJSON"] === undefined)
                    alert(emess);
                else
                    alert(e["responseJSON"]["error"]);
            }
        });
        $("#botones").show();
        cargarExamen();
        ocultarIns();
    });

    $("#boton2").click(function () {

        var url = "ServletMongoDB";
        var emess = "Error desconocido";
        var n = 1;
        tipoExamen = 1;
        $.ajax({
            method: "POST",
            url: url,
            data: {exam: n},
            success: function (u) {
                alert(u["mess"]);
            },
            error: function (e) {
                if (e["responseJSON"] === undefined)
                    alert(emess);
                else
                    alert(e["responseJSON"]["error"]);
            }
        });

        cargarExamen();
        ocultarIns();
        $("#botones").show();
    });

    $("#boton3").click(function () {

        var url = "ServletMongoDB";
        var emess = "Error desconocido";
        var n = 2;
        tipoExamen = 2;
        $.ajax({
            method: "POST",
            url: url,
            data: {exam: n},
            success: function (u) {
                alert(u["mess"]);
            },
            error: function (e) {
                if (e["responseJSON"] === undefined)
                    alert(emess);
                else
                    alert(e["responseJSON"]["error"]);
            }
        });

        cargarExamen();
        ocultarIns();
        $("#botones").show();
    });

    $("#corregir").click(function () {


        var url = "ServletMongoDBCorregir";
        var emess = "Error desconocido";
        var n = $("#dni").val();
        var t = tipoExamen;
        var a = $("#select-1").val();
        var b = $('input:radio[name=exampleRadios]:checked').val();
        var c = $("#text-1").val();
        var d = $('input[type=checkbox]:checked').val();
        var e = $("#multiple-1").val().toString();
        if (a.length != 0 && b.length != 0 && c.length != 0 && d.length != 0 && e.length != 0) {
            if (n.length != 0) {
                if (/^\d{8}[a-zA-Z]$/.test(n) == true) {


                    var r1 = respuestas[0];
                    var r2 = respuestas[1];
                    var r3 = respuestas[2];
                    var r4 = respuestas[3];
                    var r5 = respuestas[4];


                    $.ajax({

                        method: "POST",
                        url: url,
                        data: {dni: n, tipoExamen: t, select: a, radio: b, text: c, checkbox: d, multiple: e,
                            r1: r1, r2: r2, r3: r3, r4: r4, r5: r5},
                        success: function (u) {
                            alert(u["mess"]);
                        },
                        error: function (e) {
                            if (e["responseJSON"] === undefined)
                                alert(emess);
                            else
                                alert(e["responseJSON"]["error"]);
                        }
                    });
                    cargarExamen();
                    ocultarIns();
                } else {
                    alert("Introduzca un DNI adecuado");
                }
            } else {
                alert("Introduzca el DNI")
            }
        } else {
            alert("Contesta todas las preguntas.")
        }
    });
});
function cargarExamen() {
    var url = "ServletMongoDB";
    var emess = "Error desconocido";
    var n = 1;
    $.ajax({
        type: "get",
        url: "ServletMongoDB",
        dataType: "json",
        success: function (jsn) {

            $.getJSON(url, function (jsn) {
                document.getElementById("select1").innerHTML = "";
                document.getElementById("radio1").innerHTML = "";
                document.getElementById("text1").innerHTML = "";
                document.getElementById("checkbox1").innerHTML = "";
                document.getElementById("multiple1").innerHTML = "";

                //Cargar Select

                $("#select1").append("<h3>" + jsn.pregunta1.titulo + "</h3><br>" +
                        "<select id='select-1' class='form-control'>" +
                        "<option value=''>Selecciona una opción</option>" +
                        "<option value='0'>" + jsn.pregunta1.respuesta1[0] + "</option>" +
                        "<option value='1'>" + jsn.pregunta1.respuesta1[1] + "</option>" +
                        "<option value='2'>" + jsn.pregunta1.respuesta1[2] + "</option>" + "</select>");
                //Cargar Radio
                $("#radio1").append("<h3>" + jsn.pregunta2.titulo + "</h3><br>" +
                        "<input class='form-check-input' type='radio' name='exampleRadios'id='radio-1' value='0' checked>" +
                        "<label class='form-check-label'>" + jsn.pregunta2.respuesta2[0] + "</label><br>" +
                        '<input class="form-check-input" type="radio" name="exampleRadios" id="radio-2" value="1">' +
                        '<label class="form-check-label">' + jsn.pregunta2.respuesta2[1] + "</label><br>" +
                        '<input class="form-check-input" type="radio" name="exampleRadios" id="radio-3" value="2">' +
                        '<label class="form-check-label">' + jsn.pregunta2.respuesta2[2] + "</label><br>");
                //Cargar Text
                $("#text1").append("<h3>" + jsn.pregunta3.titulo + "</h3><br>" +
                        '<input type="text" class="form-control" id="text-1" placeholder="Si/No">');
                //Cargar CheckBox
                $("#checkbox1").append("<h3>" + jsn.pregunta4.titulo + "</h3><br>" +
                        '<input class="form-check-input" type="checkbox" name="exampleCheckBox"id="defaultCheck1" value="0">' +
                        '<label class="form-check-label"id="checkbox-1">' + jsn.pregunta4.respuesta4[0] + "</label><br>" +
                        '<input class="form-check-input" type="checkbox" name="exampleCheckBox"id="defaultCheck1" value="1">' +
                        '<label class="form-check-label"id="checkbox-2">' + jsn.pregunta4.respuesta4[1] + "</label><br>" +
                        '<input class="form-check-input" type="checkbox" name="exampleCheckBox"id="defaultCheck1" value="2">' +
                        '<label class="form-check-label"id="checkbox-3">' + jsn.pregunta4.respuesta4[2] + "</label><br>");
                //CargarMultiple
                $("#multiple1").append("<h3>" + jsn.pregunta5.titulo + "</h3><br>" +
                        '<select class="selectpicker" multiple id="multiple-1">' +
                        "<option value=''>Selecciona una opción o más</option>" +
                        "<option value='0'>" + jsn.pregunta5.respuesta5[0] + "</option>" +
                        "<option value='1'>" + jsn.pregunta5.respuesta5[1] + "</option>" +
                        "<option value='2'>" + jsn.pregunta5.respuesta5[2] + "</option>" + "</select>");

                //CargarRespuestas

                respuestas[0] = jsn.pregunta1.respuesta1.answer;
                respuestas[1] = jsn.pregunta2.respuesta2.answer;
                respuestas[2] = jsn.pregunta3.respuesta3.answer;
                respuestas[3] = jsn.pregunta4.respuesta4.answer;
                respuestas[4] = jsn.pregunta5.respuesta5.answer1 + "," + jsn.pregunta5.respuesta5.answer2;

            });
        },
        error: function (e) {
            if (e["responseJSON"] === undefined)
                alert(emess);
            else
                alert(e["responseJSON"]["error"]);
        }
    });

}

function ocultarIns() {
    $("#modalIns").hide();
}

function mostrarIns() {
    $("#modalIns").show();
}





