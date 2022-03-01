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
            <script src="./js/logreq.js"></script>
        </head>
        <body>
            <h:form>
                <label>EMAIL: </label> <h:inputText required="" value="#{beanLogin.email}"/><br>
                <label>CONTRASEÑA: </label> <h:inputSecret required="" value="#{beanLogin.passwd}"/><br>
                <h:commandButton action="#{beanLogin.esUsuario()}" value="Login"/>
            </h:form>
                
            <h:form>
                <label>EMAIL: </label> <h:inputText required="" value="#{beanRegister.email}"/><br>
                <label>Fecha Nacimiento: </label> <h:inputText styleClass="fecha" required="" value="#{beanRegister.fecha_naci}"/><br>
                <label>Pais: </label> <h:inputText required="" value="#{beanRegister.pais}"/><br>
                <label>Nombre: </label> <h:inputText required="" value="#{beanRegister.nombre}"/><br>
                <label>Apellidos: </label> <h:inputText required="" value="#{beanRegister.apellidos}"/><br>
                <label>CONTRASEÑA: </label> <h:inputSecret required="" value="#{beanRegister.passwd}"/><br>
                <h:commandButton action="#{beanRegister.guardarUsuario()}" value="Registrar"/>
            </h:form>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
        </body>
    </html>
</f:view>
