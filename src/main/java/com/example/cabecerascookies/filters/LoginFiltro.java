package com.example.cabecerascookies.filters;

import com.example.cabecerascookies.service.LoginService;
import com.example.cabecerascookies.service.impl.LoginServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebFilter({"/products"})
public class LoginFiltro implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LoginService service = new LoginServiceImpl();
        Optional<String> username = service.getUsername((HttpServletRequest) request);
        if (username.isPresent()) {
            chain.doFilter(request, response);
        }else{
            ((HttpServletResponse) response) .sendError(HttpServletResponse.SC_UNAUTHORIZED, "You cannot access this service");
        }
    }

}
