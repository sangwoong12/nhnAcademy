package com.nhnacademy.nhnmart.controller;

import com.nhnacademy.nhnmart.init.RequestMapping;
import com.nhnacademy.nhnmart.item.BuyList;
import com.nhnacademy.nhnmart.item.BuyListRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(value = "/cart.do", method = RequestMapping.Method.GET)
@Slf4j
public class CartFormController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        BuyListRepository buyListRepository = (BuyListRepository) req.getServletContext().getAttribute("buyListRepository");
        String id = (String) req.getSession().getAttribute("id");

        BuyList buyList = buyListRepository.getById(id);

        req.setAttribute("buyList",buyList.getBuyList());
        log.info("구매리스트:"+buyList.getBuyList().toString());
        req.setAttribute("totalPrice",buyList.getTotalPrice());
        log.info("총가격:"+buyList.getTotalPrice());
        return "cartForm.jsp";
    }
}
