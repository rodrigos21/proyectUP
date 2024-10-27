package arq.web.tp.integrador.service;

import arq.web.tp.integrador.roles.entity.RoleEntity;
import arq.web.tp.integrador.roles.entity.UserRoleEntity;
import arq.web.tp.integrador.roles.repository.RoleJPARepository;
import arq.web.tp.integrador.roles.repository.UserRoleJPARepository;
import arq.web.tp.integrador.roles.service.RoleService;
import arq.web.tp.integrador.security.AuthService;
import arq.web.tp.integrador.user.dao.UserJPARepository;
import arq.web.tp.integrador.user.dto.UserDTO;
import arq.web.tp.integrador.user.dto.UserUpdateRequest;
import arq.web.tp.integrador.user.entity.UserEntity;
import arq.web.tp.integrador.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;
import java.util.stream.Collectors;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
public class UserServiceTest {
    @Mock
    UserJPARepository userJPARepository;
    @Mock
    UserRoleJPARepository userRoleJPARepository;
    @Mock
    RoleJPARepository roleJPARepository;
    @Mock
    RoleService roleService;
    @Mock
    AuthService authService;
    @InjectMocks
    UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUsers() throws Exception {
        List<UserEntity> users = new ArrayList<>();
        when(userJPARepository.getUsers()).thenReturn(users);
        userService.getUsers();
    }

    @Test
    void getUser() throws Exception {
        UserEntity entity = new UserEntity();
        entity.setDni("1");
        entity.setPhone("1");
        entity.setName("a");
        entity.setSurname("a");
        UserRoleEntity role = UserRoleEntity.builder().build();
        entity.setRoles(Arrays.asList(role).stream().collect(Collectors.toSet()));
        when(userJPARepository.getUserById(1L)).thenReturn(entity);
    }

    @Test
    void createUser() throws Exception {
        UserDTO userDTO = UserDTO.builder()
                .id(1L).name("a").phone("1").surname("a")
                .dni("123").password("123").email("asd").build();
        UserEntity entity = new UserEntity();
        RoleEntity role = new RoleEntity();
        Set<UserRoleEntity> userRoles = new HashSet<>();
        UserRoleEntity userRole = new UserRoleEntity(entity, role);
        when(userJPARepository.save(entity)).thenReturn(entity);
        when(roleJPARepository.findById(1L)).thenReturn(Optional.of(role));
        when(userRoleJPARepository.save(userRole)).thenReturn(userRole);

        userService.createUser(userDTO);
    }

    @Test
    void deleteUser() throws Exception {
        UserEntity entity = UserEntity.builder().id(1L).name("a").surname("a").build();
        when(userJPARepository.getUserById(1L)).thenReturn(entity);
        doNothing().when(userRoleJPARepository).deleteByUserId(1L);
        doNothing().when(userJPARepository).deleteById(1L);
        userService.deleteUser(1L);
    }

    @Test
    void updateUser() throws Exception {
        UserUpdateRequest userDTO = UserUpdateRequest.builder().build();
        UserEntity entity = UserEntity.builder().id(1L).name("a").surname("a").phone("123").dni("123").email("test").build();
        when(userJPARepository.getUserById(1L)).thenReturn(entity);
        when(userJPARepository.save(entity)).thenReturn(entity);
        userService.updateUser(1L, userDTO);
    }
}
