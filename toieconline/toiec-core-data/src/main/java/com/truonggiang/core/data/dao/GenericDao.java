package com.truonggiang.core.data.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<ID extends Serializable, Entity> {
    List<Entity> findAll();
    Entity update(Entity entity);
    void save(Entity entity);
    Entity findById(ID id);
    Object[] findByProperty(String property, Object value, String sortExpression, String sortDirection);
    Integer delete(List<ID> ids);


}
