package com.example.cabecerascookies.servlets;

import com.example.cabecerascookies.model.Product;
import com.example.cabecerascookies.service.ProductService;
import com.example.cabecerascookies.service.impl.ProductServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet({"/products.xls", "/products.html", "/products"})
public class ProductXlsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductService service = new ProductServiceImpl();
        String id = req.getParameter("id");
        List<Product> products = getProdByID(service, id);



        resp.setContentType("text/html;charset=UTF-8");
        String servletPath = req.getServletPath();
        boolean isXls = servletPath.endsWith(".xls");
        if (isXls) {
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("Content-Disposition", "attachment;filename=products.xls");
        }
        try (PrintWriter out = resp.getWriter()) {

            if (!isXls){
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println(" <head>");
                out.println(" <meta charset=\"UTF-8\">");
                out.println(" <title>Listado de Productos</title>");
                out.println(" </head>");
                out.println(" <body>");
                out.println(" <h1>Listado de Productos!</h1>");
                out.println("<p><a href=\"" + req.getContextPath() + "/productos.xls" + "\">exportar a xls</a></p>");
                out.println("<p><a href=\"" + req.getContextPath() + "/productos.json" + "\">mostrar json</a></p>");
                out.println("<form method=\"get\">");
                out.println("<label for=\"id\">Buscar por ID:</label>");
                out.println("<input type=\"text\" id=\"id\" name=\"id\">");
                out.println("<button type=\"submit\">Buscar</button>");
                out.println("</form>");
            }
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>id</th>");
            out.println("<th>nombre</th>");
            out.println("<th>tipo</th>");
            out.println("<th>precio</th>");
            out.println("</tr>");
            if (products == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No products found");
            }else {
                products.forEach(p->{
                    out.println("<tr>");
                    out.println("<td>" + p.getId() + "</td>");
                    out.println("<td>" + p.getName() + "</td>");
                    out.println("<td>" + p.getType() + "</td>");
                    out.println("<td>" + p.getPrice() + "</td>");
                    out.println("</tr>");
                });
            }
            out.println("</table>");
            if (!isXls){
                out.println(" </body>");
                out.println("</html>");
            }
        }
    }

    private List<Product> getProdByID (ProductService service, String id){
        List<Product> products;
        if (id != null){
            Product product = service.getProduct(Integer.parseInt(id));
            if (product != null){
                products = new ArrayList<>(List.of(product));
            }else {
                return null;
            }
        }else {
            products = service.list();
        }
        return products;
    }

}
