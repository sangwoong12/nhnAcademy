package com.nhnacademy.springjpa.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NamedEntityGraphs({
    @NamedEntityGraph(name = "itemWithOrderItems", attributeNodes = {
        @NamedAttributeNode("orderItems")
    }),
    @NamedEntityGraph(name = "itemWithOrderItemsAndOrder", attributeNodes = {
        @NamedAttributeNode(value = "orderItems", subgraph = "orderItems")
    }, subgraphs = @NamedSubgraph(name = "orderItems", attributeNodes = {
        @NamedAttributeNode("order")
    }))
})
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Items")
public class Item {
    @Id
    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "item_name")
    private String itemName;

    private Long price;

    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItems;

}
