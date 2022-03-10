/*VARIABLES GLOBALES*/
let categoriaIPL =  '<li class="nav-item mx-3">'+ //0 nombre de la categoria, 1 enlace a ver la categoria
                        '<a class="nav-link" href="#">{0}</a>'+ 
                    '</li>'

let artiPrinciIPL = '<div class="owl-item" style="width: 730px;">'+ //0 Titular, 1 descripcion corta, 2 fecha formateada , 3 imagen, 4 enlance
                        '<div class="item">'+
                            '<div class="carousel-content-wrapper mb-2">'+
                                '<div class="carousel-content">'+
                                    '<h1 class="font-weight-bold">'+
                                        '<a href="{4}">{0}</a>'+
                                    '</h1>'+
                                    '<h5 class="font-weight-normal m-0">'+
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

let columnaIPL = '<div class="row columna{0}"></div>' // 0 numero de la columna
let artColumIPL = '<div class="col-sm-6">'+ // 0 Nombre apellido , 1 Descripcion corta articulo, 2 enlace a ver el articulo
                    '<div class="py-3 border-bottom">'+
                        '<div class="d-flex align-items-center pb-2">'+
                            '<span class="fs-12 text-muted">{0}</span>'+
                        '</div>'+
                        '<p class="fs-14 m-0 font-weight-medium line-height-sm">'+
                            '<a href="{2}">{1}</a>'+
                        '</p>'+
                    '</div>'+
                   '</div>'

let CATEGORIAS = {}
let ARTICULOS = {}
let Config = {
    cantidadArtPrin : 4, //Cantidad de articulos principales
    timeout: 5000, //Tiempo en pasar de articulo despues de hacer hover en uno en MS
    speed: 4000, // Tiempo en pasar de articulo de forma normal
    cantidadColumnasNoticiasRecientes: 5, //Cantidad de columnas de articulos secundarios
    cantidaddeArticulosPorColumna: 2 // Cantidad de articulos por columna de articulos secundarios
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

    function ordenarArt(v) {
        let o = {}
        let o_ = {}
        Object.keys(ARTICULOS).forEach(function(k) {
            o[k] = ARTICULOS[k].prioridad
        })

        let o__ = Object.keys(o).sort(function(a,b){return o[b]-o[a];})
        for (let i=0;i!=v;i++) {
            o_[o__[i]] = ARTICULOS[o__[i]]
        }
        return o_
    }

    function cargaArtSecun() {
        let articulos_ = ordenarArt(Config.cantidadColumnasNoticiasRecientes*Config.cantidaddeArticulosPorColumna)
        let x_ = 0
        for (let xy=0;xy!=Config.cantidadColumnasNoticiasRecientes;xy++) {
            let colum_ = columnaIPL.replace('{0}',xy)
            $('.cargaRecientes').append(colum_)
            for (let xx=x_;xx!=Config.cantidaddeArticulosPorColumna+x_;xx++) {
                let arti_ = artColumIPL.replace('{0}', Object.values(articulos_)[xx].codUsuario).replace('{1}', Object.values(articulos_)[xx].titular).replace('{2}', 'puente?is=a&destino=/articulo.jsp&codigoArt='+Object.values(articulos_)[xx].codArt)
                $('.columna'+xy).append(arti_)
            }
            x_+=Config.cantidaddeArticulosPorColumna
        }
    }

    function textHTML(str) {
        var parser = new DOMParser();
        var doc = parser.parseFromString(str, 'text/html');
        return doc.body;
    }

    function cargaArtPrinci() {
        let articulos_ = ordenarArt(Config.cantidadArtPrin)
        Object.keys(articulos_).forEach(function(k) {
            let arti_ = artiPrinciIPL.replace('{0}',articulos_[k].titular).replace('{1}',articulos_[k].descripcion).replace('{2}',articulos_[k].fechaPubli.split(',')[0]).replace('{3}',articulos_[k].imagen).replace('{4}', 'puente?is=a&destino=/articulo.jsp&codigoArt='+articulos_[k].codArt)
            $('.cargaPrinci').append(arti_)
        })

        if ($("#main-banner-carousel").length) {
            $("#main-banner-carousel").owlCarousel({
              loop: true,
              autoplay: true,
              autoplayTimeout: Config.timeout,
              autoplaySpeed: Config.speed,
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
            })
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
            cargaArtSecun()
        })
    }
    /**
    *  Metodos publicos
    */
    return {
        cargaCategorias: cargaCategorias,
        getScrollPercent: getScrollPercent,
        cargarArticulos: cargarArticulos,
        textHTML: textHTML
    }
})()
