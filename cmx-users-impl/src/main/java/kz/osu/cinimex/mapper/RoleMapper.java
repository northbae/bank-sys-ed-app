package kz.osu.cinimex.mapper;

import kz.osu.cinimex.dto.RoleDto;
import kz.osu.cinimex.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDto roleToRoleDto(Role role);

    Role roleDtoToRole(RoleDto roleDto);

    @Named("roleToRoleName")
    default Set<String> roleToRoleName(List<Role> roleList) {
        return roleList.stream().map(Role::getName).collect(Collectors.toSet());
    }
}