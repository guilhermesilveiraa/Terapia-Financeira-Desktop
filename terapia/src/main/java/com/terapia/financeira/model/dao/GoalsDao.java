package com.terapia.financeira.model.dao;

import java.util.List;

import com.terapia.financeira.model.entities.Goals;

public interface  GoalsDao {
    void insert(Goals obj);
    void update(Goals obj);
    void delete(int id);
    Goals findByTitle(String key);
    List<Goals> findByType(Integer type);
}
