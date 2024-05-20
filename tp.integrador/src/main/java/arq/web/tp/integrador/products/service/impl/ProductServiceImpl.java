package arq.web.tp.integrador.products.service.impl;


import arq.web.tp.integrador.products.converter.ProductConverter;
import arq.web.tp.integrador.products.dao.ProductDAO;
import arq.web.tp.integrador.products.dao.repository.ProductJPARepository;
import arq.web.tp.integrador.products.dto.ProductDTO;
import arq.web.tp.integrador.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductDAO productDAO;
    private ProductJPARepository productJPARepository;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO,
                              ProductJPARepository productJPARepository) {
        this.productDAO = productDAO;
        this.productJPARepository = productJPARepository;
    }

    @Override
    public List<ProductDTO> getProducts() {
        return productJPARepository.getProducts().stream().map(p -> ProductConverter.toDTO(p)).toList();
    }

    @Override
    public ProductDTO getById(Long id) {
        return ProductConverter.toDTO(productJPARepository.getReferenceById(id));
    }

    @Override
    public Long createProduct(ProductDTO productDTO) {

        var r = productJPARepository.save(ProductConverter.toEntity(productDTO));
        return r.getIdProduct();
    }

    @Override
    public void updateProduct(ProductDTO productDTO) {
        productJPARepository.save(ProductConverter.toEntity(productDTO));
    }

    @Override
    public void deleteProduct(Long productId) {
        productJPARepository.deleteById(productId);
    }
}
