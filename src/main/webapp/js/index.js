$(function() {
    console.clear()
    let utils_ = UTILS__
    utils_.cargaCategorias()
    utils_.cargarArticulos()



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