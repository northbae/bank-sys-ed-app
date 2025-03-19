package kz.osu.cinimex.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Date openDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Column(name = "close_date")
    private Date closeDate;

    @Column(name = "is_blocked", nullable = false)
    private Boolean isBlocked;

    @Column(name = "current_status", length = 10, nullable = false)
    private String currentStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pay_system_code", nullable = false)
    private PaySystem paySystem;

    @ManyToMany()
    @JoinTable(name = "occ_account_card",
               joinColumns = @JoinColumn(name = "card_id"),
               inverseJoinColumns = @JoinColumn(name = "account_id"))
    private List<UUID> accounts = new ArrayList<>();
}
