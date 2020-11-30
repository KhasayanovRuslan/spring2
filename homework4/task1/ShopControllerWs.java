package com.geekbrains.geekmarketwinter.controllers;

import com.geekbrains.geekmarketwinter.entites.Greeting;
import com.geekbrains.geekmarketwinter.entites.Message;
import com.geekbrains.geekmarketwinter.entites.OrderItem;
import com.geekbrains.geekmarketwinter.services.ShoppingCartService;
import com.geekbrains.geekmarketwinter.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ShopControllerWs {
    private ShoppingCartService shoppingCartService;

    @Autowired
    public void setShoppingCartService(ShoppingCartService shoppingCartSrvice) {
        this.shoppingCartService = shoppingCartService;
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(Message message, HttpSession session) throws Exception {
        int count = 0;
        ShoppingCart cart = shoppingCartService.getCurrentCart(session);
        List<OrderItem> items = cart.getItems();
        for(OrderItem item: items) {
            count++;
        }
        Thread.sleep(3000); // simulated delay
        return new Greeting(message.getName() + " добавлен в корзину!" + "В корзине " + count + "товаров.");
    }

//    @MessageMapping("/hello")
//    @SendTo("/topic/greetings")
//    public Greeting greeting(Message message) throws Exception {
//        Thread.sleep(3000); // simulated delay
//        return new Greeting(message.getName() + " добавлен в корзину!");
//    }
}
