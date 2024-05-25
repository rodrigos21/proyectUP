package arq.web.tp.integrador.products.controller;

import arq.web.tp.integrador.products.dao.repository.ProductJPARepository;
import arq.web.tp.integrador.products.dto.ProductDTO;
import arq.web.tp.integrador.products.dto.PurchaseOrder;
import arq.web.tp.integrador.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService,
                             ProductJPARepository productJPARepository) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDTO> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createProduct(@RequestBody ProductDTO productDTO) {
        return productService.createProduct(productDTO);
    }

    @PutMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@PathVariable(value = "productId") Long productId,
                              @RequestBody ProductDTO productDTO) {
        productService.updateProduct(productDTO);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable(value = "productId") Long productId) {
        productService.deleteProduct(productId);
    }
}
