package com.example.cabecerascookies.service;

import com.example.cabecerascookies.exception.ServiceJdbcException;
import com.example.cabecerascookies.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> list();
    Product getProduct(Integer id);
}
