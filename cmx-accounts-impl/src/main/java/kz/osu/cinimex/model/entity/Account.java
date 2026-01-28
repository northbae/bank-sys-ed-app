package kz.osu.cinimex.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kz.osu.cinimex.model.enums.AccountCurrency;
import kz.osu.cinimex.model.enums.AccountState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "oca_account")
public class Account {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "number", length = 20, nullable = false)
    private String number;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency", length = 3, nullable = false)
    private AccountCurrency currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "current_status", length = 30, nullable = false)
    private AccountState currentStatus;

    @Column(name = "sum")
    private BigDecimal sum;

    @Column(name = "open_date")
    private LocalDate openDate;

    @Column(name = "close_date")
    private LocalDate closeDate;

    @Column(name = "is_blocked", nullable = false)
    private Boolean isBlocked;

    @Column(name = "is_rejected_by_user")
    private Boolean isRejectedByUser;

    @Column(name = "user_login", nullable = false)
    private String userLogin;
}
