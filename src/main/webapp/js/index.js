$(function() {
    console.clear()
    
    let utils_ = UTILS__
    utils_.cargaCategorias()
    utils_.cargarArticulos()

    $('.emailNewletter').attr('title', "El email tiene que ser correcto")
    $('.emailNewletter').attr('pattern', "[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$")
    $('.emailNewletter').attr('type', "email")
    $('.emailNewletter').attr('placeholder', "Introduzca un email...")
    $('.emailNewletter').attr('required', "true")

    $('.navbar-toggler').ready(function() {
        $('.navbar-toggler').click()
    })

    $('.btn').on('click',function(){
        $(this).val('¡Gracias!')
        $(this).addClass('enviado pe-5')
        $(this).after('<i class="fa-solid fa-check listoo"></i>')
        
    })

    $(this).on('scroll', function() {
        let porcen = Math.round(utils_.getScrollPercent())
        if (porcen > 15) {
            $('#collapsibleNavbar').collapse('hide')
            $('.fondo2').show().css('width',porcen+'vw')
            $('.fondo').hide()
            $('.caja').css('height','8vh')
            $('.logo').css('height','7vh')
        }else if(porcen < 14){
            $('.caja').css('height','11vh')
            $('.logo').css('height','10vh')
        }
        else {
            $('#collapsibleNavbar').collapse('show')
            $('.fondo2').hide()
            $('.fondo').show()
        }
    })
})