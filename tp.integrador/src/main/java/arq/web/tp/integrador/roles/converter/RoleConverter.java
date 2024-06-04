package arq.web.tp.integrador.roles.converter;

import arq.web.tp.integrador.roles.dto.RoleDTO;
import arq.web.tp.integrador.roles.entity.RoleEntity;

public class RoleConverter {
    private RoleConverter(){

    }

    public static RoleDTO toDTO(RoleEntity roleEntity){
        return RoleDTO.builder()
                .id(roleEntity.getId())
                .name(roleEntity.getName())
                .description(roleEntity.getDescription())
                .build();
    }

    public static RoleEntity toEntity(RoleDTO roleDTO){
        return RoleEntity.builder()
                .id(roleDTO.getId())
                .name(roleDTO.getName())
                .description(roleDTO.getDescription())
                .build();
    }
}
