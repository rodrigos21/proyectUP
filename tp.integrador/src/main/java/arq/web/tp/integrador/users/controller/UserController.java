package arq.web.tp.integrador.users.controller;

import arq.web.tp.integrador.users.dto.UserDTO;
import arq.web.tp.integrador.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public UserDTO getUser(@PathVariable(value = "userId") Long userId) {
        return userService.getUser(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable(value = "userId") Long userId) {
        userService.deleteUser(userId);
    }
}
