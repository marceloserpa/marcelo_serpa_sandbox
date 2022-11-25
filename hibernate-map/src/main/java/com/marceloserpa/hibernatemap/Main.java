package com.marceloserpa.hibernatemap;

import com.marceloserpa.hibernatemap.unidirectional.Post;
import com.marceloserpa.hibernatemap.unidirectional.PostComment;
import java.util.List;
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


        Post postEntity = new Post();
        postEntity.setTitle("My first post");
        postEntity.setComments(List.of(
            new PostComment("comment 1"),
            new PostComment("comment 2"),
            new PostComment("comment 3")
        ));

        session.save(postEntity);

        var posts = session.createQuery("from Post", Post.class).list();

        for(Post post : posts) {
            System.out.println(post);
            System.out.println(post.getComments());
        }

        session.getTransaction().commit();
        sessionFactory.close();
    }
}