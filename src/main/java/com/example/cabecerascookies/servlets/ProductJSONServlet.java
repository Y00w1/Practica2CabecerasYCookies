package com.example.cabecerascookies.servlets;

import com.example.cabecerascookies.model.Product;
import com.example.cabecerascookies.service.ProductService;
import com.example.cabecerascookies.service.impl.ProductServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ProductJSONServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductService service = new ProductServiceImpl();
        List<Product> products = service.list();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(products);
        resp.setContentType("application/json");
        resp.getWriter().write(json);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream jsonStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        Product product = mapper.readValue(jsonStream, Product.class);
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()){

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println(" <head>");
            out.println(" <meta charset=\"UTF-8\">");
            out.println(" <title>Detalle Producto desde JSON</title>");
            out.println(" </head>");
            out.println(" <body>");
            out.println(" <h1>Detalle de producto desde json!</h1>");
            out.println("<ul>");
            out.println("<li>Id: "+ product.getId() + "</li>");
            out.println("<li>Nombre: " + product.getName() + "</li>");
            out.println("<li>Tipo: " + product.getType() + "</li>");
            out.println("<li>Precio: " + product.getPrice() + "</li>");
            out.println("</ul>");
            out.println(" </body>");
            out.println("</html>");


        }
    }
}
