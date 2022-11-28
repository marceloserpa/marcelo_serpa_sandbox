package com.marceloserpa.hibernatepoc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {

    public static void main(String[] args) {

        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        Metadata metadata = new MetadataSources(standardServiceRegistry)
                .getMetadataBuilder().build();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        session.beginTransaction();


        // Preparing data
        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setDescription("Financial");
        session.save(departmentEntity);

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setDescription("Admin");
        session.save(roleEntity);

        UserEntity userEntity = new UserEntity();
        userEntity.setName("Marcelo");
        session.save(userEntity);

        // Load entities for test
        var department = session.createQuery("from DepartmentEntity", DepartmentEntity.class).getSingleResult();
        var user = session.createQuery("from UserEntity", UserEntity.class).getSingleResult();
        var role = session.createQuery("from RoleEntity", RoleEntity.class).getSingleResult();

        // Link all entities
        UserDepartmentRoleLinkEntity linkEntity = new UserDepartmentRoleLinkEntity();
        linkEntity.setDepartment(department);
        linkEntity.setRole(role);
        linkEntity.setUser(user);
        session.save(linkEntity);

        // Show links :)
        System.out.println("******************* SHOW LINKS ***************************************");
        var links = session.createQuery("from UserDepartmentRoleLinkEntity",
            UserDepartmentRoleLinkEntity.class).list();

        for(UserDepartmentRoleLinkEntity link : links) {
            System.out.println(
                String.format(
                    ">> User: %s \n   Department: %s \n   Role: %s \n\n", link.getUser().getName(), link.getDepartment().getDescription(), link.getRole().getDescription()
                )
            );
        }

        session.getTransaction().commit();
        sessionFactory.close();
    }
}