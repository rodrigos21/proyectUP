package arq.web.tp.integrador.user2.service;

import arq.web.tp.integrador.exceptions.CustomException;
import arq.web.tp.integrador.roles.dto.RoleDTO;
import arq.web.tp.integrador.roles.dto.UserRoleId;
import arq.web.tp.integrador.roles.entity.RoleEntity;
import arq.web.tp.integrador.roles.entity.UserRoleEntity;
import arq.web.tp.integrador.roles.repository.RoleJPARepository;
import arq.web.tp.integrador.roles.repository.UserRoleJPARepository;
import arq.web.tp.integrador.roles.service.RoleService;
import arq.web.tp.integrador.security.AuthService;
import arq.web.tp.integrador.security.RegisterRequest;
import arq.web.tp.integrador.user2.converter.UserConverter;
import arq.web.tp.integrador.user2.dao.UserJPARepository;
import arq.web.tp.integrador.user2.dto.Report;
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
    private AuthService authService;

    @Autowired
    public UserService(UserJPARepository userJPARepository,
                       RoleJPARepository roleJPARepository,
                       UserRoleJPARepository userRoleJPARepository,
                       RoleService roleService,
                       AuthService authService) {
        this.userJPARepository = userJPARepository;
        this.roleJPARepository = roleJPARepository;
        this.userRoleJPARepository = userRoleJPARepository;
        this.roleService = roleService;
        this.authService = authService;
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

        if (userDTO.getRoles() != null && !userDTO.getRoles().isEmpty()) {
            Set<UserRoleEntity> userRoles = new HashSet<>();
            for (Long roleId : userDTO.getRoles()) {
                RoleEntity role = roleJPARepository.findById(roleId)
                        .orElseThrow(() -> new CustomException("Role not found",HttpStatus.NOT_FOUND));

                UserRoleEntity userRole = new UserRoleEntity(user, role);
                userRoles.add(userRoleJPARepository.save(userRole));
            }
            user.setRoles(userRoles);
        }

        //  Register a new user on a UserSecurityTable
        authService.register(RegisterRequest.builder().username(userDTO.getEmail()).password(userDTO.getPassword()).build());

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

    public Report getReport() {
        var x =  userRoleJPARepository.findAll();
        List<UserRoleEntity> list = x.stream().map(e->{
            return UserRoleEntity.builder()
                    .user(new UserEntity().builder()
                            .id(e.getUser().getId())
                            .name(e.getUser().getName())
                            .surname(e.getUser().getSurname())
                            .dni(e.getUser().getDni())
                            .email(e.getUser().getEmail())
                            .phone(e.getUser().getPhone())
                    .build())
                    .role(new RoleEntity().builder()
                            .id(e.getRole().getId())
                            .name(e.getRole().getName())
                            .description(e.getRole().getDescription())
                            .build())
                    .build();
        }).collect(Collectors.toList());

        Report report = new Report();
        report.addAll(list);

        return report;
    }
}
