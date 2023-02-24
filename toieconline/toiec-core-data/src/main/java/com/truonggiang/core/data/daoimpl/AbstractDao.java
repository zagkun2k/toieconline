package com.truonggiang.core.data.daoimpl;

import com.truonggiang.core.common.constant.CoreConstant;
import com.truonggiang.core.common.utils.HibernateUtil;
import com.truonggiang.core.data.dao.GenericDao;
import org.hibernate.*;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class AbstractDao<ID extends Serializable, Entity> implements GenericDao<ID, Entity> {

    private Class<Entity> persistenceClass;

    public AbstractDao () {
        this.persistenceClass = (Class<Entity>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    public String getPersistenceClassName () {
        return persistenceClass.getSimpleName();
    }

    @Override
    public List<Entity> findAll() {
        List<Entity> list = new ArrayList<>();
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            StringBuilder sql = new StringBuilder("from ");
            sql.append(this.getPersistenceClassName());
            Query query = session.createQuery(sql.toString());
            list = query.list();
            transaction.commit();
        } catch (HibernateError e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public Entity update(Entity entity) {
        Entity result = null;
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            Object object = session.merge(entity);
            result = (Entity) object;
            transaction.commit();
        } catch (HibernateError e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public void save(Entity entity) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (HibernateError e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public Entity findById(ID id) {
        Entity result = null;
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            result = (Entity) session.get(this.persistenceClass, id);
            if (result == null) {
                throw new ObjectNotFoundException(" NOT FOUND " + id, null);
            }
            transaction.commit();
        } catch (HibernateError e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public Object[] findByProperty(String property, Object value, String sortExpression, String sortDirection) {
        //Khoi tao List chua cac Entity
        List<Entity> list = new ArrayList<>();
        //Khoi tao bien chua size list( Do query.list.get(int index) return ve object nen khoi tao kieu object )
        Object totalItem = 0;

        //Khoi tao transaction
        Transaction transaction = null;
        //Khoi tao session
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            //Mo transaction
            transaction = session.beginTransaction();

            //Truy van List Entity
            StringBuilder sqlList = new StringBuilder("from ");
            sqlList.append(this.getPersistenceClassName());
            if (property != null && value != null) {
                sqlList.append(" where ").append(property).append("= :value");
            }
            if (sortExpression != null && sortDirection != null) {
                sqlList.append(" order by ").append(sortExpression).append(" " + (sortDirection.equals(CoreConstant.SORT_ASC) ? "asc" : "desc"));
            }
            Query queryList = session.createQuery(sqlList.toString());
            if (value != null) {
                queryList.setParameter("value", value);
            }
            //Tra ve list
            list = queryList.list();

            //Truy van size List Entity
            StringBuilder sqlSize = new StringBuilder("select count(*) from ");
            sqlSize.append(this.getPersistenceClassName());
            if (property != null && value != null) {
                sqlSize.append(" where ").append(property).append("= :value");
            }
            Query querySize = session.createQuery(sqlSize.toString());
            if (value != null) {
                querySize.setParameter("value", value);
            }
            //Tra ve kieu Object , dung de lay ra size list
            totalItem = querySize.list().get(0);

            //Xac nhan giao dich
            transaction.commit();
        } catch (HibernateError e) {

            //Giao dich lai
            transaction.rollback();
        } finally {

            //Dong session
            session.close();
        }

        // Return ve mang object
        return new Object[]{totalItem, list};
    }

    @Override
    public Integer delete(List<ID> ids) {
        Integer countDelete = 0;
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            for (ID item: ids) {
                Entity entity = (Entity) session.get(this.persistenceClass, item);
                session.delete(entity);
                countDelete++;
            }
            transaction.commit();
        } catch (HibernateError e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }

        return countDelete;
    }
}
