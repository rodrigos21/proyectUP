package arq.web.tp.integrador.user.dto;

import arq.web.tp.integrador.roles.entity.UserRoleEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
@Data
@EqualsAndHashCode(callSuper = false)
public class Report extends ArrayList<UserRoleEntity> {
}
