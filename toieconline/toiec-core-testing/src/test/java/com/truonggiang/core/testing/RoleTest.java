//package com.truonggiang.core.testing;
//
//import com.truonggiang.core.dao.RoleDao;
//import com.truonggiang.core.daoimpl.RoleDaoImpl;
//import com.truonggiang.core.persistence.entity.RoleEntity;
//import org.testng.annotations.Test;
//
//import javax.management.relation.Role;
//import java.util.ArrayList;
//import java.util.List;
//
//public class RoleTest {
//
//    @Test
//    public void checkFindAll() {
//        RoleDao roleDao = new RoleDaoImpl();
//        List<RoleEntity> roleEntityList = roleDao.findAll();
//    }
//
//    @Test
//    public void checkUpdateRole() {
//        RoleDao roleDao = new RoleDaoImpl();
//        RoleEntity roleEntity = new RoleEntity();
//        roleEntity.setRoleId(1);
//        roleEntity.setName("USER_1");
//
//        roleDao.update(roleEntity);
//    }
//
//    @Test
//    public void checkSaveRole() {
//        RoleDao roleDao = new RoleDaoImpl();
//        RoleEntity roleEntity = new RoleEntity();
//        roleEntity.setRoleId(3);
//        roleEntity.setName("ADMIN_1");
//        roleDao.update(roleEntity);
//    }
//
//    @Test
//    public void checkFindById() {
//        RoleDao roleDao = new RoleDaoImpl();
//        RoleEntity roleEntity = roleDao.findById(3);
//    }
//
//    @Test
//    public void checkFindByProperty() {
//        RoleDao roleDao = new RoleDaoImpl();
//        String property = null;
//        Object value = null;
//        String sortExpression = null;
//        String sortDirection = null;
//
//        //Khoi tao gia tri property can tim
//        property = "roleid";
//        sortExpression = "roleid";
//        sortDirection = "2";
////        value = 2;
//
//        Object[] objects = roleDao.findByProperty(property, value, sortExpression, sortDirection);
//    }
//
//    @Test
//    public void checkDelete() {
//        RoleDao roleDao = new RoleDaoImpl();
//        List<Integer> list = new ArrayList<>();
//        list.add(1);
////        list.add(2);
//        Integer numberDelete = roleDao.delete(list);
//    }
//}
