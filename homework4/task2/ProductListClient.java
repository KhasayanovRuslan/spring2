package magazine1.services;

import magazine1.entites.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("geek-spring-cloud-eureka-product-list")
public interface ProductListClient {
    @RequestMapping("/getProductList")
    List<Product> getProductList();

}
