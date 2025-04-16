package kz.osu.cinimex.mapper;

import kz.osu.cinimex.dto.ChangeUserDto;
import kz.osu.cinimex.dto.UserDto;
import kz.osu.cinimex.entity.Role;
import kz.osu.cinimex.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RoleMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(target = "roles", ignore = true)
    User userDtoToUser(UserDto UserDto);

    @Mapping(target = "roles", source = "roles", qualifiedByName = "roleToRoleName")
    UserDto userToUserDto(User user);

    default User userChangesToUser(User user, ChangeUserDto userChanges, List<Role> roles) {
        user.setLastName(userChanges.getLastName());
        user.setFirstName(userChanges.getFirstName());
        user.setEmail(userChanges.getEmail());
        user.setPhone(userChanges.getPhone());
        user.setRoles(roles);
        return user;
    }
}
