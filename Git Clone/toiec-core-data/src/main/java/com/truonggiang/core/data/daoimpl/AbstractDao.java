package com.truonggiang.core.data.daoimpl;

import com.truonggiang.core.common.utils.HibernateUtil;
import com.truonggiang.core.data.dao.GenericDao;
import org.hibernate.HibernateError;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class AbstractDao<ID extends Serializable, T> implements GenericDao<ID, T> {

    private Class<T> persistenceClass;

    public AbstractDao () {
        this.persistenceClass = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    public String getPersistenceClassName () {
        return persistenceClass.getSimpleName();
    }

    protected Session GetSession () {
        return HibernateUtil.getSessionFactory().openSession();
    }
    @Override
    public List<T> findAll() {
        List<T> list = new ArrayList<>();
        Transaction transaction = null;

        try {
            transaction = this.GetSession().beginTransaction();
            StringBuilder sql = new StringBuilder("from ");
            sql.append(this.getPersistenceClassName());
            Query query = this.GetSession().createQuery(sql.toString());
            list = query.list();
            transaction.commit();
        } catch (HibernateError e) {
            transaction.rollback();
            throw e;
        }
        return list;
    }
}
