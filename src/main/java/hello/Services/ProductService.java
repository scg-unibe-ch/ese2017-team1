package hello.Services;

import hello.Product.Product;
import hello.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service responsible for handling requests concerning products
 */
@Service("productService")
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    /**
     * @return Iterable of all Products in productRepository
     */
    public Iterable<Product> listAllProducts() {return productRepository.findAll(); }

    /**
     * returns product with Id productId
     */
    public Product findProduct(Long productId) { return productRepository.findOne(productId); }
}
