package kz.osu.cinimex.controller;

import kz.osu.cinimex.dto.RoleDto;
import kz.osu.cinimex.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RoleControllerImpl implements RoleController {
    private final RoleService roleService;

    @Override
    public ResponseEntity<Void> createRole(RoleDto roleDto) {
        roleService.createRole(roleDto);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Page<RoleDto>> getAllRoles(Pageable pageable, String name, String description) {
        return ResponseEntity.ok(roleService.getAllRoles(pageable, name, description));
    }

    @Override
    public ResponseEntity<Void> deleteRole(String name) {
        roleService.deleteRoleById(name);
        return ResponseEntity.ok().build();
    }
}
