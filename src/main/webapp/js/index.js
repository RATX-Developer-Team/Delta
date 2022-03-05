$(function() {
    console.clear()
    let utils_ = UTILS__
    utils_.cargaCategorias()
    utils_.cargarArticulos()

    $('.emailNewletter').attr('title', "El email tiene que ser correcto")
    $('.emailNewletter').attr('pattern', "[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$")
    $('.emailNewletter').attr('type', "email")
    $('.emailNewletter').attr('placeholder', "Introduzca un email...")

    $('.navbar-toggler').ready(function() {
        $('.navbar-toggler').click()
    })

    $(this).on('scroll', function() {
        let porcen = Math.round(utils_.getScrollPercent())
        if (porcen > 15) {
            $('#collapsibleNavbar').collapse('hide')
            $('.fondo2').show().css('width',porcen+'vw')
            $('.fondo').hide()
        } else {
            $('#collapsibleNavbar').collapse('show')
            $('.fondo2').hide()
            $('.fondo').show()
        }
    })
})