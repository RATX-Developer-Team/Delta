$(function() {
    $('.fecha').attr('readonly', true).datepicker({
        dateFormat: 'dd/mm/yy',
        changeMonth: true,
        changeYear: true,
        yearRange: "1920:2021"
    });
});