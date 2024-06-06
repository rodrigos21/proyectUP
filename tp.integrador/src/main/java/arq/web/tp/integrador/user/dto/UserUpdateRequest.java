package arq.web.tp.integrador.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
@Builder
public class UserUpdateRequest implements Serializable {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String dni;
    private String phone;

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UserUpdateRequest that = (UserUpdateRequest) obj;
        return Objects.equals(id, that.id);
    }

}
