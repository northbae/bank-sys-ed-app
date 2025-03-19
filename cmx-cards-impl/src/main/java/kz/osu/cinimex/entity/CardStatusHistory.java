package kz.osu.cinimex.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "card_status_history")
public class CardStatusHistory {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "status", length = 30, nullable = false)
    private String status;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;
}
