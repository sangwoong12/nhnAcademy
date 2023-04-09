package com.nhnacademy.nhnmart.listener;

import com.nhnacademy.nhnmart.item.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class WebApplicationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        FoodStand foodStand = new FoodStand();
        Food onion = new Food("Onion",1000);
        foodStand.add(onion,2);
        Food eggs = new Food("eggs",2000);
        foodStand.add(eggs,5);
        Food greenOnion = new Food("greenOnion", 500);
        foodStand.add(greenOnion,10);
        Food apple = new Food("apple", 2000);
        foodStand.add(apple,20);
        context.setAttribute("foodStand",foodStand);

        BuyListRepository buyListRepository =new BuyListRepository();
        context.setAttribute("buyListRepository",buyListRepository);

        UserRepository userRepository = new UserRepository();
        userRepository.add(new User("admin","1234",0));
        userRepository.add(new User("user1","5678",0));
        userRepository.add(new User("user2","5678",0));

    }
}
