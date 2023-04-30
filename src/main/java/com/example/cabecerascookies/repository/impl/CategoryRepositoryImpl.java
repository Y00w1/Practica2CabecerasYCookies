package com.example.cabecerascookies.repository.impl;

import com.example.cabecerascookies.model.Category;
import com.example.cabecerascookies.repository.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CategoryRepositoryImpl implements Repository<Category> {
    private Connection conn;

    public CategoryRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Category> list() throws SQLException {
        return null;
    }

    @Override
    public Category byId(Long id) throws SQLException {
        return null;
    }

    @Override
    public void save(Category category) throws SQLException {

    }

    @Override
    public void delete(Long id) throws SQLException {

    }
}
