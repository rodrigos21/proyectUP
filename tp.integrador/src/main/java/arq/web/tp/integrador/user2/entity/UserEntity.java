package arq.web.tp.integrador.user2.entity;

import arq.web.tp.integrador.roles.entity.RoleEntity;
import arq.web.tp.integrador.roles.entity.UserRoleEntity;
import jakarta.persistence.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Data
@Builder
@Table(name = "USERS")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max = 255)
    private String name;
    @NotBlank
    @Size(max = 255)
    private String surname;
    @NotBlank
    @Email
    @Size(max = 255)
    @Column(unique = true)
    private String email;
    @NotBlank
    @Size(min = 8, max = 255)
    //@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$",message = "La contraseña debe contener al menos una letra mayúscula, una letra minúscula, un dígito y un carácter especial")
    private String password;
    @Size(max = 20)
    private String dni;
    @Size(max = 20)
    private String phone;
    @OneToMany(mappedBy = "user")
/*    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))*/
    private Set<UserRoleEntity> roles;

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UserEntity that = (UserEntity) obj;
        return Objects.equals(id, that.id);
    }
}


