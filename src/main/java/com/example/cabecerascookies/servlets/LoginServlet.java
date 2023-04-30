package com.example.cabecerascookies.servlets;

import com.example.cabecerascookies.model.Product;
import com.example.cabecerascookies.service.LoginService;
import com.example.cabecerascookies.service.ProductService;
import com.example.cabecerascookies.service.impl.LoginServiceImpl;
import com.example.cabecerascookies.service.impl.ProductServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    final static String USERNAME = "admin";
    final static String PASSWORD = "1234";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = (Connection)req.getAttribute("conn");
        ProductService service = new ProductServiceImpl(conn);

        LoginService auth = new LoginServiceImpl();
        Optional<String> cookieOpt = auth.getUsername(req);

        if (cookieOpt.isPresent()) {
            resp.setContentType("Text/html; charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println(" <head>");
                out.println(" <meta charset=\"UTF-8\">");
                out.println(" <title>Hola " + cookieOpt.get() +"</title>");
                out.println(" </head>");
                out.println(" <body>");
                out.println(" <h1>Hola " + cookieOpt.get() + " has iniciado sesión con éxito!</h1>");
                out.println("<p><a href='" + req.getContextPath() +
                        "/index.html'>volver</a></p>");
                out.println("<p><a href='" + req.getContextPath() +
                        "/logout'>cerrar sesión</a></p>");
                out.println(" </body>");
                out.println("</html>");

            }
        }else{
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if(USERNAME.equals(username) && PASSWORD.equals(password)){

            HttpSession session = req.getSession();
            session.setAttribute("username", username);

            //Cookie usernameCookie = new Cookie("username", username);
            //resp.addCookie(usernameCookie);

            resp.sendRedirect(req.getContextPath() + "/login.html");
            /*
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()){
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println(" <head>");
                out.println(" <meta charset=\"UTF-8\">");
                out.println(" <title>Login correcto</title>");
                out.println(" </head>");
                out.println(" <body>");
                out.println(" <h1>Login correcto!</h1>");
                out.println(" <h3>Hola "+ username + " has iniciado sesión con éxito!</h3>");
                out.println(" </body>");
                out.println("</html>");
              }
             */
        } else {
            //req.setAttribute("name", "Test forward()");
            //getServletContext().getRequestDispatcher("/Login.jsp").forward(req,resp);
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Sorry you don't have access to this page");
        }
    }
}
