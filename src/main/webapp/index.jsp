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
        </head>
        <body>
            <div class="sticky-top contaier-fuild">
                <div class="row fondo">
                </div>
                <header class="sticky-top container-fluid fondoBarra">
                    <div class="container-fluid">
                        <nav class="navbar bg-transparent navbar-light">
                            <div class="container-fluid d-flex flex-row">
                                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                                        data-bs-target="#collapsibleNavbar">
                                    <span class="navbar-toggler-icon m-auto "></span>
                                </button>
                                <a class="navbar-brand fs-3" href="#"><img class="img-responsive" height="80" src="./img/logo.png" alt="Logotipo del periodico Delta."/></a>
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
            <div class="container-fluid">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-8">
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
                        <div class="col-lg-4">
                        <div class="row">
                            <div class="col-sm-6">
                            <div class="py-3 border-bottom">
                                <div class="d-flex align-items-center pb-2">
                                <img src="assets/images/dashboard/Profile_1.jpg" class="img-xs img-rounded mr-2" alt="thumb">
                                <span class="fs-12 text-muted">Henry Itondo</span>
                                </div>
                                <p class="fs-14 m-0 font-weight-medium line-height-sm">
                                The Most And Least Visited Countries In The World
                                </p>
                            </div>
                            </div>
                            <div class="col-sm-6">
                            <div class="py-3 border-bottom">
                                <div class="d-flex align-items-center pb-2">
                                <img src="assets/images/dashboard/Profile_2.jpg" class="img-xs img-rounded mr-2" alt="thumb">
                                <span class="fs-12 text-muted">Oka Tomoaki</span>
                                </div>
                                <p class="fs-14 m-0 font-weight-medium line-height-sm">
                                The Best Places to Travel in month August
                                </p>
                            </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6">
                            <div class="pt-4 pb-4 border-bottom">
                                <div class="d-flex align-items-center pb-2">
                                <img src="assets/images/dashboard/Profile_2.jpg" class="img-xs img-rounded mr-2" alt="thumb">
                                <span class="fs-12 text-muted">Joana Leite</span>
                                </div>
                                <p class="fs-14 m-0 font-weight-medium line-height-sm">
                                Focus On Fun And Challenging Lifetime Activities
                                </p>
                            </div>
                            </div>
                            <div class="col-sm-6">
                            <div class="pt-3 pb-4 border-bottom">
                                <div class="d-flex align-items-center pb-2">
                                <img src="assets/images/dashboard/Profile_4.jpg" class="img-xs img-rounded mr-2" alt="thumb">
                                <span class="fs-12 text-muted">Rita Leite</span>
                                </div>
                                <p class="fs-14 m-0 font-weight-medium line-height-sm">
                                Bread Is The Most Widely Consumed Food In The World
                                </p>
                            </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6">
                            <div class="pt-4 pb-4">
                                <div class="d-flex align-items-center pb-2">
                                <img src="assets/images/dashboard/Profile_5.jpg" class="img-xs img-rounded mr-2" alt="thumb">
                                <span class="fs-12 text-muted">Jurrien Oldhof</span>
                                </div>
                                <p class="fs-14 m-0 font-weight-medium line-height-sm">
                                What Is Music, And What Does It Mean To Us
                                </p>
                            </div>
                            </div>
                            <div class="col-sm-6">
                            <div class="pt-3 pb-4">
                                <div class="d-flex align-items-center pb-2">
                                <img src="assets/images/dashboard/Profile_6.jpg" class="img-xs img-rounded mr-2" alt="thumb">
                                <span class="fs-12 text-muted">Yamaha Toshinobu</span>
                                </div>
                                <p class="fs-14 m-0 font-weight-medium line-height-sm">
                                Is Breakfast The Most Important Meal Of The Day
                                </p>
                            </div>
                            </div>
                        </div>
                        </div>
                    </div>
                </div>
                <h:form>
                    <h:commandButton value="Iniciar sesión" action="alta"/>
                </h:form>
            </div>
            <footer class="bd-footer">
                <section class="subs" id="signup">
                    <div class="container px-4 px-lg-5">
                        <div class="row gx-4 gx-lg-5">
                            <div class="col-md-10 col-lg-8 mx-auto text-center">
                                <i class="far fa-paper-plane fa-2x mb-2 text-white"></i>
                                <h2 class="text-white mb-5">¡Suscribete para recibir nuevas noticias!</h2>
                                <form class="form-subs" id="contactForm" data-sb-form-api-token="API_TOKEN">

                                    <div class="row input-group-newsletter">
                                        <div class="col"><input class="form-control" id="emailAddress" type="email" placeholder="Introduzca un email..." aria-label="Introduzca un email" data-sb-validations="required,email" /></div>
                                        <div class="col-auto"><button class="btn btn-subs disabled" id="submitButton" type="submit">Suscribirme</button></div>
                                    </div>
                                    <div class="invalid-feedback mt-2" data-sb-feedback="emailAddress:required">Es requerido un email.</div>
                                    <div class="invalid-feedback mt-2" data-sb-feedback="emailAddress:email">Email no válido.</div>
                                    <!-- Submit success message-->
                                    <!---->
                                    <!-- This is what your users will see when the form-->
                                    <!-- has successfully submitted-->
                                    <div class="d-none" id="submitSuccessMessage">
                                        <div class="text-center mb-3 mt-2 text-white">
                                            <div class="fw-bolder">¡Enviado Correctamente!</div>
                                            To activate this form, sign up at
                                            <br />
                                            <a href=""></a>
                                        </div>
                                    </div>
                                    <!-- Submit error message-->
                                    <!---->
                                    <!-- This is what your users will see when there is-->
                                    <!-- an error submitting the form-->
                                    <div class="d-none" id="submitErrorMessage"><div class="text-center text-danger mb-3 mt-2">Error enviando el mensaje</div></div>
                                </form>
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
            <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
        </body>
    </html>
</f:view>
