package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.annotation.Question;
import com.nhnacademy.springjpa.domain.ItemDto;
import com.nhnacademy.springjpa.entity.Item;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryCustom {
    // select * from Items where item_name like '{itemName}'
    List<Item> findByItemNameLike(String itemName);

    // select item_id from Items
    // where item_name = '{itemName}'
    // and price = {price} limit 1
    boolean existsByItemNameAndPrice(String itemName, Long price);

    // select count(*) from Items where item_name like '%{itemName}%'
    int countByItemNameLike(String itemName);

    // delete from Items where price between {price1} and {price2}
    void deleteByPriceBetween(long price1, long price2);

    @Question
    List<Item> findByPriceIn(Collection<Long> prices);

    @Query("select i from Item i where i.price > ?1")
    List<Item> getItemsHavingPriceAtLeast(long price);

    @Query(value = "select * from Items where price > ?1", nativeQuery = true)
    List<Item> getItemsHavingPriceAtLeast2(long price);

    @Modifying
    @Query("update Item i set i.itemName = :itemName where i.itemId = :itemId")
    int updateItemName(@Param("itemId") Long itemId, @Param("itemName")String itemName);

    List<Item> findByOrderItems_QuantityGreaterThan(Integer quantity);

    List<Item> findByOrderItems_Order_OrderDateAfter(Date orderDate);

    ItemDto findByItemId(Long itemId);

    Page<ItemDto> getAllBy(Pageable pageable);

    @Query("select i "
        + "from Item i "
        + "left outer join fetch i.orderItems oi "
        + "inner join fetch oi.order o")
    List<Item> getAllItemsWithAssociations();

    @EntityGraph("itemWithOrderItems")
    List<Item> readAllBy();

    @EntityGraph("itemWithOrderItemsAndOrder")
    List<Item> queryAllBy();

}
