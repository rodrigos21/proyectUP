package arq.web.tp.integrador.roles.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
@Builder
public class RoleDTO implements Serializable {
    private Long id;
    private String name;
    private String description;

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        RoleDTO that = (RoleDTO) obj;
        return Objects.equals(id, that.id);
    }
}
