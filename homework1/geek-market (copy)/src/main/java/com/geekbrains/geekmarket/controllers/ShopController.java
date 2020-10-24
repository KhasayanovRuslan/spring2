package com.geekbrains.geekmarket.controllers;

import com.geekbrains.geekmarket.entities.OrderItem;
import com.geekbrains.geekmarket.entities.Product;
import com.geekbrains.geekmarket.repositories.OrderItemRepository;
import com.geekbrains.geekmarket.services.OrderItemService;
import com.geekbrains.geekmarket.services.ProductService;
import com.geekbrains.geekmarket.services.ShoppingCartService;
import com.geekbrains.geekmarket.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopController {
    private OrderItemService orderItemService;

    private ProductService productService;

    private ShoppingCartService shoppingCartService;

    @Autowired
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public String shopPage(Model model) {
        List<Product> allProducts = productService.getAllProducts();
        model.addAttribute("products", allProducts);
        return "shop-page";
    }

    @GetMapping("/ajax")
    public String ajaxShopPage(Model model) {
       // List<Product> allProducts = productService.getAllProducts();
       // model.addAttribute("products", allProducts);
        return "shop-page-ajax";
    }

    @GetMapping("/cart/add/{id}")
    public String addProductToCart(Model model, @PathVariable("id") Long id, HttpServletRequest httpServletRequest) {
        shoppingCartService.addToCart(httpServletRequest.getSession(), id);
        String referrer = httpServletRequest.getHeader("referer");
        return "redirect:" + referrer;
    }

    @GetMapping("/order/fill")
    public String orderPage(Model model, HttpSession httpSession) {
        ShoppingCart cart = shoppingCartService.getCurrentCart(httpSession);
        model.addAttribute("cart", cart);
        return "order-page";
    }

    @PostMapping("/process_order/fill")
    @Transactional
    public String doneOrder(Model model, HttpSession httpSession) {
        ShoppingCart cart = shoppingCartService.getCurrentCart(httpSession);
        List<OrderItem> items = cart.getItems();

        for (OrderItem o : items) {
            this.orderItemService.save(o);
        }

        return "order-confirmation";
    }
}
