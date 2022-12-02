package com.marceloserpa.hibernate6;

public class Main {

    public static void main(String[] args) {
        Person personSaved = null;
        try(var session = ConnectionFactory.createSession()){
            session.beginTransaction();

            Person person = new Person();
            person.setName("Marcelo");
            person.setLastname("Serpa");

            session.save(person);
            personSaved = person;

            session.getTransaction().commit();
        }

        try(var session = ConnectionFactory.createSession()) {
            session.beginTransaction();
            session.delete(personSaved);
            session.getTransaction().commit();
        }







    }
}