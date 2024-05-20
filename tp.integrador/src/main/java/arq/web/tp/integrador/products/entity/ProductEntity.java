package arq.web.tp.integrador.products.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Table(name = "PRODUCT")
@Entity
public class ProductEntity implements Serializable {

    private static final long serialVersionUID = 2481350986623601245L;

    @Id
    @Column(name = "ID_PRODUCT", nullable = false, precision = 12)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;
    @Column(name = "DESCRIPTION", nullable = false, length = 255)
    private String description;
    @Column(name = "AMOUNT", nullable = false)
    private Double amount;

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
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
}
