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
            throw new CustomException("Error al obtener productos", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ProductDTO getById(Long id) {
        try {
            return ProductConverter.toDTO(productJPARepository.getReferenceById(id));
        } catch (Exception e) {
            throw new CustomException("Error al obtener producto", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional
    public Long createProduct(ProductDTO productDTO) {
        try {
            var r = productJPARepository.save(ProductConverter.toEntity(productDTO));
            return r.getId();
        } catch (Exception e) {
            throw new CustomException("Error al crear", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional
    public void updateProduct(ProductDTO productDTO) {
        try {
            productJPARepository.save(ProductConverter.toEntity(productDTO));
        } catch (Exception e) {
            throw new CustomException("Error al actualizar", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void deleteProduct(Long productId) {
        try {
            productJPARepository.deleteById(productId);
        } catch (Exception e) {
            throw new CustomException("Error al eliminar", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
