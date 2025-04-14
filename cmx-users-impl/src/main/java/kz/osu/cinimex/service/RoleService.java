package kz.osu.cinimex.service;

import kz.osu.cinimex.dto.RoleDto;
import kz.osu.cinimex.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService {
    void createRole(RoleDto roleDto);
    Page<RoleDto> getAllRoles(Pageable pageable, String name, String description);
    void deleteRoleById(String name);
    List<Role> getRolesByName(Iterable<String> roleNames);
}
