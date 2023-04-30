package com.example.cabecerascookies.service.impl;

import com.example.cabecerascookies.exception.ServiceJdbcException;
import com.example.cabecerascookies.model.Category;
import com.example.cabecerascookies.model.Product;
import com.example.cabecerascookies.repository.Repository;
import com.example.cabecerascookies.repository.impl.CategoryRepositoryImpl;
import com.example.cabecerascookies.repository.impl.ProductoRepositoryImpl;
import com.example.cabecerascookies.service.ProductService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    private Repository<Product> productRepository;
    private Repository<Category> categoryRepository;

    public ProductServiceImpl(Connection connection) {
        this.productRepository = new ProductoRepositoryImpl(connection);
        this.categoryRepository = new CategoryRepositoryImpl(connection);
    }

    @Override
    public List<Product> list(){
        try{
            return productRepository.list();
        } catch (SQLException e) {
            System.out.println(e);
            //throw new ServiceJdbcException(e.getMessage(),e.getCause());

        }
        return null;
    }

    @Override
    public Product getProduct(Integer id) {
        return null;
    }



    /*
    private List<Product> products = Arrays.asList(new Product(1, "Notebook", "Computing", 175000.0),
            new Product(2, "Desktop", "Office", 100000.0),
            new Product(3, "Keyboard", "Computing", 40000.0)
    );

    @Override
    public List<Product> list() {
        return products;
    }

    @Override
    public Product getProduct(Integer id) {
         return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    */
     */
}
