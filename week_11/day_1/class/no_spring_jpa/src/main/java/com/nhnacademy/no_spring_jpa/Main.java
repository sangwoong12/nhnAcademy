package com.nhnacademy.no_spring_jpa;

import com.nhnacademy.no_spring_jpa.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        User user = new User();
        user.setName("홍길동");

        EntityManagerFactory enf = Persistence.createEntityManagerFactory("default");

        EntityManager entityManager = enf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();

        entityManager.close();
    }
}
