package com.nhnacademy.nhnmart.servlet;

import com.nhnacademy.nhnmart.item.Baskets;
import com.nhnacademy.nhnmart.item.Food;
import com.nhnacademy.nhnmart.item.FoodStand;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(
        name = "initServlet",
        urlPatterns = "/init"
)
@Slf4j
public class InitServlet extends HttpServlet {
    static boolean init = false;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        Baskets baskets = new Baskets();
        config.getServletContext().setAttribute("baskets",baskets);
        log.error("init InitServlet");
        init = true;
    }

    //호출 할때마다 foodstand 초기화
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext sc = getServletContext();
        FoodStand foodStand = FoodStand.getFoodStand();
        FoodStand.setInit();
        System.out.println(sc.getAttribute("apple"));
        //상품이 추가되어도 로직이 변화없도록 구현
        Enumeration<String> foodNames = sc.getAttributeNames();
        System.out.println(foodNames);
        while (foodNames.hasMoreElements()){
            String foodName = foodNames.nextElement();
            System.out.println("foodName" +foodName);
            String str = sc.getInitParameter(foodName);

            String[] split = str.split(",");

            int amount = Integer.parseInt(split[1]);
            int price = Integer.parseInt(split[0]);
            Food food = new Food(foodName, amount);

            foodStand.add(food,price);

            sc.setAttribute(foodName,food);
        }
        //foodStand, food는 servlet context 단계에 있어도 된다.
        sc.setAttribute("foodStand",foodStand);
        RequestDispatcher rd = req.getRequestDispatcher("/initHome.jsp");
        rd.forward(req,resp);
//        resp.sendRedirect("/initHome.jsp");
    }
}
