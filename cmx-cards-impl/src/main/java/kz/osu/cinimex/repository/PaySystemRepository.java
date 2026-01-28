package kz.osu.cinimex.repository;

import kz.osu.cinimex.entity.PaySystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaySystemRepository extends JpaRepository<PaySystem, String> {
}
