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
            <link rel="stylesheet" href="css/estiloLogin.css"/>
            <link rel="shortcut icon" type="image/x-icon" href="./img/assets/FAVICONDELTA.ico">
            <script src="./js/logreq.js"></script>
        </head>
        <body>
            <div class="sticky-top contaier-fuild">
                <div class="row fondo">
                </div>
                <header class="sticky-top container-fluid fondoBarra">
                    <div class="container-fluid">
                        <nav class="navbar bg-transparent navbar-light">
                            <div class="container-fluid">
                                <div class="col d-flex justify-content-start">
                                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                                            data-bs-target="#collapsibleNavbar">
                                        <span class="navbar-toggler-icon m-auto "></span>
                                    </button>
                                </div>
                                <div class="col d-flex justify-content-center">
                                    <a class="navbar-brand fs-3 m-0" href="/Delta/faces/index.jsp"><img class="img-responsive" height="100" src="./img/assets/MODOCLARO.png" alt="Logotipo del periodico Delta."/></a>
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
                                        <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3">
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

                <div class="container border mt-5 border-success border-3 rounded-3 bordeColor">
                    <div class="row ">
                        <div class="botones">
                            <input type="radio" name="tipo" value="login" id="log" checked><label for="log">Inicia Sesión</label>
                            <input type="radio" name="tipo" value="reg" id="reg"><label for="reg">Registrarse</label>
                        </div>
                        <h:form styleClass="p-5 formuLog">
                            <fieldset>
                                <div class="row d-flex align-middle justify-content-end">

                                    <img class="col-3 img-fluid mb-4" src="" alt="Logotipo Página">

                                </div>

                                <div class="col form-floating mb-3">
                                    <h:inputText required="" value="#{beanLogin.email}" styleClass="form-control"/>
                                    <label for="correo"><i class="fa-solid fa-envelope me-2"></i>Correo Electrónico</label>
                                </div>
                                <div class="col form-floating mb-3">
                                    <h:inputSecret required="" value="#{beanLogin.passwd}" styleClass="form-control"/>
                                    <label for="contra"><i class="fa-solid fa-lock me-2"></i>Contraseña</label>
                                </div>

                                <div class="col">
                                    <a href="#" class="link-primary">¿Has olvidado la contraseña?</a>
                                </div>

                                <div class="row mt-5">
                                    <div class="col-12 text-center">
                                        <h:commandButton action="#{beanLogin.esUsuario()}" value="Entrar" styleClass="botonsillo colorsillo"/>
                                        
                                    </div>
                                </div>

                                <div class="row mt-5">
                                    <div class="col-12 text-center d-flex justify-content-around">
                                        <button type="button" class="btnGoogle"><a href="#">Inicia sesión con Google</a></button>

                                        <button type="button" class="btnFace"><a href="#"><i class="fa-brands fa-facebook-f iconFace"></i>Inicia sesión con Facebook</a></button>
                                    </div>
                                </div>
                                <div class="row mt-5">        
                                    <h4><h:outputText value="#{beanLogin.error}" /></h4>
                                </div>
                            </fieldset>
                        </h:form>
                    </div>
                
                    <div class="row ">
                        <h:form styleClass="p-5 formuReg">
                            <fieldset>
                                <div class="row d-flex justify-content-end align-middle">
                                    <img class="col-3 img-fluid mb-4" src="" alt="Logotipo Página">
                                </div>
                                <div class="row row-cols-2">

                                    <div class="col form-floating mb-3">
                                        <h:inputText required="" value="#{beanRegister.nombre}" styleClass="form-control"/>
                                        <label for="nomReg" class="ms-2"><i class="fa-solid fa-user me-2"></i>Nombre</label>
                                    </div>

                                    <div class="col form-floating mb-3">
                                        <h:inputText required="" value="#{beanRegister.apellidos}" styleClass="form-control"/>
                                        <label for="apeReg" class="ms-2"><i class="fa-regular fa-user me-2"></i>Apellidos</label>
                                    </div>

                                    <div class="col form-floating mb-3">
                                        <h:inputText required="" value="#{beanRegister.email}" styleClass="form-control"/>
                                        <label for="correo" class="ms-2"><i class="fa-solid fa-envelope me-2"></i>Correo Electrónico</label>
                                        <div id="emailHelpBlock" class="form-text">
                                            El email introducido debe contener un patron valido y ser real, para garantizar la comunicacion con usted.
                                        </div>
                                    </div>

                                    <div class="col form-floating mb-3">
                                        <h:inputSecret required="" value="#{beanRegister.passwd}" styleClass="form-control"/>
                                        <label for="contra" class="ms-2"><i class="fa-solid fa-lock me-2"></i>Contraseña</label>
                                        <div id="passwordHelpBlock" class="form-text">
                                            Tu contraseña debe tener entre 8-20 caractéres, contener números y letras sin espacios o carácter especial.
                                        </div>
                                    </div>

                                    <div class="col form-floating mb-3">
                                        <h:inputText required="" value="#{beanRegister.fecha_naci}" styleClass="fecha form-control"/>
                                        <label for="fechReg" class="ms-2"><i class="fa-solid fa-calendar-days me-2"></i>Fecha Nacimiento</label>
                                    </div>

                                    <div class="col form-floating mb-3">
                                        <h:inputText required="" value="#{beanRegister.pais}" styleClass="form-control"/>
                                        <label for="paisReg" class="ms-2"><i class="fa-solid fa-earth-americas me-2"></i>País</label>
                                    </div>
                                </div>

                                <div class="row mt-5">
                                    <div class="col-12 text-center">
                                        <h:commandButton action="#{beanRegister.guardarUsuario()}" value="Registrar" styleClass="botonsillo colorsillo"/>
                                    </div>
                                </div>
                                <div class="row mt-5">
                                    <div class="col-12 text-center d-flex justify-content-around">
                                        <button type="button" class="btnGoogle"><a href="#">Registrarse con Google</a></button>
                                        <button type="button" class="btnFace"><a href="#"><i class="fa-brands fa-facebook-f iconFace"></i>Registrarse con Facebook</a></button>
                                    </div>
                                </div>
                            </fieldset>
                        </h:form>
                    </div>
                </div>
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
            <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
        </body>
    </html>
</f:view>
