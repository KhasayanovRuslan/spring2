package product-list;

import org.springframework.web.bind.annotation.RequestMapping;

public interface ProductListController {
    @RequestMapping("/getProductList")
    List<Product> getProductList();
}