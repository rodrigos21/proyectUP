package arq.web.tp.integrador.orders.service;

import arq.web.tp.integrador.orders.dao.OrderJPARepository;
import arq.web.tp.integrador.orders.dto.Report;
import arq.web.tp.integrador.orders.entity.OrderProductsEntity;
import arq.web.tp.integrador.products.dto.PurchaseOrder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderService {

    @Autowired
    private OrderJPARepository repository;


    public Long createPurchase(PurchaseOrder order) {
        // TODO Map order and create
        /*repository.save();*/
        return null;
    }

    public Report getReport() {
        List<OrderProductsEntity> list = repository.getReport();
        return null;
    }
}
