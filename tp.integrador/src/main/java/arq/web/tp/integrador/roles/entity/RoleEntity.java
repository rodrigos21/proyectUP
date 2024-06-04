package arq.web.tp.integrador.roles.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Builder
@Data
@Table(name = "ROLES")
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max = 255)
    private String name;
    @Size(max = 255)
    private String description;
    @OneToMany(mappedBy = "role")
    private Set<UserRoleEntity> users;

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        RoleEntity that = (RoleEntity) obj;
        return Objects.equals(id, that.id);
    }
}

