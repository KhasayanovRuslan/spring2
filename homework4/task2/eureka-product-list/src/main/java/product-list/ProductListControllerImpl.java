package product-list;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ProductListControllerImpl implements ProductListController {
    private ProductService productService;

    @Autowired
    @Lazy
    private EurekaClient eurekaClient;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public List<Product> getProductList() {
        return List<Product> productList = productService.getAllProducts();
    }

}
