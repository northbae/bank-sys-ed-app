package kz.osu.cinimex.mapper;

import kz.osu.cinimex.dto.ChangeUserDto;
import kz.osu.cinimex.dto.UserDto;
import kz.osu.cinimex.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {RoleMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(target = "roles", ignore = true)
    User userDtoToUser(UserDto UserDto);

    @Mapping(target = "roles", ignore = true)
    User changeUserDtoToUser(ChangeUserDto changeUserDto);

    @Mapping(target = "roles", source = "roles", qualifiedByName = "roleToRoleName")
    UserDto userToUserDto(User user);
}
