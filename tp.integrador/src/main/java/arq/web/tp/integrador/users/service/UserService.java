package arq.web.tp.integrador.users.service;

import arq.web.tp.integrador.auth.dto.UserCredentialDTO;
import arq.web.tp.integrador.auth.dto.UserLoginResponse;
import arq.web.tp.integrador.users.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getUsers();

    UserDTO getUser(Long userId);

    Long createUser(UserDTO userDTO);

    void deleteUser(Long userId);

    UserLoginResponse login(UserCredentialDTO userCredentialDTO);
}
