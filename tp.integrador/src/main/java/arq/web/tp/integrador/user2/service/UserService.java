package arq.web.tp.integrador.user2.service;

import arq.web.tp.integrador.auth.dto.UserCredentialDTO;
import arq.web.tp.integrador.auth.dto.UserLoginResponse;
import arq.web.tp.integrador.exceptions.CustomException;
import arq.web.tp.integrador.roles.converter.RoleConverter;
import arq.web.tp.integrador.roles.dto.RoleDTO;
import arq.web.tp.integrador.roles.dto.UserRoleId;
import arq.web.tp.integrador.roles.entity.RoleEntity;
import arq.web.tp.integrador.roles.entity.UserRoleEntity;
import arq.web.tp.integrador.roles.repository.RoleJPARepository;
import arq.web.tp.integrador.roles.repository.UserRoleJPARepository;
import arq.web.tp.integrador.roles.service.RoleService;
import arq.web.tp.integrador.user2.converter.UserConverter;
import arq.web.tp.integrador.user2.dao.UserJPARepository;
import arq.web.tp.integrador.user2.dto.UserDTO;
import arq.web.tp.integrador.user2.dto.UserDTOResponse;
import arq.web.tp.integrador.user2.dto.UserUpdateRequest;
import arq.web.tp.integrador.user2.entity.UserEntity;


import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleService.class);

    private UserJPARepository userJPARepository;
    private UserRoleJPARepository userRoleJPARepository;
    private RoleJPARepository roleJPARepository;
    private RoleService roleService;

    @Autowired
    public UserService(UserJPARepository userJPARepository,
                       RoleJPARepository roleJPARepository,
                       UserRoleJPARepository userRoleJPARepository,
                       RoleService roleService) {
        this.userJPARepository = userJPARepository;
        this.roleJPARepository = roleJPARepository;
        this.userRoleJPARepository = userRoleJPARepository;
        this.roleService = roleService;
    }

    @Transactional
    public List<UserDTOResponse> getUsers() {
        List<UserEntity> users = userJPARepository.getUsers();
        return users.stream()
                .map(UserConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserDTOResponse getUser(Long userId) throws CustomException {
        UserEntity entity = getAndValidateUser(userId);
        return UserConverter.convertToDTO(entity);
    }

    @Transactional
    public Long createUser(UserDTO userDTO) {
        UserEntity user = UserEntity.builder()
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .dni(userDTO.getDni())
                .phone(userDTO.getPhone())
                .build();
        user = userJPARepository.save(user);

        Set<UserRoleEntity> userRoles = new HashSet<>();
        for (Long roleId : userDTO.getRoles()) {
            RoleEntity role = roleJPARepository.findById(roleId)
                    .orElseThrow(() -> new RuntimeException("Role not found: " + roleId));

            UserRoleEntity userRole = new UserRoleEntity(user, role);
            userRoles.add(userRoleJPARepository.save(userRole));
        }
        user.setRoles(userRoles);

        return user.getId();
    }

    @Transactional
    public void deleteUser(Long userId) {
        getAndValidateUser(userId);
        userRoleJPARepository.deleteByUserId(userId);
        userJPARepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(Long userId, UserUpdateRequest userDTO) {
        UserEntity entity = getAndValidateUser(userId);
        if (!entity.getName().equalsIgnoreCase(userDTO.getName())) {
            entity.setName(userDTO.getName());
        }
        if (!entity.getSurname().equalsIgnoreCase(userDTO.getSurname())) {
            entity.setSurname(userDTO.getSurname());
        }
        if (!entity.getEmail().equalsIgnoreCase(userDTO.getEmail())) {
            entity.setEmail(userDTO.getEmail());
        }
        if (!entity.getDni().equals(userDTO.getDni())) {
            entity.setDni(userDTO.getDni());
        }
        if (!entity.getPhone().equals(userDTO.getPhone())) {
            entity.setPhone(userDTO.getPhone());
        }
        userJPARepository.save(entity);
    }

    @Transactional
    public void addRoleToUser(Long userId, Long roleId) {
        UserEntity entity = userJPARepository.getUserById(userId);
        if (entity == null || ObjectUtils.isEmpty(entity)) {
            throw new CustomException("User not found", HttpStatus.NOT_FOUND);
        }
        RoleDTO roleDTO = roleService.getRole(roleId);
        if(userRoleJPARepository.existsById(new UserRoleId(userId, roleId))){
            throw new CustomException("The user already has this role", HttpStatus.CONFLICT);
        }
        userRoleJPARepository.addRoleToUser(userId, roleId);
    }

    @Transactional
    public void deleteUserRole(Long userId, Long roleId) {
        UserEntity entity = userJPARepository.getUserById(userId);
        if (entity == null || ObjectUtils.isEmpty(entity)) {
            throw new CustomException("User not found", HttpStatus.NOT_FOUND);
        }
        RoleDTO roleDTO = roleService.getRole(roleId);
        userRoleJPARepository.deleteUserRole(userId, roleId);
    }

    private UserEntity getAndValidateUser(Long userId) {
        UserEntity entity = userJPARepository.getUserById(userId);
        if (entity == null || ObjectUtils.isEmpty(entity)) {
            throw new CustomException("User not found", HttpStatus.NOT_FOUND);
        }
        return entity;
    }

    public UserLoginResponse login(UserCredentialDTO userCredentialDTO) {
       /* if (userCredentialDTO.getEmail() != null && userCredentialDTO.getPassword() != null) {
            UserLoginResponse response = new UserLoginResponse();
            var r = userJPARepository.findByEmail(userCredentialDTO.getEmail());
            if (r != null && userCredentialDTO.getEmail().equals(r.getEmail())
                    && userCredentialDTO.getPassword().equals(r.getPassword())) {
                UserDTO dto = new UserDTO();
                dto.setId(r.getId());
                dto.setEmail(r.getEmail());
                dto.setRole(r.getRole());
                response.setSuccess(true);
                response.setMessage("Login successfully");
                response.setUserDTO(dto);
                return response;
            }
        }*/
        return null;
    }

}
