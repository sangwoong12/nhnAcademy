package com.nhnacademy.springjpa.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.springjpa.annotation.Question;
import com.nhnacademy.springjpa.config.RootConfig;
import com.nhnacademy.springjpa.config.WebConfig;
import com.nhnacademy.springjpa.entity.Item;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
    @ContextConfiguration(classes = RootConfig.class),
    @ContextConfiguration(classes = WebConfig.class)
})
public class ItemRepositoryTest2 {
    @Autowired
    private ItemRepository itemRepository;

    @Test
    void test() {
        Class<?> clazz = Arrays.stream(itemRepository.getClass().getInterfaces())
            .filter(iface -> iface.isAssignableFrom(ItemRepository.class))
            .findFirst()
            .orElse(null);

        assertThat(clazz).isNotNull();

        Method questionedMethod = Arrays.stream(clazz.getDeclaredMethods())
            .filter(method -> method.isAnnotationPresent(Question.class))
            .findFirst()
            .orElse(null);

        assertThat(questionedMethod).isNotNull();

        List<Item> items = ReflectionTestUtils.invokeMethod(itemRepository, questionedMethod.getName(),
            Arrays.asList(100L, 200L));

        assertThat(items).isNotEmpty()
                         .hasSize(3);
    }

}
