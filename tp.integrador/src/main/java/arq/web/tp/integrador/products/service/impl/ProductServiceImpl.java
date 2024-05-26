package arq.web.tp.integrador.products.service.impl;


import arq.web.tp.integrador.exceptions.CustomException;
import arq.web.tp.integrador.products.converter.ProductConverter;
import arq.web.tp.integrador.products.dao.repository.ProductJPARepository;
import arq.web.tp.integrador.products.dto.ProductDTO;
import arq.web.tp.integrador.products.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    private ProductJPARepository productJPARepository;

    @Autowired
    public ProductServiceImpl(ProductJPARepository productJPARepository) {
        this.productJPARepository = productJPARepository;
    }

    @Override
    public List<ProductDTO> getProducts() {
        try {
            return productJPARepository.getProducts().stream().map(p -> ProductConverter.toDTO(p)).toList();
        } catch (Exception e) {
            throw new CustomException("Products not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ProductDTO getById(Long id) {
        var p = productJPARepository.getProductById(id);
        if (ObjectUtils.isEmpty(p)) {
            throw new CustomException("Product not found", HttpStatus.NOT_FOUND);
        }
        return ProductConverter.toDTO(p);
    }

    @Override
    @Transactional
    public Long createProduct(ProductDTO productDTO) {
        try {
            var r = productJPARepository.save(ProductConverter.toEntity(productDTO));
            return r.getId();
        } catch (Exception e) {
            throw new CustomException("Error creating product", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional
    public void updateProduct(Long productId, ProductDTO productDTO) {
        var p = productJPARepository.findById(productId);
        if (p == null || p.isEmpty()) {
            throw new CustomException("Product not found", HttpStatus.NOT_FOUND);
        }
        productJPARepository.save(ProductConverter.toEntity(productDTO));
    }

    @Override
    public void deleteProduct(Long productId) {
        var p = productJPARepository.findById(productId);
        if (p == null || p.isEmpty()) {
            throw new CustomException("Product not found", HttpStatus.NOT_FOUND);
        }
        productJPARepository.deleteById(productId);
    }
}
