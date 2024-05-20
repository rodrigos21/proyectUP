package arq.web.tp.integrador.products.service;

import arq.web.tp.integrador.products.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getProducts();

    ProductDTO getById(Long id);

    Long createProduct(ProductDTO productDTO);

    void updateProduct(ProductDTO productDTO);

    void deleteProduct(Long productId);
}
