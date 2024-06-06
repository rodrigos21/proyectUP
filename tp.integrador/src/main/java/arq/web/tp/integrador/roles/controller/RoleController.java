package arq.web.tp.integrador.roles.controller;

import arq.web.tp.integrador.roles.dto.RoleDTO;
import arq.web.tp.integrador.roles.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@Validated
public class RoleController {

    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<RoleDTO> getRoles() {
        return roleService.getRoles();
    }

    @GetMapping("/{id}")
    public RoleDTO getRole(@PathVariable Long id) {
        return roleService.getRole(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createRole(@RequestBody @Valid RoleDTO role) {
        return roleService.createRole(role);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRole(@PathVariable Long id, @RequestBody @Valid RoleDTO role) {
        roleService.updateRole(id, role);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
    }
}
