package arq.web.tp.integrador.orders.dao;

import arq.web.tp.integrador.orders.entity.OrderProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductJPARepository extends JpaRepository<OrderProductsEntity, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM ORDER_PRODUCTS WHERE ID_ORDER = :orderId")
    List<OrderProductsEntity> findByOrderId(@Param(value = "orderId") Long orderId);
}
