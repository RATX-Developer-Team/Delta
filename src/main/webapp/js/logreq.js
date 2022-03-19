$(function() {
    console.clear()
    
    $('.fecha').attr('readonly', false).datepicker({
        dateFormat: 'dd/mm/yy',
        changeMonth: true,
        changeYear: true,
        yearRange: "1920:2021"
    })

    $('#log').on('click', function() {
        $('.formuReg').hide()
        $('.formuLog').show()
    })

    $('#reg').on('click', function() {
        $('.formuReg').show()
        $('.formuLog').hide()
    })

    $("input").prop("required",true);
});