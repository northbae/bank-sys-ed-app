package kz.osu.cinimex.repository;

import kz.osu.cinimex.model.entity.AccountStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountStatusHistoryRepository extends JpaRepository<AccountStatusHistory, UUID> {
}
