package arq.web.tp.integrador.controller;


import arq.web.tp.integrador.user.controller.UserController;
import arq.web.tp.integrador.user.dto.Report;
import arq.web.tp.integrador.user.dto.UserDTO;
import arq.web.tp.integrador.user.dto.UserDTOResponse;
import arq.web.tp.integrador.user.dto.UserUpdateRequest;
import arq.web.tp.integrador.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;


@ExtendWith(SpringExtension.class)
class UserControllerTest {

    private static String URL = "/api/v1/users";
    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void getUsers() throws Exception {
        List<UserDTOResponse> list = new ArrayList<>();
        Mockito.doReturn(list).when(userService).getUsers();
        mockMvc.perform(MockMvcRequestBuilders.get(URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void getUser() throws Exception {
        var dto = UserDTOResponse.builder().build();
        Mockito.doReturn(dto).when(userService).getUser(1L);
        mockMvc.perform(MockMvcRequestBuilders.get(URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void createUserTest() throws Exception {
        Long id = 1L;
        String payload = "{\n" +
                "    \"name\":\"test\",\n" +
                "    \"surname\": \"test\",\n" +
                "    \"email\": \"a@a.com\",\n" +
                "    \"password\": \"1234\",\n" +
                "    \"dni\":123,\n" +
                "    \"phone\":123,\n" +
                "    \"roles\": [1]\n" +
                "}";
        Mockito.doReturn(id).when(userService).createUser(any());
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(MockMvcResultMatchers.status().is(201));
    }

    @Test
    void deleteUserTest() throws Exception {
        Mockito.doNothing().when(userService).deleteUser(1L);
        mockMvc.perform(MockMvcRequestBuilders.delete(URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void updateUserTest() throws Exception {
        String payload = "{\n" +
                "    \"name\":\"Juan\",\n" +
                "    \"surname\": \"Perez\",\n" +
                "    \"dni\":123456456,\n" +
                "    \"phone\":456456456\n" +
                "}";
        var r = UserUpdateRequest.builder().build();
        Mockito.doNothing().when(userService).updateUser(1L, r);
        mockMvc.perform(MockMvcRequestBuilders.put(URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void addUserRoleTest() throws Exception {
        Mockito.doNothing().when(userService).addRoleToUser(1L, 1L);
        mockMvc.perform(MockMvcRequestBuilders.post(URL + "/1/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void deleteUserRoleTest() throws Exception {
        Mockito.doNothing().when(userService).addRoleToUser(1L, 1L);
        mockMvc.perform(MockMvcRequestBuilders.delete(URL + "/1/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void getReportTest() throws Exception {
        Report report = new Report();
        Mockito.doReturn(report).when(userService).getReport();
        mockMvc.perform(MockMvcRequestBuilders.get(URL + "/reports")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
