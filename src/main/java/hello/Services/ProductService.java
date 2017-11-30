package hello.Services;

import hello.Product.Product;
import hello.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("productService")
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public Iterable<Product> listAllProducts() {return productRepository.findAll(); }

    public Product findProduct(Long productId) { return productRepository.findOne(productId); }
}
