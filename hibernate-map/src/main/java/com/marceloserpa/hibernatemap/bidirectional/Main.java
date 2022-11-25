package com.marceloserpa.hibernatemap.bidirectional;

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


        BiPost postEntity = new BiPost();
        postEntity.setTitle("My first post");
        postEntity.addComment(new BiPostComment("comment 1"));
        postEntity.addComment(new BiPostComment("comment 2"));
        postEntity.addComment(new BiPostComment("comment 3"));

        session.save(postEntity);

        var posts = session.createQuery("from BiPost", BiPost.class).list();

        for(BiPost post : posts) {
            System.out.println(post);
            System.out.println(post.getComments());
        }

        System.out.println("--------------");

        var comment = session.createQuery("from BiPostComment where id = 1",
            BiPostComment.class).getSingleResult();

        System.out.println(comment);
        System.out.println(comment.getBipost());
        System.out.println("----------------------------------");

        var biPost = posts.get(0);
        var firstComment = biPost.getComments().get(0);
        biPost.removeComment(firstComment);

        posts = session.createQuery("from BiPost", BiPost.class).list();

        for(BiPost post : posts) {
            System.out.println(post);
            System.out.println(post.getComments());
        }

        session.getTransaction().commit();
        sessionFactory.close();
    }
}