package com.nhnacademy.servlet;

import com.nhnacademy.nhnmart.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class CartServlet extends HttpServlet {
    /**
     * 로그인을 하지않았거나 init 을 실행하지 않았다면 진행할 수 없고, 살물건을 form으로 받아 buylist 를 만들고 foodStand 에서 물품을 빼고 basket에 추가하고 attribute 한다..
     * 만약 구매할려는 갯수가 더 많다면 다시 foodStand 에 넣고 예외처리를 통해 홈화면으로 돌려보낸다.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        FoodStand foodStand = (FoodStand) getServletContext().getAttribute("foodStand");
        if (Objects.isNull(req.getSession(false))) {
            log.info("로그인 필요");
            resp.sendRedirect("/login");
        } else if (Objects.isNull(foodStand)) { //null 일경우 init 과정을 생략하였기 때문에 강제로 init 으로 보낸다.
            resp.sendRedirect("/init");
        } else {
            //구매 목록을 만들고
            BuyList buyList = new BuyList();
            Enumeration<String> foodNames = getServletContext().getInitParameterNames();
            while (foodNames.hasMoreElements()) {
                String foodName = foodNames.nextElement();
                int amount = Integer.valueOf(req.getParameter(foodName));
                buyList.add(new BuyList.Item(foodName,amount));
            }
            //Basket 담기 이과정에서 검증 필요.
            Basket basket = new Basket();

            try{//Map<Food,Integer> 로 하면 간단하게 처리할 수 있지만 기본스펙이 ArrayList
                for (BuyList.Item item : buyList.getItems()) {
                    for(int i = 0; i < item.getAmount(); i++){
                        Food food = foodStand.getFood(item.getName());
                        basket.add(food);
                    }
                }
            } catch (NotFoundFoodException e){
                //장바구니에 담는 것을 실패하여 다시 상품매대에 넣는다.
                for (Food food : basket.getFoods()) {
                    foodStand.add(food);
                }
                log.info("물품 수량보다 구매 수량이 더 많습니다.");
                resp.sendRedirect("/");
            }
            log.info("검증 완료");

            getServletContext().setAttribute("basket",basket);

            try(PrintWriter out = resp.getWriter()){
                postHtml(out);
            }catch (IOException e){
                log.error("/cart : {}",e.getMessage(),e);
            }
        }
    }

    /**
     * 이 역시 로그인,init을 하지 않으면 넘어갈 수 없고 통과할 경우 장바구니 정보를 출력한다.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Basket basket = (Basket) getServletContext().getAttribute("basket");

        if(Objects.isNull(req.getSession(false))){
            log.info("로그인 필요");
            resp.sendRedirect("/login");
        } else if (Objects.isNull(basket)) { //null 일경우 cart post 과정을 생략하였기 때문에 강제로 init 으로 보낸다.
            resp.sendRedirect("/init");
        } else {
            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");
            //같은 물품 별로 <이름,수량>
            Map<String, Integer> map = basket.getFoods().stream()
                    .collect(Collectors.toMap(Food::getName, f -> 1, Integer::sum));

            try(PrintWriter out = resp.getWriter()){
                getHtml(map, out);
            }catch (IOException e){
                log.info("/cart {}",e.getMessage());
            }
        }
    }

    private void getHtml(Map<String, Integer> map, PrintWriter out) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='utf-8'>");
        out.println("<link rel='stylesheet' href='nhnmart.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div>");
        out.println("<ui>");
        Enumeration<String> foodNames = getServletContext().getInitParameterNames();
        int totalPrice = 0;
        while(foodNames.hasMoreElements()){
            Food food = (Food) getServletContext().getAttribute(foodNames.nextElement());
            int price = food.getPrice() * map.get(food.getName());
            totalPrice+= price;
            out.println("<li> 물품이름 :"+food.getName() + "수량 :"+ map.get(food.getName()) + " 가격 :"+ price +"</li></br>");
        }
        out.println("<li> 총 가격 :"+totalPrice+"</li>");
        out.println("</ui>");
        out.println("</div>");
        out.println("<div>");
        out.println("<a href='/'>홈으로</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    private static void postHtml(PrintWriter out) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='utf-8'>");
        out.println("<link rel='stylesheet' href='nhnmart.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div>");
        out.println("<a href='/cart'>장바구니 화면</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

}
