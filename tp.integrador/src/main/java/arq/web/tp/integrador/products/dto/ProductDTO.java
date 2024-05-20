package arq.web.tp.integrador.products.dto;

import java.io.Serializable;

public class ProductDTO implements Serializable {

    private static final long serialVersionUID = 2481350986623601245L;

    private Long id;
    private String description;
    private Double amount;

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
}
