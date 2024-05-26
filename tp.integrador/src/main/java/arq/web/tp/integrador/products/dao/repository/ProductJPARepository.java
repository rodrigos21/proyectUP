package arq.web.tp.integrador.products.dao.repository;

import arq.web.tp.integrador.exceptions.CustomException;
import arq.web.tp.integrador.products.entity.ProductEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductJPARepository extends JpaRepository<ProductEntity,Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM PRODUCT")
    List<ProductEntity> getProducts() throws CustomException;

    @Query(nativeQuery = true, value = "SELECT * FROM PRODUCT WHERE ID_PRODUCT =:productId")
    ProductEntity getProductById(@Param("productId") Long productId) throws CustomException;
}
