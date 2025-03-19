package kz.osu.cinimex.repository;

import jdk.jfr.Registered;
import kz.osu.cinimex.entity.CardStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@Registered
public interface CardStatusHistoryRepository extends JpaRepository<CardStatusHistory, UUID> {
}
