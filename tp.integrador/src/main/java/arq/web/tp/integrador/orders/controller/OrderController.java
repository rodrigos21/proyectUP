package arq.web.tp.integrador.orders.controller;

import arq.web.tp.integrador.orders.dto.Report;
import arq.web.tp.integrador.orders.service.OrderService;
import arq.web.tp.integrador.products.dto.PurchaseOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/purchase")
    @ResponseStatus(HttpStatus.CREATED)
    public Long createPurchase(@RequestBody PurchaseOrder order) {
        return orderService.createPurchase(order);
    }

    @GetMapping("/report")
    public Report getReport() {
        return orderService.getReport();
    }
}
