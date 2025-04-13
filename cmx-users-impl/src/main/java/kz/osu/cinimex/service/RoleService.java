package kz.osu.cinimex.service;

import kz.osu.cinimex.dto.RoleDto;
import kz.osu.cinimex.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PagedModel;

import java.util.List;

public interface RoleService {
    void createRole(RoleDto roleDto);
    PagedModel<RoleDto> getAllRoles(Pageable pageable, String name, String description);
    void deleteRoleById(String name);
    List<Role> getRolesByName(List<String> roleNames);
    boolean existsByName(String name);
}
