package com.nhnacademy.springjpa.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.springjpa.config.RootConfig;
import com.nhnacademy.springjpa.config.WebConfig;
import com.nhnacademy.springjpa.entity.Item;
import com.nhnacademy.springjpa.entity.OrderItem;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.Date;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
public class ItemRepositoryTest {
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void test() {
        List<Item> items = itemRepository.findByItemNameLike("%ng%");
        assertThat(items.size()).isEqualTo(2);

        assertThat(itemRepository.existsByItemNameAndPrice("mango", 350L)).isTrue();

        itemRepository.flush();
    }


    @Test
    void test2() {
        List<Item> items1 = itemRepository.getItemsHavingPriceAtLeast(250L);
        assertThat(items1).hasSize(4);

        List<Item> items2 = itemRepository.getItemsHavingPriceAtLeast2(250L);
        assertThat(items2).hasSize(4);
    }

    @Test
    void test3() {
        Item item1 = itemRepository.findById(1L).get();
        assertThat(item1.getItemName()).isEqualTo("apple");

        int result = itemRepository.updateItemName(1L, "samsung");
        itemRepository.flush();

        Item item2 = itemRepository.findById(1L).get();
        assertThat(item2.getItemName()).isNotEqualTo("samsung");

        entityManager.clear();

        item2 = itemRepository.findById(1L).get();
        assertThat(item2.getItemName()).isEqualTo("samsung");
    }

    @Test
    void test4() {
        List<Item> items = itemRepository.findByOrderItems_QuantityGreaterThan(4);
        assertThat(items).hasSize(1);
    }

    @Test
    void test5() {
        Date date = Date.from(LocalDateTime.of(2018, 8, 23, 10, 30)
            .toInstant(ZoneOffset.of("+09:00")));
        List<Item> items = itemRepository.findByOrderItems_Order_OrderDateAfter(date);
        assertThat(items).hasSize(6);
    }

    @Test
    void test6() {
        List<Item> items = itemRepository.getItemsAfterOrderDate(
            LocalDateTime.of(2018, 8, 23, 10, 30, 0));

        assertThat(items).hasSize(6);
    }

    @Test
    void test7() {
        itemRepository.findById(1L);
    }

    @Test
    void test8() {
        itemRepository.findAll();
    }

    @Test
    void test9() {
        IntSummaryStatistics statistics = itemRepository.findAll()
            .stream()
            .map(Item::getOrderItems)
            .flatMap(Collection::stream)
            .collect(Collectors.summarizingInt(OrderItem::getQuantity));

        assertThat(statistics.getSum()).isEqualTo(17);
    }

    @Test
    void test10() {
        itemRepository.getAllItemsWithAssociations();
    }

    @Test
    void test11() {
        itemRepository.readAllBy();
    }

    @Test
    void test12() {
        itemRepository.queryAllBy();
    }


}
