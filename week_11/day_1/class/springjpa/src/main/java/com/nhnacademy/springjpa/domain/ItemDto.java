package com.nhnacademy.springjpa.domain;

import org.springframework.beans.factory.annotation.Value;

public interface ItemDto {
    @Value("#{target.itemName}")
    String getName();

    Long getPrice();

}
