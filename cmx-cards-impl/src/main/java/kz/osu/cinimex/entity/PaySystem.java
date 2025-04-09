package kz.osu.cinimex.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pay_system")
public class PaySystem {
    @Id
    @Column(name = "id", length = 10)
    private String id;

    @Column(name = "description")
    private String description;

    @Column(name = "priority", nullable = false)
    private Integer priority;
}
