package arq.web.tp.integrador.roles.dto;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleId implements Serializable {

    @NotNull
    private Long userId;

    @NotNull
    private Long roleId;

    @Override
    public int hashCode() {
        return userId.hashCode() + roleId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        UserRoleId that = (UserRoleId) obj;

        if (!userId.equals(that.userId)) return false;
        return roleId.equals(that.roleId);
    }
}

