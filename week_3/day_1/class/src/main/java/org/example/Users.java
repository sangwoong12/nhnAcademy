package org.example;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Users implements Iterable<User>{
    List<User> userList = new ArrayList<>();

    public void addUser(User user){
        userList.add(user);
    }
    @Override
    public Iterator<User> iterator() {
        return this.userList.iterator();
    }

    @Override
    public Spliterator<User> spliterator() {
        return this.userList.spliterator();
    }

    @Override
    public void forEach(Consumer<? super User> action) {
        this.userList.forEach(action);
    }
}
