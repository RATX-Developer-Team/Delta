/*VARIABLES GLOBALES*/

let CATEGORIAS = {}
let ARTICULOS = {}
let SUBCATEGORIAS = {}

let Config = {
    cantidadArtPrin : 4, //Cantidad de articulos principales
    timeout: 5000, //Tiempo en pasar de articulo despues de hacer hover en uno en MS
    speed: 4000, // Tiempo en pasar de articulo de forma normal
    cantidadColumnasNoticiasRecientes: 5, //Cantidad de columnas de articulos secundarios
    cantidadArticulosPopularesGrandes: 1, //Cantidad de articulos populares grandes
    cantidadArticulosPopularesPeque: 4, //Cantidad de articulos populares pequeños
    cantidadArticulosRecientes2: 4, //Cantidad de articulos recientes al final de la pagina
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

    function parametro(v) {
        const params = new Proxy(new URLSearchParams(window.location.search), {
            get: (searchParams, prop) => searchParams.get(prop),
        });
        return params[v]
    }

    function contarArt(b,v) {
        if (b) {//Contar articulos por categoria
            let i = 0;
            Object.keys(ARTICULOS).forEach(function(k) {
                let o = ARTICULOS[k]
                if(o.codCategoria==v) {
                    i++
                }
            })
            return i;
        } else {//Contar articulos por subcategoria
            let i = 0;
            Object.keys(ARTICULOS).forEach(function(k) {
                let o = ARTICULOS[k]
                if(o.codSubCategoria==v) {
                    i++
                }
            })
            return i;
        }
    }

    function cargaSubs() {
        $('.subcategorias').ready(function () {
            Object.keys(SUBCATEGORIAS).forEach(function(k) {
                let o_ = JSON.parse(SUBCATEGORIAS[k])
                let codCate = parametro("codigoCategoria")
                Object.keys(o_).forEach(function(k1) {
                    if (o_[k1]==codCate) {
                        let subcate_ = categoriaIPL.replace('{0}',k1).replace('{1}','puente?is=sc&destino=/subcategoria.jsp&codigoSubcategoria='+k)
                        $('.subcategorias').append(subcate_)
                    }
                })
            })
        })
    }

    function cargaAriculosEnCategoria() {
        let articulos_ = ordenarArt(contarArt(true,parametro("codigoCategoria")),"prioridad")
        Object.keys(articulos_).forEach(function(k) {
            let arti_ = articuloEnCategoriasIPL.replace('{0}', articulos_[k].imagen).replace('{1}', articulos_[k].nVisitas).replace('{2}', articulos_[k].fechaPubli.split(',')[0]).replace('{3}', articulos_[k].titular).replace('{4}', articulos_[k].descripcion).replace('{5}', 'puente?is=a&destino=/articulo.jsp&codigoArt='+articulos_[k].codArt)
            $('.cargaArticulosPorCategorias').prepend(arti_)
        })
    }

    function cargaAriculosEnSubCategoria() {
        let articulos_ = ordenarArt(contarArt(false,parametro("codigoSubcategoria")),"prioridad")
        Object.keys(articulos_).forEach(function(k) {
            let arti_ = articuloEnCategoriasIPL.replace('{0}', articulos_[k].imagen).replace('{1}', articulos_[k].nVisitas).replace('{2}', articulos_[k].fechaPubli.split(',')[0]).replace('{3}', articulos_[k].titular).replace('{4}', articulos_[k].descripcion).replace('{5}', 'puente?is=a&destino=/articulo.jsp&codigoArt='+articulos_[k].codArt)
            $('.cargaDeArticulosPorSubcategorias').prepend(arti_)
        })
    }

    function cargaSubCategorias() {
        $.getJSON("response", {
            categorias: "sub"
        }, function (data) {
            $.each(data, function(index,v) {
                SUBCATEGORIAS[index] = v
            })
            cargaSubs()
            if(parametro('is')=='sc') {
                let codSub = parametro("codigoSubcategoria")
                let codigoCate = JSON.parse(SUBCATEGORIAS[codSub])[Object.keys(JSON.parse(SUBCATEGORIAS[codSub]))[0]]
                $('a[href*="codigoCategoria='+codigoCate+'"]')
                Object.keys(JSON.parse(SUBCATEGORIAS[codSub]))[0]
                let html = $('.nombreCategoriaEnSub').html()
                $('.nombreSubCategoriaCarga').html(Object.keys(JSON.parse(SUBCATEGORIAS[codSub]))[0])
                $('.nombreCategoriaEnSub').html(html.replace('{0}','puente?is=c&destino=/categoria.jsp&codigoCategoria='+JSON.parse(SUBCATEGORIAS[codSub])[Object.keys(JSON.parse(SUBCATEGORIAS[codSub]))[0]]).replace('{1}',CATEGORIAS[JSON.parse(SUBCATEGORIAS[codSub])[Object.keys(JSON.parse(SUBCATEGORIAS[codSub]))[0]]]))
            }
        })
    }

    function cargaCate() {
        $('.categorias').ready(function () {
            Object.keys(CATEGORIAS).forEach(function(k) {
                let cate_ = categoriaIPL.replace('{0}',CATEGORIAS[k]).replace('{1}','puente?is=c&destino=/categoria.jsp&codigoCategoria='+k)
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
            if(parametro('is')!='c') {
                cargaCate()
            }
            let cod = parametro("codigoCategoria")
            $('.nombreCategoriaCarga').html(CATEGORIAS[cod])
        })
    }

    function ordenarArt(v,kk) {
        let o = {}
        let o_ = {}
        Object.keys(ARTICULOS).forEach(function(k) {
            o[k] = ARTICULOS[k][kk]
        })

        let o__ = Object.keys(o).sort(function(a,b){return o[b]-o[a];})
        for (let i=0;i!=v;i++) {
            o_[o__[i]] = ARTICULOS[o__[i]]
        }
        return o_
    }

    function cargarArticulosRecientes() {
        let articulos_ = ordenarArt(Config.cantidadArticulosRecientes2,"codArt")
        Object.keys(articulos_).forEach(function(k) {
            let arti_ = noticiaRecienteIPL.replace('{0}', articulos_[k].imagen).replace('{1}', articulos_[k].titular).replace('{1}', articulos_[k].titular).replace('{2}', articulos_[k].descripcion).replace('{3}', articulos_[k].categoria).replace('{4}', 'puente?is=a&destino=/articulo.jsp&codigoArt='+articulos_[k].codArt).replace('{4}', 'puente?is=a&destino=/articulo.jsp&codigoArt='+articulos_[k].codArt)
            $('.cargaRecientes2').prepend(arti_)
        })
    }

    function cargarArticulosPopulares() {
        let articulos_ = ordenarArt(Config.cantidadArticulosPopularesGrandes,"nVisitas")
        Object.keys(articulos_).forEach(function(k,index) {
            if (index==0) {
                let arti_ = noticiaPopuPrinciIPL.replace('{0}', articulos_[k].imagen).replace('{1}', articulos_[k].titular).replace('{1}', articulos_[k].titular).replace('{2}', articulos_[k].descripcion).replace('{3}', articulos_[k].categoria).replace('{4}', 'puente?is=a&destino=/articulo.jsp&codigoArt='+articulos_[k].codArt)
                $('.cargaPopulares').prepend(arti_)
            } else {

            }
        })
    }

    function cargarArticulosPopulares2() {
        let articulos_ = ordenarArt(Config.cantidadArticulosPopularesPeque,"nVisitas")
        Object.keys(articulos_).forEach(function(k,index) {
            if (index%2==0) {
                let arti_ = noticiaPopularIPL.replace('{0}', articulos_[k].imagen).replace('{1}', articulos_[k].titular).replace('{2}', articulos_[k].descripcion).replace('{3}', articulos_[k].categoria).replace('{4}', 'puente?is=a&destino=/articulo.jsp&codigoArt='+articulos_[k].codArt)
                $('.cargaPopulares1').append(arti_)
            } else {
                let arti_ = noticiaPopularIPL.replace('{0}', articulos_[k].imagen).replace('{1}', articulos_[k].titular).replace('{2}', articulos_[k].descripcion).replace('{3}', articulos_[k].categoria).replace('{4}', 'puente?is=a&destino=/articulo.jsp&codigoArt='+articulos_[k].codArt)
                $('.cargaPopulares2').append(arti_)
            }
        })
    }

    function cargaArtSecun() {
        let articulos_ = ordenarArt(Config.cantidadColumnasNoticiasRecientes*Config.cantidaddeArticulosPorColumna,"prioridad")
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
        var dom = document.createElement('div');
        dom.innerHTML = str;
        return dom;
    }

    function cargaArtPrinci() {
        let articulos_ = ordenarArt(Config.cantidadArtPrin,"prioridad")
        Object.keys(articulos_).forEach(function(k) {
            let arti_ = artiPrinciIPL.replace('{0}',articulos_[k].titular).replace('{0}',articulos_[k].titular).replace('{1}',articulos_[k].descripcion).replace('{2}',articulos_[k].fechaPubli.split(',')[0]).replace('{3}',articulos_[k].imagen).replace('{4}', 'puente?is=a&destino=/articulo.jsp&codigoArt='+articulos_[k].codArt)
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
            if (parametro('is')==null || parametro('is')=="") {
                cargaArtPrinci()
                cargaArtSecun()
                cargarArticulosPopulares()
                cargarArticulosPopulares2()
                cargarArticulosRecientes()
            } else if(parametro('is')=='c') {
                cargaAriculosEnCategoria()
            } else if(parametro('is')=='sc') {
                cargaAriculosEnSubCategoria()
            } else if(parametro('is')=='a') {
                $('.bread').ready(function() {
                    let c = parametro("codigoArt")
                    let art = ARTICULOS[c]
                    let htmlCate = $('.nombreCategoriaEnArticulo').html()
                    let htmlSubCate = $('.nombreSubCategoriaEnArticulo').html()
                    $('.nombreCategoriaEnArticulo').html(htmlCate.replace('{0}','puente?is=c&destino=/categoria.jsp&codigoCategoria='+art.codCategoria).replace('{1}',art.categoria))
                    $('.nombreSubCategoriaEnArticulo').html(htmlSubCate.replace('{2}','puente?is=sc&destino=/subcategoria.jsp&codigoSubcategoria='+art.codSubCategoria).replace('{3}',art.subcategoria))
                })
            }
        })
    }
    /**
    *  Metodos publicos
    */
    return {
        cargaCategorias: cargaCategorias,
        getScrollPercent: getScrollPercent,
        cargarArticulos: cargarArticulos,
        cargaSubCategorias: cargaSubCategorias,
        parametro: parametro,
        textHTML: textHTML
    }
})()
