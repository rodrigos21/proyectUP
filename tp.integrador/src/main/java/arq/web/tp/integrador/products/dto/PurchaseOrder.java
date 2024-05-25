package arq.web.tp.integrador.products.dto;

import arq.web.tp.integrador.users.dto.UserDTO;

import java.util.List;

public class PurchaseOrder {
    private Long id;
    private UserDTO user;
    private List<ProductDTO> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
