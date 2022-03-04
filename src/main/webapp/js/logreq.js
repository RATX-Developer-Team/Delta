$(function() {
    $('.fecha').attr('readonly', true).datepicker({
        dateFormat: 'dd/mm/yy',
        changeMonth: true,
        changeYear: true,
        yearRange: "1920:2021"
    });
    function cambiaField(selec){
            var formLog = document.getElementById("formLog");
            var formReg = document.getElementById("formReg");

            var tarj = document.getElementById("tarjeta");

            var credito = document.getElementById("credit");

            if(selec == log){
                    formLog.style.display="block";
                    formReg.style.display="none";

            }else{
                    formReg.style.display="block";
                    formLog.style.display="none";

            }
    }
});