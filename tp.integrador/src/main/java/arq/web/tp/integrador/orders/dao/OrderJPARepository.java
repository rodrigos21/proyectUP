package arq.web.tp.integrador.orders.dao;

import arq.web.tp.integrador.exceptions.CustomException;
import arq.web.tp.integrador.orders.entity.OrderEntity;
import arq.web.tp.integrador.orders.entity.OrderProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderJPARepository extends JpaRepository<OrderEntity, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM ORDER_PRODUCTS")
    List<OrderProductsEntity> getReport() throws CustomException;
}
