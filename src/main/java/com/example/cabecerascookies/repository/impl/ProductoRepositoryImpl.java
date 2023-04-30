package com.example.cabecerascookies.repository.impl;

import com.example.cabecerascookies.model.Product;
import com.example.cabecerascookies.repository.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositoryImpl implements Repository<Product> {
    private Connection conn;

    public ProductoRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Product> list() throws SQLException {
        List<Product> products = new ArrayList<>();
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT p.*, c.name as category FROM products as p inner join categories as c ON " +
                    "(p.category_id = c.id) order by p.id ASC")){
            while (rs.next()){
                Product p = getProduct(rs);
                products.add(p);
            }
        }
        return products;
    }

    @Override
    public Product byId(Long id) throws SQLException {
        return null;
    }

    @Override
    public void save(Product product) throws SQLException {

    }

    @Override
    public void delete(Long id) throws SQLException {

    }
}
