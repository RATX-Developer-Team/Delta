<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html lang="es" xmlns="http://www.w3.org/">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>Delta | Diario online líder de información en español</title>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
            <script src="https://code.jquery.com/ui/1.13.0/jquery-ui.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/js/all.min.js"></script>
            <script src="./js/lib/owl.carousel.min.js"></script>
            <script src="./js/utilidades.js"></script>
            <script src="./js/index.js"></script>
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/css/bootstrap.min.css"/>
            <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.0/themes/smoothness/jquery-ui.css"/>
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"/>
            <link rel="stylesheet" href="./css/lib/owl.carousel.min.css"/>
            <link rel="stylesheet" href="./css/lib/owl.theme.default.min.css"/>
            <link rel="stylesheet" href="./css/estilo.css"/>
            <link rel="shortcut icon" type="image/x-icon" href="./img/assets/FAVICONDELTA.ico">
        </head>
        <body>
            <div class="contaier-fuild">

            <!--  HEADER  -->
            <div class="row sticky-top">
              
                <div class="contaier-fuild p-0">

                    <div class="row fondo">
                    </div>

                    <header class="sticky-top container-fluid fondoBarra">
                        <div class="container-fluid">
                            <nav class="navbar bg-transparent navbar-light">
                                <div class="container-fluid caja">
                                    <div class="col d-flex justify-content-start">
                                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                                                data-bs-target="#collapsibleNavbar">
                                            <span class="navbar-toggler-icon m-auto "></span>
                                        </button>
                                    </div>
                                    <div class="col d-flex justify-content-center">
                                        <a class="navbar-brand fs-3 m-0" href="index.jsp"><img class="img-responsive logo" height="100" src="./img/assets/MODOCLARO.png" alt="Logotipo del periodico Delta."/></a>
                                    </div>
                                    <div class="col d-flex justify-content-end">
                                        <div class="d-flex flex-row align-items-center">
                                            <div class="me-2 dropdown text-end">
                                                <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
                                                    <i class="fa-solid fa-user usua"></i>
                                                </a>
                                                <ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1">
                                                    <li><a class="dropdown-item" href="#">New project...</a></li>
                                                    <li><a class="dropdown-item" href="#">Settings</a></li>
                                                    <li><a class="dropdown-item" href="#">Profile</a></li>
                                                    <li><hr class="dropdown-divider"></li>
                                                    <li><a class="dropdown-item" href="#">Sign out</a></li>
                                                </ul>
                                            </div>
                                            <form class="col-12 col-md-auto mb-3 mb-md-0 me-md-3">
                                                <input type="search" class="form-control" placeholder="Buscar..." aria-label="Search">
                                            </form>
                                        </div>
                                    </div>
                                    <div class="collapse navbar-collapse justify-content-between" id="collapsibleNavbar">
                                        <ul class="navbar-nav">
                                            <div class="container-fluid d-flex flex-row categorias justify-content-center">
                                            </div>
                                        </ul>
                                    </div>
                                </div>
                            </nav>
                        </div>
                    </header>
                    <div class="row fondo2">
                    </div>
                </div>
            </div>    
            <!--  FIN DEL HEADER  -->

            <!--  NOTICIAS PRINCIPALES  -->
            <div class="row ">
              <div class="col d-flex justify-content-center p-0">
                <div class="col-md-8 mt-3">
                        <div class="row">
                            <div class="col-md-8">
                                <div class="owl-carousel owl-theme owl-loaded owl-drag" id="main-banner-carousel">
                                    <div class="owl-stage-outer">
                                        <div class="owl-stage cargaPrinci" style="transform: translate3d(-3650px, 0px, 0px); transition: all 2s ease 0s; width: 5840px;">

                                        </div>
                                    </div>
                                    <div class="owl-nav disabled">
                                        <button type="button" role="presentation" class="owl-prev">
                                            <span aria-label="Previous">‹</span>
                                        </button>
                                        <button type="button" role="presentation" class="owl-next">
                                            <span aria-label="Next">›</span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4 cargaRecientes">

                            </div>

                        </div>
                    <h:form>
                        <h:commandButton value="Iniciar sesión" action="alta"/>
                    </h:form>
                </div>
              </div>
            </div>  
        <!-- FIN DE  NOTICIAS PRINCIPALES  -->    
            
        
        <!--  NOTICIAS POPULARES -->
        <div class="row" id="notPopu">
          <div class="col d-flex justify-content-center p-0">
            <div class="col-md-8 mt-5">

              <div class="row">
                <div class="col-md-3">
                  <div class="d-flex position-relative float-left">
                    <h3 id="tit" class="tituloSeccion">Noticias Populares</h3>
                  </div>
                </div>
              </div>

              <div class="row">
                <div class="col-md-6  mb-5 mb-sm-2">
                  <div class="position-relative imagen-hover">
                    <img src="./img/globo.jpg" class="img-fluid" alt="Noticia popular" />
                    <span class="py-3 px-4 bg-dark text-white fs-6 fw-bold lh-sm position-absolute bottom-0 start-0 tituloNoticiaP">AÑADIR CATEGORIA</span>
                  </div>
                  <h1 class="font-weight-600 mt-3">Título</h1>
                  <p class="fs-15 font-weight-normal">
                    Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and 
                  </p>
                </div>

                <div class="col-md-6  mb-5 mb-sm-2">
                  <div class="row">
                    <div class="col-sm-6  mb-5 mb-sm-2">
                      <div class="position-relative imagen-hover">
                        <img src="./img/foto.png" class="img-fluid" alt="Noticia popular" />
                        <span class="py-2 px-3 bg-dark text-white fs-6 fw-bold lh-sm position-absolute bottom-0 start-0 tituloNoticiaP">Política</span>
                      </div>
                      <h5 class="font-weight-600 mt-3">A look at California's eerie plane graveyards</h5>
                      <p class="fs-15 font-weight-normal">
                        Lorem Ipsum has been the industry's standard dummy text
                      </p>
                    </div>
                    <div class="col-sm-6  mb-5 mb-sm-2">
                      <div class="position-relative imagen-hover">
                        <img src="./img/foto.png" class="img-fluid" alt="Noticia popular" />
                        <span class="py-2 px-3 bg-dark text-white fs-6 fw-bold lh-sm position-absolute bottom-0 start-0 tituloNoticiaP">AÑADIR CATEGORIA</span>
                      </div>
                      <h5 class="font-weight-600 mt-3">The world's most beautiful racecourses</h5>
                      <p class="fs-15 font-weight-normal">
                        Lorem Ipsum has been the industry's standard dummy text
                      </p>
                    </div>
                  </div>
                  <div class="row mt-3">
                    <div class="col-sm-6  mb-5 mb-sm-2">
                      <div class="position-relative imagen-hover">
                        <img src="./img/foto.png" class="img-fluid" alt="Noticia popular" />
                        <span class="py-2 px-3 bg-dark text-white fs-6 fw-bold lh-sm position-absolute bottom-0 start-0 tituloNoticiaP">AÑADIR CATEGORIA</span>
                      </div>
                      <h5 class="font-weight-600 mt-3">Japan cancels cherry blossom festivals over virus fears</h5>
                      <p class="fs-15 font-weight-normal">
                        Lorem Ipsum has been the industry's standard dummy text
                      </p>
                    </div>
                    <div class="col-sm-6">
                      <div class="position-relative imagen-hover">
                        <img src="./img/foto.png" class="img-fluid" alt="Noticia popular" />
                        <span class="py-2 px-3 bg-dark text-white fs-6 fw-bold lh-sm position-absolute bottom-0 start-0 tituloNoticiaP">AÑADIR CATEGORIA</span>
                      </div>
                      <h5 class="font-weight-600 mt-3">Classic cars reborn as electric vehicles</h5>
                      <p class="fs-15 font-weight-normal">
                        Lorem Ipsum has been the industry's standard dummy text
                      </p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!--  FIN NOTICIAS POPULARES -->
        
        <!-- NOTICIAS MUNDIALES -->
        <div class="row">
          <div class="col d-flex justify-content-center p-0">
            <div class="col-md-8 mt-5">

            <div class="row">
              <div class="col-sm-12">
                <div class="d-flex position-relative  float-left">
                  <h3 id="tit" class="tituloSeccion">Noticias Mundiales</h3>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-lg-3 col-sm-6 grid-margin mb-5 mb-sm-2">
                <div class="position-relative imagen-hover">
                  <img src="./img/viajes.jpg" class="img-fluid" alt="Noticia Mundial" /> <!-- MEJOR FOTOS CUADRADAS -->
                  <span class="py-2 px-3 bg-dark text-white fs-6 fw-bold lh-sm position-absolute bottom-0 start-0 tituloNoticiaP">AÑADIR CATEGORIA</span>
                </div>
                <h5 class="font-weight-bold mt-3">Refugees flood Turkey's border with Greece</h5>
                <p class="fs-15 font-weight-normal">
                  Lorem Ipsum has been the industry's standard dummy text
                </p>
                <a href="#" class="font-weight-bold text-dark pt-2">Leer Artículo</a>
              </div>
              <div class="col-lg-3 col-sm-6 mb-5 mb-sm-2">
                <div class="position-relative imagen-hover">
                  <img src="./img/noticias.jpg" class="img-fluid" alt="Noticia Mundial" /> 
                  <span class="py-2 px-3 bg-dark text-white fs-6 fw-bold lh-sm position-absolute bottom-0 start-0 tituloNoticiaP">AÑADIR CATEGORIA</span>
                </div>
                <h5 class="font-weight-bold mt-3">South Korea’s Moon Jae-in sworn in vowing address</h5>
                <p class="fs-15 font-weight-normal">
                  Lorem Ipsum has been the industry's standard dummy text
                </p>
                <a href="#" class="font-weight-bold text-dark pt-2">Leer Artículo</a>
              </div>
              <div class="col-lg-3 col-sm-6 mb-5 mb-sm-2">
                <div class="position-relative imagen-hover">
                  <img src="./img/viajes.jpg" class="img-fluid" alt="Noticia Mundial" /> 
                  <span class="py-2 px-3 bg-dark text-white fs-6 fw-bold lh-sm position-absolute bottom-0 start-0 tituloNoticiaP">AÑADIR CATEGORIA</span>
                </div>
                <h5 class="font-weight-bold mt-3">These puppies are training to assist in avalanche rescue</h5>
                <p class="fs-15 font-weight-normal">
                  Lorem Ipsum has been the industry's standard dummy text
                </p>
                <a href="#" class="font-weight-bold text-dark pt-2">Leer Artículo</a>
              </div>
              <div class="col-lg-3 col-sm-6 mb-5 mb-sm-2">
                <div class="position-relative imagen-hover">
                  <img src="./img/noticias.jpg" class="img-fluid" alt="Noticia Mundial" /> 
                  <span class="py-2 px-3 bg-dark text-white fs-6 fw-bold lh-sm position-absolute bottom-0 start-0 tituloNoticiaP">AÑADIR CATEGORIA</span>
                </div>
                <h5 class="font-weight-bold mt-3">'Love Is Blind' couple opens up about their first year</h5>
                <p class="fs-15 font-weight-normal">
                  Lorem Ipsum has been the industry's standard dummy text
                </p>
                <a href="#" class="font-weight-bold text-dark pt-2">Leer Artículo</a>
              </div>
            </div>
          </div>
        </div>
      </div>

        <!-- FIN DE NOTICIAS MUNDIALES-->
        
        <!--  FOOTER DE LA PÁGINA CON EL NEWSLETTER -->
         <div class="row">  
            <footer class="bd-footer p-0">
                <section class="subs" id="signup">
                    <div class="container px-4 px-md-5">
                        <div class="row gx-4 gx-md-5">
                            <div class="col-md-10 col-md-8 mx-auto text-center">
                                <i class="far fa-paper-plane fa-2x mb-2 text-white"></i>
                                <h2 class="text-white mb-5">¡Suscribete para recibir nuevas noticias!</h2>
                                <h:form styleClass="form-subs">
                                    <div class="row input-group-newsletter">
                                        <div class="col">
                                            <h:inputText styleClass="form-control emailNewletter" value="#{beanRegister.emailNews}"/>
                                        </div>
                                        <div class="col-auto btnNews position-relative">
                                            <h:commandButton styleClass="btn btn-subs" actionListener="#{beanRegister.guardarMail()}" value="Suscribirme"/>
                                        </div>
                                    </div>
                                </h:form>
                            </div>
                        </div>
                    </div>
                </section>
                <div class="position-relative w-100 pie">
                    <div class="redes position-absolute start-50 translate-middle">
                        <ul>
                            <li><a href="#" target="blank"><i class="neg fab fa-github"></i></a></li>
                            <li><a href="#" target="blank"><i class="neg fab fa-instagram"></i></a></li>
                            <li><a href="#" target="blank"><i class="neg fab fa-linkedin-in"></i></a></li>
                            <li><a href="#" target="blank"><i class="neg fab fa-codepen"></i></a></li>
                        </ul>
                    </div>
                </div>
            </footer>
          </div> 
        <!--  FIN FOOTER -->
        
        </div>
        
        
        
            <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
        </body>
    </html>
</f:view>
