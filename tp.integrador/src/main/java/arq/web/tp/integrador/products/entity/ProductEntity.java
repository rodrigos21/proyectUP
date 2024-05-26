package arq.web.tp.integrador.products.entity;

import arq.web.tp.integrador.orders.entity.OrderProductsEntity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "PRODUCT")
public class ProductEntity implements Serializable {

    private static final long serialVersionUID = 2481350986623601245L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUCT", nullable = false, precision = 12)
    private Long id;
    @Column(name = "DESCRIPTION", nullable = false, length = 255)
    private String description;
    @Column(name = "AMOUNT", nullable = false)
    private Double amount;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderProductsEntity> orderProducts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public List<OrderProductsEntity> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProductsEntity> orderProducts) {
        this.orderProducts = orderProducts;
    }
}
