package arq.web.tp.integrador.users.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "USERS")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 2481350986623601245L;

    @Id
    @Column(name = "ID_USER", nullable = false, precision = 12)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "EMAIL", nullable = false, length = 50)
    private String email;
    @Column(name = "PASSWORD", nullable = false, length = 50)
    private String password;
    @Column(name = "ROLE", nullable = false, length = 20)
    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
