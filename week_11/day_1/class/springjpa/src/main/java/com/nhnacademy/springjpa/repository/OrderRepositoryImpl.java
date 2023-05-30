package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.Item;
import com.nhnacademy.springjpa.entity.Order;
import com.nhnacademy.springjpa.entity.QItem;
import com.nhnacademy.springjpa.entity.QOrder;
import com.nhnacademy.springjpa.entity.QOrderItem;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class OrderRepositoryImpl extends QuerydslRepositorySupport implements OrderRepositoryCustom {
    public OrderRepositoryImpl() {
        super(Order.class);
    }

    @Override
    public List<Item> getItemsHavingOrderItemQuantityGreaterThan(int quantity) {
        QOrder order = QOrder.order;
        QOrderItem orderItem = QOrderItem.orderItem;
        QItem item = QItem.item;

        return from(orderItem)
                .innerJoin(orderItem.order, order)
                .innerJoin(orderItem.item, item)
                .where(orderItem.quantity.gt(quantity))
                .select(item)
                .distinct()
                .fetch();
    }
}
