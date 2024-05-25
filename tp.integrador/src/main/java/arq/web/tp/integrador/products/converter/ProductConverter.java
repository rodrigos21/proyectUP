package arq.web.tp.integrador.products.converter;

import arq.web.tp.integrador.products.dto.ProductDTO;
import arq.web.tp.integrador.products.entity.ProductEntity;

public class ProductConverter {
    private ProductConverter() {

    }

    public static ProductDTO toDTO(ProductEntity entity) {
        ProductDTO dto = new ProductDTO();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setAmount(entity.getAmount());
        return dto;
    }

    public static ProductEntity toEntity(ProductDTO dto){
        ProductEntity entity = new ProductEntity();
        if(dto.getId() != null ){
            entity.setId(dto.getId());
        }
        entity.setDescription(dto.getDescription());
        entity.setAmount(dto.getAmount());
        return entity;
    }
}
