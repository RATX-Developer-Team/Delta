let categoriaIPL =  '<li class="nav-item">'+ //0 nombre de la categoria, 1 enlace a ver la categoria
                        '<a class="nav-link" href="{1}" aria-label="Dirigirse a la categoría {0}">{0}</a>'+ 
                    '</li>'

let artiPrinciIPL = '<div class="owl-item" style="width: 730px;">'+ //0 Titular, 1 descripcion corta, 2 fecha formateada , 3 imagen, 4 enlance
                        '<div class="item">'+
                            '<div class="carousel-content-wrapper mb-2">'+
                                '<div class="carousel-content">'+
                                    '<h1 class="font-weight-bold">'+
                                        '<a href="{4}" aria-label="Dirigirse al artículo {0}" class="textoSombra">{0}</a>'+
                                    '</h1>'+
                                    '<h5 class="font-weight-normal m-0 textoSombra">'+
                                        '{1}'+
                                    '</h5>'+
                                    '<p class="text-color m-0 pt-2 d-flex align-items-center">'+
                                        '<span class="fs-10 mr-1 textoSombra">{2}</span>'+
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
                            '<span class="fs-12 text-muted" aria-label="Autor del Artículo">{0}</span>'+
                        '</div>'+
                        '<p class="fs-14 m-0 font-weight-medium line-height-sm">'+
                            '<a href="{2}" aria-label="Dirigirse al articulo {1}">{1}</a>'+
                        '</p>'+
                    '</div>'+
                   '</div>'

let noticiaPopuPrinciIPL = '<div class="col-md-6  mb-5 mb-sm-2">'+// 0 imagen, 1 titular, 2 descipcion corta, 3 categoria, 4 codArt
                                '<div class="position-relative imagen-hover">'+
                                    '<img src="./img/{0}" class="img-fluid" alt="Imagen de {1}" />'+
                                    '<span class="py-3 px-4 bg-dark text-white fs-6 fw-bold lh-sm position-absolute bottom-0 start-0 tituloNoticiaP">{3}</span>'+
                                '</div>'+
                                '<h1 class="font-weight-600 mt-3"><a href="{4}" aria-label="Dirigirse al artículo {1}">{1}</a></h1>'+
                                '<p class="fs-15 font-weight-normal" aria-label="Descripción del artículo {2}">'+
                                    '{2}'+
                                '</p>'+
                            '</div>'

let noticiaPopularIPL = '<div class="col-sm-6  mb-5 mb-sm-2">'+// 0 imagen, 1 titular, 2 descipcion corta, 3 categoria, 4 codArt
                            '<div class="position-relative imagen-hover">'+
                            '  <img src="./img/{0}" class="img-fluid" alt="Imagen de noticia popular {1}" />'+
                            '  <span class="py-2 px-3 bg-dark text-white fs-6 fw-bold lh-sm position-absolute bottom-0 start-0 tituloNoticiaP" aria-label="Categoría del Artículo">{3}</span>'+
                            '</div>'+
                            '<h5 class="font-weight-600 mt-3"><a href="{4}" aria-label="Dirigirse al artículo {1}">{1}</a></h5>'+
                            '<p class="fs-15 font-weight-normal" aria-label="Descripción del artículo">'+
                            '  {2}'+
                            '</p>'+
                        '</div>'

let noticiaRecienteIPL = '<div class="card col-lg-3 col-sm-6 mb-5 mb-sm-2">'+// 0 imagen, 1 titular, 2 descipcion corta, 3 categoria, 4 codArt
                            '<div class="card-body">'+
                            '<div class="position-relative imagen-hover">'+
                                '<img src="./img/{0}" class="img-fluid" alt="Imagen de Noticia Recientes de {1}" />'+
                                '<span class="py-2 px-3 bg-dark text-white fs-6 fw-bold lh-sm position-absolute bottom-0 start-0 tituloNoticiaP" aria-label="Categoría del Artículo">{3}</span>'+
                            '</div>'+
                            '<h5 class="card-title mt-3"><a href="{4}" aria-label="Dirigirse al artículo">{1}</a></h5>'+
                            '<p class="card-text fs-15 " aria-label="Descripción del artículo">'+
                                '{2}'+
                            '</p>'+
                            '</div>'+
                            '<div class="card-footer">'+
                                '<a href="{4}" class="font-weight-bold text-dark pt-2" aria-label="Dirigirse al artículo">Leer Artículo</a>'+
                            '</div>'+
                        '</div>'

let articuloEnCategoriasIPL = '<article class="cajita">'+//0 imagen, 1 visitas, 2 fecha, 3 titular, 4 descipcion, 5 enlace
                                '<figure class="text-center position-relative card-img-top imagen-hover">'+
                                    '<a href="{5}"><img src="./img/{0}" class="img-fluid rounded" alt="Imágen Artículo en Categoría de {3}" /></a>'+
                                    '<div class="tags position-absolute bottom-0 end-0 mb-0 text-white w-100">'+
                                        '<p class="fs-5 mb-0">'+
                                            '<i class="fa-solid fa-eye"></i>'+
                                            '<span class="mx-2">{1}</span>'+
                                            '<i class="fa-solid fa-calendar"></i>'+
                                            '<span class="mx-2">{2}</span>'+
                                        '</p>'+
                                    '</div>'+
                                '</figure>'+
                                '<div class="card-body">'+
                                    '<header>'+
                                        '<p>'+
                                            '<span class="card-title h3 fw-bold mb-1"><a href="{5}">{3}</a></span>'+
                                        '</p>'+
                                        '<p>'+
                                            '<span class="h5 mb-1">{4}</span>'+
                                        '</p>'+
                                    '</header>'+
                                '</div>'+
                               '</article>'