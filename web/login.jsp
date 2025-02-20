<%-- 
    Document   : login
    Created on : 20 feb 2025, 12:40:23
    Author     : pauladominguez
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Inicio de Sesión</title>
        <link rel="stylesheet" type="text/css" href="./estilos/estiloLogin.css">
        <link href="https://fonts.googleapis.com/css2?family=Switzer:wght@400;700&display=swap" rel="stylesheet">
    </head>
    <body>
        <header class="cabeceraInicio" id="cabecera">

            <div class="izquierdaCabecera">
                <h1>SV</h1>
            </div>

            <div class="centroCabecera">
                <nav class="menu-desplegable">
                    <ul>
                        <li style="margin-top: -5px;"><a href="inicio.jsp"><img src="./svg/home_24dp_WHITE_FILL0_wght400_GRAD0_opsz24.svg" alt="home"></a></li>
                        <li><a href="destinos.html">Destinos</a></li>
                        <li><a href="contacto.html">Contacto</a></li>
                        <li style="margin-top: -4px;"><a href="login.jsp"><img src="./svg/person_24dp_WHITE_FILL0_wght400_GRAD0_opsz24.svg" alt="usuario"></a></li>
                    </ul>
                </nav>

                <!-- Menú hamburguesa -->
                <div class="menu-hamburguesa" id="hamburger" onclick="toggleMenu()">
                    <div class="bar"></div>
                    <div class="bar"></div>
                    <div class="bar"></div>
                </div>            
            </div>

            <div class="derechaCabecera">
                <input type="text" placeholder="Buscar">
                <a href=""><img src="./svg/search_24dp_WHITE_FILL0_wght400_GRAD0_opsz24.svg" alt="lupa"></a>
            </div>
        </header>
        <main>
            <section class="inicioSesion">
                <div class="divInicioSesion">
                    <h1 class="tituloInicioSesion">Bienvenido</h1>
                    <h3 class="subtituloInicioSesion">Inicio de sesión</h3>
                    <form method="POST" class="formularioInicioSesion">
                        <label for="email" class="emailLogin" style="margin-right: 40px">Email</label>
                        <input type="email" name="email" required="">
                        <br><br>
                        <label for="password" class="passwordLogin">Contraseña</label>
                        <input type="password" name="password" required="">
                        <br><br>
                        <input type="submit" value="Iniciar Sesión" class="botonInicioSesion">
                        <a href="registro.jsp" class="enlaceRegistro">¿Aún no te has registrado?</a>
                    </form>
                    <br><br>
                    <c:if test="${not empty error}">
                        <div class="error">${error}</div>
                    </c:if>
                </div>
            </section>
        </main>
            
        <footer class="footerInicio">

            <div class="logoFooter">
                <h1>El Sueño del Viajero</h1>
                <div class="redesSociales">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="whitesmoke" stroke-width="1" stroke-linecap="round" stroke-linejoin="round" class="icon icon-tabler icons-tabler-outline icon-tabler-brand-instagram">
                        <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
                        <path d="M4 8a4 4 0 0 1 4 -4h8a4 4 0 0 1 4 4v8a4 4 0 0 1 -4 4h-8a4 4 0 0 1 -4 -4z" />
                        <path d="M9 12a3 3 0 1 0 6 0a3 3 0 0 0 -6 0" />
                        <path d="M16.5 7.5v.01" />
                    </svg>
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="whitesmoke" stroke-width="1" stroke-linecap="round" stroke-linejoin="round" class="icon icon-tabler icons-tabler-outline icon-tabler-brand-x">
                        <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
                        <path d="M4 4l11.733 16h4.267l-11.733 -16z" />
                        <path d="M4 20l6.768 -6.768m2.46 -2.46l6.772 -6.772" />
                    </svg>
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="whitesmoke" stroke-width="1" stroke-linecap="round" stroke-linejoin="round" class="icon icon-tabler icons-tabler-outline icon-tabler-brand-facebook">
                        <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
                        <path d="M7 10v4h3v7h4v-7h3l1 -4h-4v-2a1 1 0 0 1 1 -1h3v-4h-3a5 5 0 0 0 -5 5v2h-3" />
                    </svg>
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="whitesmoke" stroke-width="1" stroke-linecap="round" stroke-linejoin="round" class="icon icon-tabler icons-tabler-outline icon-tabler-brand-linkedin">
                        <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
                        <path d="M8 11v5" />
                        <path d="M8 8v.01" />
                        <path d="M12 16v-5" />
                        <path d="M16 16v-3a2 2 0 1 0 -4 0" />
                        <path d="M3 7a4 4 0 0 1 4 -4h10a4 4 0 0 1 4 4v10a4 4 0 0 1 -4 4h-10a4 4 0 0 1 -4 -4z" />
                    </svg>
                </div>
                
                <div class="enlacesFooter">
                    <p>Términos y Condiciones</p>
                    <p>Política de Privacidad</p>
                    <p>Política de Cookies</p>
                </div>
            </div>

            <div class="derechaFooter">
                <div class="contenedorDerechaFooter">
                    <div class="primeraColumnaFooter">
                        <h3>Destinos</h3>
                        <p>Europa</p>
                        <p>América</p>
                        <p>Asia</p>
                        <p>África</p>
                        <p>Oceanía</p>
                    </div>
                    <div class="segundaColumnaFooter">
                        <h3>Acerca de</h3>
                        <p>Nuestra historia</p>
                        <p>Nuestro equipo</p>
                        <p>Contacto</p>
                        <p>Trabaja con nosotros</p>
                    </div>
                    <div class="terceraColumnaFooter">
                        <h3>Enlaces</h3>
                        <p>Empieza a planificar</p>
                        <p>Blog</p>
                        <p>Explorar destinos</p>
                    </div>
                </div>
                <div class="copyRigthFooter">
                    <p>© 2021 El Sueño del Viajero. Todos los derechos reservados.</p>
                    <div>
                        <a href="#cabecera">Volver al Principio</a>
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="whitesmoke" stroke-width="1" stroke-linecap="round" stroke-linejoin="round" class="icon icon-tabler icons-tabler-outline icon-tabler-arrow-up">
                            <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
                            <path d="M12 5l0 14" />
                            <path d="M18 11l-6 -6" />
                            <path d="M6 11l6 -6" />
                        </svg>
                    </div>
                </div>
            </div>

        </footer>

        <script>
            function toggleMenu() {
                const menuDesplegable = document.querySelector('.menu-desplegable');
                menuDesplegable.classList.toggle('active');
            }
        </script>
    </body>
</html>
