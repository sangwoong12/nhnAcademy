package com.nhnacademy.no_spring_jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default"); // [엔티티 매니저 팩토리] - 생성
        EntityManager em = emf.createEntityManager(); // [엔티티 메니저] - 생성
        EntityTransaction tx = em.getTransaction(); // [트랜잭션] - 획득

        try {
            tx.begin(); // [트랜잭션] - 시작
            // 비지니스 로직 실행
            tx.commit(); // [트랜잭션] - 커밋
        } catch (Exception e) {
            tx.rollback(); // [트랜잭션] - 롤백
        } finally {
            em.close();// [엔티티 매니저] - 종료
        }
        emf.close();// [인티티 메니저 팩토리] - 종료
    }
}
