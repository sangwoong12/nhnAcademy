package com.nhnacademy.springjpa.entity;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.springjpa.config.RootConfig;
import com.nhnacademy.springjpa.config.WebConfig;
import com.nhnacademy.springjpa.repository.ItemRepository;
import com.nhnacademy.springjpa.repository.OrderItemRepository;
import com.nhnacademy.springjpa.repository.OrderRepository;
import java.util.Arrays;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public class OrderItemTest {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Test
    void test() {
        Item item1 = new Item();
        item1.setItemId(101L);
        item1.setItemName("egg");
        item1.setPrice(1000L);

        itemRepository.save(item1);
        // itemRepository.saveAndFlush(item1);

        Item item2 = new Item();
        item2.setItemId(102L);
        item2.setItemName("milk");
        item2.setPrice(2500L);

        itemRepository.save(item2);

        Order order1 = new Order();
        order1.setOrderId(3001L);
        order1.setOrderDate(new Date());

        orderRepository.save(order1);

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setPk(new OrderItem.Pk(order1.getOrderId(), 1));
        orderItem1.setItem(item1);
        orderItem1.setQuantity(10);
        orderItem1.setOrder(order1);

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setPk(new OrderItem.Pk(order1.getOrderId(), 2));
        orderItem2.setItem(item2);
        orderItem2.setQuantity(3);
        orderItem2.setOrder(order1);

        orderItemRepository.saveAll(Arrays.asList(orderItem1, orderItem2));

        assertThat(orderItem2.getOrder().getOrderId()).isEqualTo(order1.getOrderId());

        orderItemRepository.flush();
    }

}
