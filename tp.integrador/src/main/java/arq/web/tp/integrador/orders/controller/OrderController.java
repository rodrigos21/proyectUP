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

    @Autowired
    private OrderService orderService;


    @PostMapping("/purchase")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Long createPurchase(PurchaseOrder order){
        return orderService.createPurchase(order);
    }

    @GetMapping
    public Report getReport(){
        return orderService.getReport();
    }
}
