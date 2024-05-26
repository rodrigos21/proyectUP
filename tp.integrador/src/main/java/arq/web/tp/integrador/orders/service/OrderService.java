package arq.web.tp.integrador.orders.service;

import arq.web.tp.integrador.exceptions.CustomException;
import arq.web.tp.integrador.orders.dao.OrderJPARepository;
import arq.web.tp.integrador.orders.dao.OrderProductJPARepository;
import arq.web.tp.integrador.orders.dto.Report;
import arq.web.tp.integrador.orders.entity.OrderEntity;
import arq.web.tp.integrador.orders.entity.OrderProductsEntity;
import arq.web.tp.integrador.products.dao.repository.ProductJPARepository;
import arq.web.tp.integrador.products.dto.OrderReport;
import arq.web.tp.integrador.products.dto.ProductDTO;
import arq.web.tp.integrador.products.dto.PurchaseOrder;
import arq.web.tp.integrador.users.dao.repository.UserJPARepository;
import arq.web.tp.integrador.users.dto.UserDTO;
import arq.web.tp.integrador.users.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private OrderJPARepository orderJPARepository;
    private OrderProductJPARepository orderProductJPARepository;
    private ProductJPARepository productJPARepository;
    private UserJPARepository userJPARepository;

    @Autowired
    public OrderService(OrderJPARepository orderJPARepository,
                        OrderProductJPARepository orderProductJPARepository,
                        ProductJPARepository productJPARepository,
                        UserJPARepository userJPARepository) {
        this.orderJPARepository = orderJPARepository;
        this.orderProductJPARepository = orderProductJPARepository;
        this.productJPARepository = productJPARepository;
        this.userJPARepository = userJPARepository;
    }

    @Transactional
    public Long createPurchase(PurchaseOrder order) {
        OrderEntity orderEntity = new OrderEntity();
        OrderProductsEntity orderProductsEntity = new OrderProductsEntity();
        UserEntity userEntity = new UserEntity();
        List<OrderProductsEntity> orderProductsEntityList = new ArrayList<>();

        userEntity = userJPARepository.getUserById(order.getUser().getId());
        if (userEntity == null) {
            throw new CustomException("User not found", HttpStatus.NOT_FOUND);
        }

        orderEntity.setUser(userEntity);
        var savedOrder = orderJPARepository.save(orderEntity);

        order.getProducts().forEach(e -> {
            var productJPA = productJPARepository.getProductById(e.longValue());
            if (productJPA == null) {
                throw new CustomException("User not found", HttpStatus.NOT_FOUND);
            }
            OrderProductsEntity orderProducts = new OrderProductsEntity();
            orderProducts.setOrder(savedOrder);
            orderProducts.setProduct(productJPA);
            orderProductJPARepository.save(orderProducts);
        });
        return null;
    }

    @Transactional
    public Report getReport() {
        List<OrderEntity> orderEntityList = orderJPARepository.getOrderEntity();
        Report report = new Report();
        List<OrderReport> orderReportList = new ArrayList<>();

        for (OrderEntity e : orderEntityList) {
            OrderReport orderReport = new OrderReport();
            List<OrderProductsEntity> orderProductsEntityList = orderProductJPARepository.findByOrderId(e.getId());
            List<ProductDTO> productDTOList = new ArrayList<>();

            orderProductsEntityList.forEach(p -> {
                productDTOList.add(getAndMapProductsReports(p));
            });

            orderReport.setId(e.getId());
            orderReport.setUser(getAndMapUserDTO(e.getUser().getId()));
            orderReport.setProducts(productDTOList);
            orderReportList.add(orderReport);
        }
        report.setReport(orderReportList);
        return report;
    }

    private UserDTO getAndMapUserDTO(Long userId) {
        UserEntity userEntity = userJPARepository.getUserById(userId);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setEmail(userEntity.getEmail());
        return userDTO;
    }

    private ProductDTO getAndMapProductsReports(OrderProductsEntity p) {
        ProductDTO productDTO = new ProductDTO();
        var productsEntity = productJPARepository.findById(p.getProduct().getId());
        if (productsEntity.isPresent()) {
            var product = productsEntity.get();
            productDTO.setId(productsEntity.orElseThrow().getId());
            productDTO.setDescription(productsEntity.orElseThrow().getDescription());
            productDTO.setAmount(productsEntity.orElseThrow().getAmount());
        }
        return productDTO;
    }
}
