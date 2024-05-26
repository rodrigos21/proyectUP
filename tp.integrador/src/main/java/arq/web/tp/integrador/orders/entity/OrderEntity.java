package arq.web.tp.integrador.orders.entity;


import arq.web.tp.integrador.users.entity.UserEntity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "ORDERS")
public class OrderEntity implements Serializable {

    private static final long serialVersionUID = 2481350986623601245L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_seq")
    @SequenceGenerator(name = "orders_seq", sequenceName = "ORDERS_SEQ", allocationSize = 1)
    @Column(name = "ID_ORDER")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_USER", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProductsEntity> orderProducts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<OrderProductsEntity> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProductsEntity> orderProducts) {
        this.orderProducts = orderProducts;
    }
}
