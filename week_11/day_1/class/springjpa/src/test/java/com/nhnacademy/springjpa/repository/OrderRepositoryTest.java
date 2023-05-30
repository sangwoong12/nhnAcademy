package com.nhnacademy.springjpa.repository;


import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.springjpa.config.RootConfig;
import com.nhnacademy.springjpa.config.WebConfig;
import com.nhnacademy.springjpa.entity.Item;
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
public class OrderRepositoryTest {
    @Autowired
    OrderRepository orderRepository;

    @Test
    void test() {
        List<Item> items1 = ReflectionTestUtils.invokeMethod(orderRepository, "getItemsHavingOrderItemQuantityGreaterThan", 4);
        assertThat(items1).hasSize(1);

        List<Item> items2 = ReflectionTestUtils.invokeMethod(orderRepository, "getItemsHavingOrderItemQuantityGreaterThan", 1);
        assertThat(items2).hasSize(4);
    }
}
