package kz.osu.cinimex.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import kz.osu.cinimex.dto.DetailAccountDto;
import kz.osu.cinimex.dto.ErrorMessageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping("api/accounts")
public interface AccountController {

    @Operation(summary = "Блокировать счет")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Внутренние проверки банка",
                    content = { @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DetailAccountDto.class)) }),
            @ApiResponse(responseCode = "500", description = "Непредвиденная ошибка. Попробуйте позднее",
                    content = { @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorMessageDto.class)) })
    })
    @PostMapping("/{id}/block")
    ResponseEntity<DetailAccountDto> blockAccount(@PathVariable
                                                  @Parameter(name = "id", description = "id счета") UUID id);

    @Operation(summary = "Закрыть счет")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Внутренние проверки банка",
                    content = { @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DetailAccountDto.class)) }),
            @ApiResponse(responseCode = "500", description = "Непредвиденная ошибка. Попробуйте позднее",
                    content = { @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorMessageDto.class)) })
    })
    @PostMapping("/{id}/close")
    ResponseEntity<DetailAccountDto> closeAccount(@PathVariable
                                                  @Parameter(name = "id", description = "id счета") UUID id);
}
