package arq.web.tp.integrador.user.converter;


import arq.web.tp.integrador.roles.converter.RoleConverter;
import arq.web.tp.integrador.roles.entity.RoleEntity;
import arq.web.tp.integrador.user.dto.UserDTO;
import arq.web.tp.integrador.user.dto.UserDTOResponse;
import arq.web.tp.integrador.user.entity.UserEntity;

import java.util.Set;
import java.util.stream.Collectors;

public class UserConverter {
    private UserConverter() {
    }


    public static UserDTOResponse toDTO(UserEntity entity) {
        return UserDTOResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .email(entity.getEmail())
                .password(null)
                .dni(entity.getDni())
                .phone(entity.getPhone())
                .build();
    }

    public static UserEntity toEntity(UserDTO dto) {
/*        UserEntity entity = null;
        //entity.setId(dto.getId());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setRole(dto.getRole());*/
        return UserEntity.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .dni(dto.getDni())
                .phone(dto.getPhone())
                .build();
    }

    public static UserDTOResponse convertToDTO(UserEntity user) {
        Set<RoleEntity> roles = user.getRoles().stream()
                .map(userRole -> {
                    RoleEntity role = userRole.getRole();
                    return RoleEntity.builder()
                            .id(role.getId())
                            .name(role.getName())
                            .description(role.getDescription())
                            .build();
                })
                .collect(Collectors.toSet());

        return  UserDTOResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .password(null)
                .dni(user.getDni())
                .phone(user.getPhone())
                .roles(roles.stream().map(RoleConverter::toDTO).collect(Collectors.toSet()))
                .build();
    }
}
