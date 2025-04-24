package kz.osu.cinimex.repository;

import java.util.Optional;
import kz.osu.cinimex.model.entity.Account;
import kz.osu.cinimex.model.enums.AccountState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    @Query("SELECT currentStatus FROM Account WHERE id = :accountId")
    Optional<AccountState> findCurrentStatusById(UUID accountId);

    @Query("SELECT userLogin FROM Account WHERE id = :accountId")
    String findUserLoginById(UUID accountId);
}
