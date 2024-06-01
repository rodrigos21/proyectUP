package arq.web.tp.integrador.auth;

import arq.web.tp.integrador.auth.dto.UserCredentialDTO;

import arq.web.tp.integrador.auth.dto.UserLoginResponse;
import arq.web.tp.integrador.user2.dto.UserDTO;
import arq.web.tp.integrador.user2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

    private UserService userService;

    @Autowired
    public LoginController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserLoginResponse> getUsers(@RequestBody UserCredentialDTO userCredentialDTO) {
        UserLoginResponse r = userService.login(userCredentialDTO);
        /*if(r == null){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }*/
        var t =UserDTO.builder()
                .name("Rosky")
                .surname("rosky")
                .build();

        var q = UserLoginResponse.builder()
                .success(true)
                .userDTO(t)
                .build();
        return new ResponseEntity<>(q,HttpStatus.OK);
    }
}
