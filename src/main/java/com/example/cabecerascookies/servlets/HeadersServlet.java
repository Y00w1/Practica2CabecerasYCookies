package com.example.cabecerascookies.servlets;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HeadersServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String methodHttp = request.getMethod();
        //Obtiene el método de la solicitud (GET, POST, PUT, DELETE)
        String requestUri = request.getRequestURI();
        // El URI (Uniform Resource Identifier) identifica un recurso o un nombre en internet,
        //Determina la página o recurso solicitado
        String requestUrl = request.getRequestURL().toString();
        //Obtiene la URL (Uniform Resource Locator), genera enlaces a la página o recurso solicitado.
        String contextPath = request.getContextPath();
        //Guarda el contexto de la aplicación web, se puede usar para determinar la raíz
        String servletPath = request.getServletPath();
        //Obtine la ruta del servlet que ha procesado la solicitud.
        String ipClient = request.getLocalAddr();
        //Obtiene la dirección ip del cliente que ha realizado la solicitud
        String ip = request.getLocalAddr();
        //Guarda la dirección ip del servidor
        int port = request.getLocalPort();
        //Obtiene el puerto usado para la conexión
        String scheme = request.getScheme();
        //Retorna el protocolo de  la conexión (HTTP o HTTPS)
        String host = request.getHeader("Host");
        //Obtiene el nombre del host que ha realizado la solicitud
        String url = scheme + "://" + host + contextPath + servletPath;
        //Combina el protocolo, host, contexto y servlet para formar una url a la pagina o recurso solicitado
        String url2 = scheme + "://" + port + contextPath + servletPath;
        //Igual que el anterior, pero en caso de que se use un puerto diferetne al predeterminado

        getServletContext().getRequestDispatcher("/productos.html").forward(request, response);
        //No cambia la URL del navegador y el cliente no nota el reenvio interno que se da a otra página o recurso
        response.sendRedirect(request.getContextPath() + "/productos.html");
        //Send Redirect envia una respuesta HTTP de redirección al navegador del cliente


        response.setContentType("text/html");
    }

    public void destroy() {
    }
}