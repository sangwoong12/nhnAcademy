package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.Item;
import java.util.List;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface OrderRepositoryCustom {
    List<Item> getItemsHavingOrderItemQuantityGreaterThan(int quantity);

}
