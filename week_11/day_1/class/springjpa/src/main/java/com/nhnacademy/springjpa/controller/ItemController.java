package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.domain.ItemDto;
import com.nhnacademy.springjpa.entity.Item;
import com.nhnacademy.springjpa.repository.ItemRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {
    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/v1/items/{itemId}")
    public ItemDto getItem1(@PathVariable("itemId") Long itemId) {
        return itemRepository.findByItemId(itemId);
    }

    @GetMapping("/v2/items/{itemId}")
    public ItemDto getItem2(@PathVariable("itemId") Item item) {
        return new ItemDto() {
            @Override
            public String getName() {
                return item.getItemName();
            }

            @Override
            public Long getPrice() {
                return item.getPrice();
            }
        };
    }

    @GetMapping("/items")
    public List<ItemDto> getItems(Pageable pageable) {
        return itemRepository.getAllBy(pageable).getContent();
    }

}
