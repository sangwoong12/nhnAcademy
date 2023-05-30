package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.Item;
import com.nhnacademy.springjpa.entity.QItem;
import com.nhnacademy.springjpa.entity.QOrder;
import com.nhnacademy.springjpa.entity.QOrderItem;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class ItemRepositoryImpl extends QuerydslRepositorySupport implements ItemRepositoryCustom {
    public ItemRepositoryImpl() {
        super(Item.class);
    }

    @Override
    public List<Item> getItemsAfterOrderDate(LocalDateTime orderDate) {
        QItem item = QItem.item;
        QOrderItem orderItem = QOrderItem.orderItem;
        QOrder order = QOrder.order;

        Date baseDate = Date.from(orderDate.toInstant(ZoneOffset.of("+09:00")));

        return from(item)
            .leftJoin(item.orderItems, orderItem)
            .innerJoin(orderItem.order, order)
            .where(order.orderDate.after(baseDate))
            .select(item)
            .fetch();
    }

}
