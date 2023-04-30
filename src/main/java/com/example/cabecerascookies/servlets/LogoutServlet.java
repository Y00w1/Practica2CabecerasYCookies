package com.example.cabecerascookies.servlets;

import com.example.cabecerascookies.service.LoginService;
import com.example.cabecerascookies.service.impl.LoginServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth = new LoginServiceImpl();
        Optional<String> username = auth.getUsername(req);
        if (username.isPresent()){
            Cookie userC = new Cookie("username", "");
            userC.setMaxAge(0);
            resp.addCookie(userC);
        }
        resp.sendRedirect(req.getContextPath() + "/login.html");
    }
}
