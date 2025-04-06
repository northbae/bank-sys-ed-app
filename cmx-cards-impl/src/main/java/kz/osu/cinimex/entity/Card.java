package kz.osu.cinimex.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "card")
public class Card {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "number", length = 16, nullable = false)
    private String number;

    @Column(name = "type", length = 4, nullable = false)
    private String type;

    @Column(name = "cardholder", length = 100)
    private String cardholder;

    @Column(name = "open_date", nullable = false)
    private LocalDate openDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "close_date")
    private LocalDate closeDate;

    @Column(name = "is_blocked", nullable = false)
    private Boolean isBlocked;

    @Enumerated(EnumType.STRING)
    @Column(name = "current_status", length = 10, nullable = false)
    private AccountStatus currentStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pay_system_code", nullable = false)
    private PaySystem paySystem;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private List<UUID> accounts = new ArrayList<>();
}
