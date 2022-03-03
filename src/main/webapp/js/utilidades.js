/*VARIABLES GLOBALES*/
let categoriaIPL =  '<li class="nav-item mx-3">'+
                        '<a class="nav-link" href="#">{0}</a>'+
                    '</li>'

let artiPrinciIPL = '<div class="owl-item" style="width: 730px;">'+
                        '<div class="item">'+
                            '<div class="carousel-content-wrapper mb-2">'+
                                '<div class="carousel-content">'+
                                    '<h1 class="font-weight-bold">'+
                                        '{0}'+
                                    '</h1>'+
                                    '<h5 class="font-weight-normal  m-0">'+
                                        '{1}'+
                                    '</h5>'+
                                    '<p class="text-color m-0 pt-2 d-flex align-items-center">'+
                                        '<span class="fs-10 mr-1">{2}</span>'+
                                    '</p>'+
                                '</div>'+
                                '<div class="carousel-image">'+
                                    '<img src="./img/{3}" alt="Imagen de {0}">'+
                                '</div>'+
                            '</div>'+
                        '</div>'+
                    '</div>'

let CATEGORIAS = {}
let ARTICULOS = {}
let Config = {
    cantidadArtPrin : 4
}

var UTILS__ = (function() {
    function getScrollPercent() {
        var h = document.documentElement, 
            b = document.body,
            st = 'scrollTop',
            sh = 'scrollHeight';
        return (h[st]||b[st]) / ((h[sh]||b[sh]) - h.clientHeight) * 100;
    }

    function cargaCate() {
        $('.categorias').ready(function () {
            Object.keys(CATEGORIAS).forEach(function(k) {
                let cate_ = categoriaIPL.replace('{0}',CATEGORIAS[k])
                $('.categorias').append(cate_)
            })
        })
    }

    function cargaCategorias() {
        $.getJSON("response", {
            categorias: "todas"
        }, function (data) {
            $.each(data, function(index,v) {
                CATEGORIAS[index] = v
            })
            cargaCate()
        })
    }

    function principales() {
        let o = {}
        let o_ = {}
        Object.keys(ARTICULOS).forEach(function(k) {
            o[k] = ARTICULOS[k].prioridad
        })

        let o__ = Object.keys(o).sort(function(a,b){return o[b]-o[a]})
        for (let i=0;i!=Config.cantidadArtPrin;i++) {
            o_[o__[i]] = ARTICULOS[o__[i]]
        }
        return o_
    }

    function cargaArtPrinci() {
        let articulos_ = principales()
        Object.keys(articulos_).forEach(function(k) {
            let arti_ = artiPrinciIPL.replace('{0}',articulos_[k].titular).replace('{1}',articulos_[k].cuerpoNoticia).replace('{2}',articulos_[k].fechaPubli.split(',')[0]).replace('{3}',articulos_[k].imagen)
            $('.cargaPrinci').append(arti_)
        })

        if ($("#main-banner-carousel").length) {
            $("#main-banner-carousel").owlCarousel({
              loop: true,
              autoplay: true,
              autoplayTimeout: 3000,
              autoplaySpeed: 2000,
              autoplayHoverPause: true,
              autoWidth: false,
              dots: true,
              margin: 0,
              responsiveClass: true,
              responsive: {
                0: {
                  items: 1
                },
                320: {
                  items: 1
                }
              }
            });
        }
    }

    function cargarArticulos() {
        $.getJSON("response", {
            articulo: "todos"
        }, function (data) {
            $.each(data, function(index,v) {
                ARTICULOS[index] = JSON.parse(v)
            })
            cargaArtPrinci()
        })
    }
    /**
    *  Metodos publicos
    */
    return {
        cargaCategorias: cargaCategorias,
        getScrollPercent: getScrollPercent,
        cargarArticulos: cargarArticulos
    }
})();
