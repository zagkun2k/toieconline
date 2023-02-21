package com.truonggiang.core.common.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = bulildSessionFactory();
    private static SessionFactory bulildSessionFactory () {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            System.out.println("Initial session factory fail");
//            return (SessionFactory) new ExceptionInInitializerError(e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory () {
        return sessionFactory;
    }
}
