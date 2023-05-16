package com.nhnacademy.resident.entity;

import com.nhnacademy.resident.config.RootConfig;
import com.nhnacademy.resident.config.WebConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class UserTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void testUserEntity() {
        User user = new User();
        user.setId("tester");
        user.setPassword("12345");
        user.setName("테스터");
        user.setProfileFileName(null);

        entityManager.persist(user);
        entityManager.flush();

        User tester = entityManager.find(User.class, user.getUserId());

        assertThat(tester.getName()).isEqualTo(user.getName());
        assertThat(tester.getUserId()).isEqualTo(user.getUserId());
        assertThat(tester.getPassword()).isEqualTo(user.getPassword());
        assertThat(tester.getProfileFileName()).isEqualTo(user.getProfileFileName());
    }
}