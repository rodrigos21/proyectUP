package arq.web.tp.integrador.auth.dto;


import arq.web.tp.integrador.user2.dto.UserDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginResponse {
    private Boolean success;
    private String message;
    private String token;
    private UserDTO userDTO;
}
