package arq.web.tp.integrador.service;

import arq.web.tp.integrador.roles.dto.RoleDTO;
import arq.web.tp.integrador.roles.entity.RoleEntity;
import arq.web.tp.integrador.roles.entity.UserRoleEntity;
import arq.web.tp.integrador.roles.repository.RoleJPARepository;
import arq.web.tp.integrador.roles.repository.UserRoleJPARepository;
import arq.web.tp.integrador.roles.service.RoleService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class RoleServiceTest {

    @Mock
    RoleJPARepository roleJPARepository;
    @Mock
    UserRoleJPARepository userRoleJPARepository;
    @InjectMocks
    RoleService roleService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void getRole() throws Exception {
        RoleEntity entity = RoleEntity.builder().build();
        when(roleJPARepository.findById(1L)).thenReturn(Optional.ofNullable(entity));
        roleService.getRole(1L);
    }

    @Test
    void createRole() throws Exception {
        RoleDTO role = RoleDTO.builder().build();
        RoleEntity entity = RoleEntity.builder().build();
        when(roleJPARepository.save(entity)).thenReturn(entity);

        roleService.createRole(role);
    }

    @Test
    void updateRole() throws Exception {
        RoleDTO roleDTO = RoleDTO.builder().id(1L).description("test").name("test").build();
        doNothing().when(roleJPARepository).updateRole(1L, "test", "test");
        roleService.updateRole(1L, roleDTO);
    }

    @Test
    void deleteRole() throws Exception {
        List<UserRoleEntity> list = new ArrayList<>();

        when(userRoleJPARepository.getUsersRole(1L)).thenReturn(list);
        roleService.deleteRole(1L);
    }

    @Test
    void getRoles() throws Exception {
        List<RoleDTO> listDTO = new ArrayList<>();
        RoleDTO roleDTO = RoleDTO.builder().build();
        listDTO.add(roleDTO);
        List<RoleEntity> listEntity = new ArrayList<>();
        when(roleJPARepository.findAll()).thenReturn(listEntity);
        roleService.getRoles();
    }
}
