package com.nhnacademy.springjpa.entity;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.springjpa.config.RootConfig;
import com.nhnacademy.springjpa.config.WebConfig;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
    @ContextConfiguration(classes = RootConfig.class),
    @ContextConfiguration(classes = WebConfig.class)
})
public class EntityManagerTest {
    @PersistenceContext
    EntityManager entityManager;

    @Test
    void test1() {
        User user1 = new User();
        user1.setId("newUser");
        user1.setPassword("abcde");

        entityManager.persist(user1);

        User user2 = entityManager.find(User.class, "newUser");
        assertThat(user2).isEqualTo(user1);
    }

    @Test
    void test2() {
        User user1 = new User();
        user1.setId("newUser");
        user1.setPassword("abcde");

        entityManager.persist(user1);
        entityManager.flush();          // <--

        User user2 = entityManager.find(User.class, "newUser");
        assertThat(user2).isEqualTo(user1);
    }

    @Test
    void test3() {
        User user1 = new User();
        user1.setId("newUser");
        user1.setPassword("abcde");

        entityManager.persist(user1);

        User user2 = entityManager.find(User.class, "newUser");
        assertThat(user2).isEqualTo(user1);

        user2.setPassword("fghij");
        entityManager.flush();
    }

    @Test
    void test4() {
        User user1 = entityManager.find(User.class, "admin");
        User user2 = entityManager.find(User.class, "admin");
        User user3 = entityManager.find(User.class, "admin");
        User user4 = entityManager.find(User.class, "admin");
        User user5 = entityManager.find(User.class, "admin");

        assertThat(user1).isEqualTo(user2);
        assertThat(user1).isEqualTo(user3);
        assertThat(user1).isEqualTo(user4);
        assertThat(user1).isEqualTo(user5);

    }


}
