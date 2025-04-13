package kz.osu.cinimex.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import kz.osu.cinimex.dto.ErrorMessageDto;
import kz.osu.cinimex.dto.RoleDto;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping("api/roles")
public interface RoleController {
    @Operation(summary = "Добавить роль")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Роль добавлена в БД"),
            @ApiResponse(responseCode = "400", description = "Указанная роль уже существует / <название поля> является обязательным полем",
                    content = { @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorMessageDto.class)) })
    })
    @PostMapping
    ResponseEntity<Void> createRole(@Valid @RequestBody
                                             @Parameter(name = "role", description = "Информация о роли")  RoleDto roleDto);

    @Operation(summary = "Найти все роли")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Роли найдены",
                    content = { @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RoleDto.class)) })
    })
    @GetMapping
    ResponseEntity<PagedModel<RoleDto>> getAllRoles(@ParameterObject Pageable pageable,
                                                    @RequestParam(value = "name", required = false) String name,
                                                    @RequestParam(value = "description", required = false) String description);

    @Operation(summary = "Удалить роль")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Роль удалена")
    })
    @DeleteMapping("/{name}")
    ResponseEntity<Void> deleteRole(@PathVariable
                                       @Parameter(name = "name", description = "Название роли") String name);
}
