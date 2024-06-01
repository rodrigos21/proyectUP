package arq.web.tp.integrador.roles.service;

import java.util.ArrayList;
import java.util.List;

import arq.web.tp.integrador.exceptions.CustomException;
import arq.web.tp.integrador.roles.converter.RoleConverter;
import arq.web.tp.integrador.roles.dto.RoleDTO;
import arq.web.tp.integrador.roles.dto.UserRoleId;
import arq.web.tp.integrador.roles.entity.RoleEntity;
import arq.web.tp.integrador.roles.entity.UserRoleEntity;
import arq.web.tp.integrador.roles.repository.RoleJPARepository;
import arq.web.tp.integrador.roles.repository.UserRoleJPARepository;
import jakarta.transaction.Transactional;
import lombok.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@Builder
public class RoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleService.class);

    @Autowired
    private RoleJPARepository roleJPARepository;
    @Autowired
    private UserRoleJPARepository userRoleJPARepository;

    @Transactional
    public RoleDTO getRole(Long roleId) {
        RoleEntity role = roleJPARepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found: " + roleId));
        return RoleConverter.toDTO(role);
    }

    @Transactional
    public Long generateRole(RoleDTO role) throws CustomException {
        RoleEntity newRole = null;
        try {
            newRole = RoleEntity.builder()
                    .id(role.getId())
                    .name(role.getName())
                    .description(role.getDescription())
                    .build();
            newRole = roleJPARepository.save(newRole);
        } catch (Exception e) {
            throw new CustomException("Error creating role", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return newRole.getId();
    }

    @Transactional
    public void updateRole(Long id, RoleDTO role) {
        String name = role.getName();
        String description = role.getDescription();
        roleJPARepository.updateRole(id, name, description);
    }

    @Transactional
    public void deleteRole(Long roleId) {
        var r = userRoleJPARepository.getUsersRole(roleId);
        if (!ObjectUtils.isEmpty(r)) {
            throw new CustomException("One or more users have this role", HttpStatus.CONFLICT);
        }
        roleJPARepository.deleteById(roleId);
    }

    public List<RoleDTO> getRoles() {
        List<RoleDTO> listDTO = new ArrayList<>();
        List<RoleEntity> listEntity = null;
        try {
            listEntity = roleJPARepository.findAll();
            for (RoleEntity entity : listEntity) {
                listDTO.add(RoleDTO.builder()
                        .id(entity.getId())
                        .name(entity.getName())
                        .description(entity.getDescription())
                        .build());
            }
        } catch (Exception e) {
            throw new CustomException("Error obtaining roles", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return listDTO;
    }

}

