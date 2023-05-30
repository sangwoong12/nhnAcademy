package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.Item;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ItemRepositoryCustom {
    List<Item> getItemsAfterOrderDate(LocalDateTime orderDate);

}
