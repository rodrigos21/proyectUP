package arq.web.tp.integrador.orders.entity;

import arq.web.tp.integrador.products.entity.ProductEntity;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "ORDER_PRODUCTS")
public class OrderProductsEntity implements Serializable {

    private static final long serialVersionUID = 2481350986623601245L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_products_seq")
    @SequenceGenerator(name = "order_products_seq", sequenceName = "ORDER_PRODUCTS_SEQ", allocationSize = 1)
    @Column(name = "ID_ORDER_PRODUCT")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_ORDER", nullable = false)
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUCT", nullable = false)
    private ProductEntity product;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

}

