package com.nhnacademy.nhnmart.item;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class User {
    String id;
    String password;
    int money;
    public void setMoney(int money) {
        this.money = money;
    }
}
