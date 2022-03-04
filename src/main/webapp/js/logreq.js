$(function() {
    console.clear()
    $('.fecha').attr('readonly', true).datepicker({
        dateFormat: 'dd/mm/yy',
        changeMonth: true,
        changeYear: true,
        yearRange: "1920:2021"
    });

    $('#log').on('click', function() {
        $('#formReg').hide();
        $('#formLog').show();
    })

    $('#reg').on('click', function() {
        $('#formReg').show();
        $('#formLog').hide();
    })
});