package arq.web.tp.integrador.orders.dao;

import arq.web.tp.integrador.orders.entity.OrderProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductJPARepository extends JpaRepository<OrderProductsEntity,Long> {

}
