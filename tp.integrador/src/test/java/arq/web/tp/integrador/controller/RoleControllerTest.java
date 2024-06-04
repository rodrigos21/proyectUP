package arq.web.tp.integrador.controller;

import arq.web.tp.integrador.roles.controller.RoleController;
import arq.web.tp.integrador.roles.dto.RoleDTO;
import arq.web.tp.integrador.roles.service.RoleService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
class RoleControllerTest {

    private static String URL = "/api/v1/roles";
    @Mock
    RoleService roleService;

    @InjectMocks
    RoleController roleController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(roleController).build();
    }

    @Test
    void getRolesTest() throws Exception {
        List<RoleDTO> list = new ArrayList<>();
        doReturn(list).when(roleService).getRoles();
        mockMvc.perform(get(URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void getRoleTest() throws Exception {
        var r = RoleDTO.builder().build();
        doReturn(r).when(roleService).getRole(1L);
        mockMvc.perform(get(URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void createRoleTest() throws Exception{
        String payload = "{\n" +
                "    \"name\": \"TEST\",\n" +
                "    \"description\": \"TEST\"\n" +
                "}";
        doReturn(1L).when(roleService).createRole(RoleDTO.builder().build());
        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(MockMvcResultMatchers.status().is(201));
    }

    @Test
    void updateRoleTest() throws Exception{
        String payload = "{\n" +
                "    \"name\": \"TEST\",\n" +
                "    \"description\": \"TEST\"\n" +
                "}";
        doNothing().when(roleService).updateRole(1L,RoleDTO.builder().build());
        mockMvc.perform(patch(URL+"/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(MockMvcResultMatchers.status().is(204));
    }

    @Test
    void deleteRoleTest() throws Exception{
        doNothing().when(roleService).deleteRole(1L);
        mockMvc.perform(delete(URL+"/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(204));
    }

}
