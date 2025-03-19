package kz.osu.cinimex.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "oca_account")
public class Account {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "number", length = 20, nullable = false)
    private String number;

    @Column(name = "currency", length = 3, nullable = false)
    private String currency;

    @Column(name = "current_status", length = 30, nullable = false)
    private String currentStatus;

    @Column(name = "sum")
    private Double sum;

    @Column(name = "open_date", nullable = false)
    private Date openDate;

    @Column(name = "close_date")
    private Date closeDate;

    @Column(name = "is_blocked", nullable = false)
    private Boolean isBlocked;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_login", nullable = false)
    private String user_login;
}
