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
            <script src="./js/ipl.js"></script>
            <script src="./js/utilidades.js"></script>
            <script src="./js/index.js"></script>
            <script src="./js/categorias.js"></script>
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/css/bootstrap.min.css"/>
            <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.0/themes/smoothness/jquery-ui.css"/>
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"/>
            <link rel="stylesheet" href="./css/lib/owl.carousel.min.css"/>
            <link rel="stylesheet" href="./css/lib/owl.theme.default.min.css"/>
            <link rel="stylesheet" href="./css/estilo.css"/>
            <link rel="stylesheet" href="./css/estiloArticulo.css"/>
            <link rel="stylesheet" href="./css/subcate.css"/>
            <link rel="shortcut icon" type="image/x-icon" href="./img/assets/FAVICONDELTA.ico">
        </head>
        <body>
            <div class="contenedor">
                <div class="loading">
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                </div>
            </div>
            <div class="contaier-fuild">

            <!--  HEADER  -->
            <div class="sticky-top">
              
                <div class="contaier-fuild">

                    <div class="row fondo">
                    </div>

                    <header class="sticky-top container-fluid fondoBarra">
                        <div class="container-fluid">
                            <nav class="navbar bg-transparent ">
                                <div class="container-fluid caja">
                                    <div class="col d-flex justify-content-start">
                                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                                                data-bs-target="#collapsibleNavbar">
                                            <span class="navbar-toggler-icon m-auto "></span>
                                        </button>
                                    </div>
                                    <div class="col d-flex justify-content-center">
                                        <a class="navbar-brand fs-3 m-0" href="/Delta/faces/index.jsp"><img class="img-responsive logo" height="100" src="./img/assets/MODOCLARO.png" alt="Logotipo del periodico Delta."/></a>
                                    </div>
                                    <div class="col d-flex justify-content-end">
                                        <div class="d-flex flex-row align-items-center">
                                          
                                            <div class="me-2 dropdown text-end desplegable">
                                                <h:form styleClass="fAdmin">
                                                    <h:commandButton styleClass="btn btn-fondoPer" value="Administración" action="admin.jsp" rendered="#{beanLogin.usu.permiso>0}"/>
                                                </h:form>
                                                <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
                                                  <i class="fa-solid fa-user usua me-2 fs-1" data-bs-toggle="tooltip" data-bs-placement="bottom" title="Inicio de Sesión"></i>
                                                  <h:outputText value="#{beanLogin.nomUsu}"/>
                                                </a>
                                                <ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1">
                                                    <li>
                                                      <h:form styleClass="dropdown-item botonsillo">
                                                        <h:commandButton value="Accesibilidad" action="a" styleClass="btn"/>
                                                      </h:form>
                                                    </li>
                                                    
                                                    <li><hr class="dropdown-divider"></li>
                                                    <h:form styleClass="dropdown-item botonsillo" rendered="#{empty beanLogin.nomUsu}">
                                                        <li>
                                                            <h:commandLink value="Iniciar sesión" action="logreg.jsp" styleClass="btn"/>
                                                        </li>
                                                    </h:form>
                                                    <h:form styleClass="dropdown-item botonsillo" rendered="#{not empty beanLogin.nomUsu}">
                                                        <li>
                                                            <h:commandButton value="Cerrar Sesión" actionListener="#{beanLogin.logout()}" styleClass="btn"/>
                                                        </li>
                                                    </h:form>
                                                </ul>
                                            </div>
                                            <form class="col-12 col-md-auto mb-3 mb-md-0 me-md-3">
                                                <input type="search" class="form-control" placeholder="Buscar..." aria-label="Search">
                                            </form>
                                        </div>
                                    </div>
                                    <div class="collapse navbar-collapse justify-content-between" id="collapsibleNavbar">
                                        <ul class="navbar-nav">
                                            <div class="container-fluid d-flex flex-row subcategorias justify-content-center">
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
        <div class="container mt-3">
            <h1 class="nombreCategoriaCarga"></h1>
            <nav style="--bs-breadcrumb-divider: url(&#34;data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='8' height='8'%3E%3Cpath d='M2.5 0L1 1.5 3.5 4 1 6.5 2.5 8l4-4-4-4z' fill='currentColor'/%3E%3C/svg%3E&#34;);" aria-label="breadcrumb">
                <ol class="breadcrumb">
                  <li class="breadcrumb-item text-black"><a href="/Delta/faces/index.jsp"><i class="fa-solid fa-house"></i></a></li>
                  <li class="breadcrumb-item active nombreCategoriaCarga" aria-current="page"></li>
                </ol>
            </nav>
        </div>
        <div class="col-md-8 mt-5 offset-md-2 card-group">
            <div class="row cargaArticulosPorCategorias">
                
            </div>
        </div>
        
        
        
                <!--  FOOTER DE LA PÁGINA CON EL NEWSLETTER -->
         <div class="">  
            <footer class="bd-footer">
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
                            <li><a href="#" target="blank"><i class="neg fab fa-brands fa-twitter"></i></a></li>
                            <li><a href="#" target="blank"><i class="neg fab fa-instagram"></i></a></li>
                            <li><a href="#" target="blank"><i class="neg fab fa-brands fa-facebook-f"></i></a></li>
                            <li><a href="#" target="blank"><i class="neg fab fa-brands fa-youtube"></i></a></li>
                            
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
