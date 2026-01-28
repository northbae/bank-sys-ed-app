package kz.osu.cinimex.controller;

import kz.osu.cinimex.dto.DetailAccountDto;
import kz.osu.cinimex.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AccountControllerImpl implements AccountController {
    private final AccountService accountService;

    @Override
    public ResponseEntity<DetailAccountDto> blockAccount(UUID id) {
        return ResponseEntity.ok(accountService.blockAccount(id));
    }

    @Override
    public ResponseEntity<DetailAccountDto> closeAccount(UUID id) {
        return ResponseEntity.ok(accountService.closeAccount(id));
    }
}
