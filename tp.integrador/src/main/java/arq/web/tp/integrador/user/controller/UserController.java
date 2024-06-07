package arq.web.tp.integrador.user.controller;

import arq.web.tp.integrador.user.dto.Report;
import arq.web.tp.integrador.user.dto.UserDTO;
import arq.web.tp.integrador.user.dto.UserDTOResponse;
import arq.web.tp.integrador.user.dto.UserUpdateRequest;
import arq.web.tp.integrador.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Validated
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTOResponse> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public UserDTOResponse getUser(@PathVariable(value = "userId") Long userId) {
        return userService.getUser(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createUser(@RequestBody @Valid UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable(value = "userId") Long userId) {
        userService.deleteUser(userId);
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable Long userId,
                           @RequestBody UserUpdateRequest userDTO) {
        userService.updateUser(userId, userDTO);
    }

    @PostMapping("/{userId}/{roleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addRoleToUser(@PathVariable Long userId, @PathVariable Long roleId) {
        userService.addRoleToUser(userId, roleId);
    }

    @DeleteMapping("/{userId}/{roleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserRole(@PathVariable Long userId, @PathVariable Long roleId) {
        userService.deleteUserRole(userId, roleId);
    }

    @GetMapping("/reports")
    public Report getReport() {
        return userService.getReport();
    }
}
