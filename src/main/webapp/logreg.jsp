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
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/css/bootstrap.min.css"/>
            <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.0/themes/smoothness/jquery-ui.css"/>
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"/>
            <link rel="stylesheet" href="css/estiloLogin.css"/>
            <script src="./js/logreq.js"></script>
        </head>
        <body>
            <h:form>
                <label>EMAIL: </label> <h:inputText required="" value="#{beanLogin.email}"/><br>
                <label>CONTRASEÑA: </label> <h:inputSecret required="" value="#{beanLogin.passwd}"/><br>
                <h:commandButton action="#{beanLogin.esUsuario()}" value="Login"/>
            </h:form>
                
                
                <h4><h:outputText value="#{beanLogin.error}" /></h4> 
                
            <h:form>
                <label>EMAIL: </label> <h:inputText required="" value="#{beanRegister.email}"/><br>
                <label>Fecha Nacimiento: </label> <h:inputText styleClass="fecha" required="" value="#{beanRegister.fecha_naci}"/><br>
                <label>Pais: </label> <h:inputText required="" value="#{beanRegister.pais}"/><br>
                <label>Nombre: </label> <h:inputText required="" value="#{beanRegister.nombre}"/><br>
                <label>Apellidos: </label> <h:inputText required="" value="#{beanRegister.apellidos}"/><br>
                <label>CONTRASEÑA: </label> <h:inputSecret required="" value="#{beanRegister.passwd}"/><br>
                <h:commandButton action="#{beanRegister.guardarUsuario()}" value="Registrar"/>
            </h:form>
            
                <div class="row fondo"></div>

                <div class="container border border-success border-3 rounded-3 bordeColor">

                    <div class="row ">
                        <div class="botones">
                            <input type="radio" name="tipo" value="login" id="log"  checked onClick="cambiaField(this)"><label for="log">Inicia Sesión</label>
                            <input type="radio" name="tipo" value="reg" id="reg" onClick="cambiaField(this)"><label for="reg">Registrarse</label>
                        </div>
                        <form class="p-5 " id="formLog">
                            <fieldset>
                                <div class="row d-flex align-middle justify-content-end">

                                    <img class="col-3 img-fluid mb-4" src="" alt="Logotipo Página">

                                </div>

                                <div class="col form-floating mb-3">
                                    <input type="email" class="form-control" id="correoLog" placeholder="name@example.com">
                                    <label for="correo"><i class="fa-solid fa-envelope me-2"></i>Correo Electrónico</label>
                                </div>
                                <div class="col form-floating mb-3">
                                    <input type="password" class="form-control" id="contraLog" placeholder="Contraseña">
                                    <label for="contra"><i class="fa-solid fa-lock me-2"></i>Contraseña</label>
                                </div>

                                <div class="col">
                                    <a href="#" class="link-primary">¿Has olvidado la contraseña?</a>
                                </div>

                                <div class="row mt-5">
                                    <div class="col-12 text-center">
                                        <button class="botonsillo colorsillo" type="submit">Entrar</button>
                                    </div>
                                </div>

                                <div class="row mt-5">
                                    <div class="col-12 text-center d-flex justify-content-around">

                                        <button type="button" class="btnGoogle"><a href="#">Inicia sesión con Google</a></button>


                                        <button type="button" class="btnFace"><a href="#"><i class="fa-brands fa-facebook-f iconFace"></i>Inicia sesión con Facebook</a></button>


                                    </div>
                                </div>
                            </fieldset>

                        </form>
                    </div>
                    <div class="row ">
                        <form class="p-5" id="formReg">
                            <fieldset>
                                <div class="row d-flex justify-content-end align-middle">

                                    <img class="col-3 img-fluid mb-4" src="" alt="Logotipo Página">

                                </div>

                                <div class="row row-cols-2">

                                    <div class="col form-floating mb-3">
                                        <input type="text" class="form-control" id="nomReg" placeholder="Juan">
                                        <label for="nomReg" class="ms-2"><i class="fa-solid fa-user me-2"></i>Nombre</label>
                                    </div>

                                    <div class="col form-floating mb-3">
                                        <input type="text" class="form-control" id="apeReg" placeholder="Rojas Rosado">
                                        <label for="apeReg" class="ms-2"><i class="fa-regular fa-user me-2"></i>Apellidos</label>
                                    </div>

                                    <div class="col form-floating mb-3">
                                        <input type="email" class="form-control" id="correoReg" placeholder="name@example.com">
                                        <label for="correo" class="ms-2"><i class="fa-solid fa-envelope me-2"></i>Correo Electrónico</label>
                                    </div>

                                    <div class="col form-floating mb-3">
                                        <input type="password" class="form-control" id="contraReg" placeholder="Contraseña">
                                        <label for="contra" class="ms-2"><i class="fa-solid fa-lock me-2"></i>Contraseña</label>
                                        <div id="passwordHelpBlock" class="form-text">
                                            Tu contraseña debe tener entre 8-20 caractéres, contener números y letras sin espacios o carácter especial.
                                        </div>
                                    </div>

                                    <div class="col form-floating mb-3">
                                        <input type="text" class="form-control" id="fechReg" placeholder="05/02/1999">
                                        <label for="fechReg" class="ms-2"><i class="fa-solid fa-calendar-days me-2"></i>Fecha Nacimiento</label>
                                    </div>

                                    <div class="col form-floating mb-3">
                                        <input type="text" class="form-control" id="paisReg" placeholder="05/02/1999">
                                        <label for="paisReg" class="ms-2"><i class="fa-solid fa-earth-americas me-2"></i>País</label>
                                    </div>
                                </div>

                                <div class="row mt-5">
                                    <div class="col-12 text-center">
                                        <button class="botonsillo colorsillo" type="submit">Registrarse</button>
                                    </div>
                                </div>

                                <div class="row mt-5">
                                    <div class="col-12 text-center d-flex justify-content-around">

                                        <button type="button" class="btnGoogle"><a href="#">Registrarse con Google</a></button>


                                        <button type="button" class="btnFace"><a href="#"><i class="fa-brands fa-facebook-f iconFace"></i>Registrarse con Facebook</a></button>


                                    </div>
                                </div>
                            </fieldset>

                        </form>
                    </div>





                </div>
                
                   
                
                
            <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
        </body>
    </html>
</f:view>
