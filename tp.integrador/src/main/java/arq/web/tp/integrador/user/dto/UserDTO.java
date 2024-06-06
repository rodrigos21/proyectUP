package arq.web.tp.integrador.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Data
@Builder
public class UserDTO implements Serializable {

    private Long id;
    @NotBlank(message = "name cannot be blank")
    private String name;
    @NotBlank(message = "surname cannot be blank")
    private String surname;
    @NotBlank(message = "email cannot be blank")
    private String email;
    @NotBlank(message = "password cannot be blank")
    private String password;
    @NotBlank(message = "dni cannot be blank")
    private String dni;
    @NotBlank(message = "phone cannot be blank")
    private String phone;
/*    @NotEmpty*/
    private Set<Long> roles;

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UserDTO that = (UserDTO) obj;
        return Objects.equals(id, that.id);
    }
}
