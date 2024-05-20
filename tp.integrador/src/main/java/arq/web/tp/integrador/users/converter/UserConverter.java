package arq.web.tp.integrador.users.converter;

import arq.web.tp.integrador.users.dto.UserDTO;
import arq.web.tp.integrador.users.entity.UserEntity;

public class UserConverter {
    private UserConverter(){}

    public static UserDTO toDTO(UserEntity entity){
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setRole(entity.getRole());
        return dto;
    }

    public static UserEntity toEntity (UserDTO dto){
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setRole(dto.getRole());
        return entity;
    }
}
