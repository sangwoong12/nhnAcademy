package com.nhnacademy.nhnmart.item;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    Map<String,User> userMap = new HashMap<>();
    public User getuser(String id){
        return userMap.get(id);
    }
    public boolean exist(String id){
        return userMap.containsKey(id);
    }
    public void add(User user){
        userMap.put(user.getId(),user);
    }
}
