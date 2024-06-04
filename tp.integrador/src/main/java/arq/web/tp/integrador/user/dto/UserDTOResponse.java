package arq.web.tp.integrador.user.dto;

import arq.web.tp.integrador.roles.dto.RoleDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
@Data
@Builder
public class UserDTOResponse implements Serializable {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String dni;
    @NotBlank
    private String phone;
    @NotEmpty
    private Set<RoleDTO> roles;

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UserDTOResponse that = (UserDTOResponse) obj;
        return Objects.equals(id, that.id);
    }
}