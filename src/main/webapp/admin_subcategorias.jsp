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
            <title>Delta (Administración) | Diario online líder de información en español</title>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
            <script src="https://code.jquery.com/ui/1.13.0/jquery-ui.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/js/all.min.js"></script>
            <script src="./js/lib/owl.carousel.min.js"></script>
            <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.css">
            <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js"></script>
            <script src="./js/ipl.js"></script>
            <script src="./js/utilidades.js"></script>
            <script src="./js/index.js"></script>
            <script src="./js/articulo.js"></script>
            <script src="./js/admin.js"></script>
            
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/css/bootstrap.min.css"/>
            <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.0/themes/smoothness/jquery-ui.css"/>
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"/>
            <link rel="stylesheet" href="./css/lib/owl.carousel.min.css"/>
            <link rel="stylesheet" href="./css/lib/owl.theme.default.min.css"/>
            <link rel="stylesheet" href="./css/admin.css"/>
            <link rel="shortcut icon" type="image/x-icon" href="./img/assets/FAVICONDELTA.ico">
        </head>
        <body>
            <div class="d-flex">
                <!-- Sidebar -->
                <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion px-md-3 shadow" id="accordionSidebar">
                    <!-- Sidebar - Brand -->
                    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/Delta/faces/index.jsp">
                        <img class="img-responsive logo" height="100" src="./img/assets/MODOCLARO.png" alt="Logotipo del periodico Delta."/>
                    </a>
                    <!-- Divider -->
                    <hr class="sidebar-divider">
                    <!-- Nav Item - Dashboard -->
                    <li class="nav-item active">
                        <a class="nav-link text-white" href="/Delta/faces/administracion.jsp">
                            <i class="fas fa-fw fa-tachometer-alt"></i>
                            <span>Estadísticas</span>
                        </a>
                    </li>
                    <!-- Divider -->
                    <hr class="sidebar-divider">
                    <!-- Heading -->
                    <div class="sidebar-heading">
                        Control Principal
                    </div>
                    <!-- Nav Item - Pages Collapse Menu -->
                    <li class="nav-item active">
                        <a class="nav-link text-white" href="/Delta/faces/admin_articulos.jsp">
                            <i class="fa-solid fa-newspaper"></i>
                            <span>Articulos</span>
                        </a>
                    </li>
                    <h:panelGroup layout="block" id="container" rendered="#{beanAdministracion.usu.permiso==2}">
                        <li class="nav-item active">
                            <a class="nav-link text-white" href="/Delta/faces/admin_categorias.jsp">
                                <i class="fa-regular fa-star"></i>
                                <span>Categorias</span>
                            </a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link text-white" href="/Delta/faces/admin_subcategorias.jsp">
                                <i class="fa-solid fa-star"></i>
                                <span>Subcategorias</span>
                            </a>
                        </li>
                        <!-- Divider -->
                        <hr class="sidebar-divider">
                        <!-- Heading -->
                        <div class="sidebar-heading">
                            Control Usuarios
                        </div>
                        <li class="nav-item active">
                            <a class="nav-link text-white" href="/Delta/faces/admin_usuarios.jsp">
                                <i class="fa-solid fa-users"></i>
                                <span>Usuarios</span>
                            </a>
                        </li>
                    </h:panelGroup>
                </ul>
                <div id="content-wrapper" class="d-flex flex-column w-100">
                    <div id="content">
                        <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow justify-content-end">
                            <div class="topbar-divider d-none d-sm-block"></div>
                            <div>
                                <a class="nav-link " href="#">
                                    <i class="fa-solid fa-user-gear me-1"></i>
                                    <span class="mr-2 d-none d-lg-inline text-black small me-1"><h:outputText value="Editor" rendered="#{beanAdministracion.usu.permiso==1}"/></span>
                                    <span class="mr-2 d-none d-lg-inline text-black small"><h:outputText value="Administrador" rendered="#{beanAdministracion.usu.permiso==2}"/></span>
                                    <span class="mr-2 d-none d-lg-inline text-black small mx-1">|</span>
                                    <span class="mr-2 d-none d-lg-inline text-black small"><h:outputText value="#{beanAdministracion.usu.nombre}"/></span>
                                    <i class="fa-solid fa-screwdriver-wrench me-1"></i>
                                </a>
                            </div>
                        </nav>
                    </div>
                    <div>
                        <header class="align-self-center fs-1 text-center fw-bold">Control de Sub Categorías</header>
                        <div class="w-75 p-3 m-auto">
                        <h:form>
                            <h:dataTable styleClass="tablaPlugin" value="#{beanAdministracion.listaSubCategorias}" var="su" binding="#{beanAdministracion.tablaSubCategorias}">
                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText value="ID"/>
                                    </f:facet>
                                    <h:outputText value="#{su.codSubcategoria}"/>
                                </h:column>
                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText value="Nombre"/>
                                    </f:facet>
                                    <h:outputText value="#{su.nombre}"/>
                                </h:column>
                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText value="Categoria Padre"/>
                                    </f:facet>
                                    <h:outputText value="#{su.codCategoria.categoria}"/>
                                </h:column>
                                <h:column>
                                    <h:commandButton styleClass="btn btn-danger" value="Eliminar" actionListener="#{beanAdministracion.eliminarSub()}"/>
                                </h:column>
                            </h:dataTable>
                        </h:form>
                        </div>
                        <div class="w-75 p-3 m-auto formuCreaArt mt-3">
                            <header class="align-self-center fs-1 text-center fw-bold">Crear Sub Categoria</header>
                            <h:form styleClass="mt-3 w-50">
                                <div class="distinto"><label>Nombre Subcategoria: </label><h:inputText value="#{beanAdministracion.nombreSubcategoria}"/></div>
                                <div class="distinto"><label>Categoria Padre: </label><h:selectOneMenu value="#{beanAdministracion.categoriaPadreSubcategoria}">
                                    <f:selectItems value="#{beanAdministracion.listaSelectSub}"/>
                                </h:selectOneMenu></div>
                                <div class="distinto"><h:commandButton styleClass="btn btn-success" value="Guardar" actionListener="#{beanAdministracion.guardarSub()}"/></div>
                            </h:form>
                        </div>
                    </div>
                </div>
            </div>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
        </body>
    </html>
</f:view>
