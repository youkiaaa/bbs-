package com.atyume.modules.base.service;

import com.atyume.modules.base.query.PageQuery;

import java.util.List;

public interface IService<T> {

    List<T> queryAll();

    List<T> queryList(T entity);

    T queryOne(T entity);

    T queryById(Object id);

    List<T> queryList(T entity, PageQuery pageQuery);

    int create(T entity);

    int updateAll(T entity);

    int updateNotNull(T entity);

    int delete(T entity);

    int deleteById(Object id);

    int count(T entity);

}
