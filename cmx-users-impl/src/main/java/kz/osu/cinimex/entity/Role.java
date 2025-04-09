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
@Table(name = "ocu_role")
public class Role {
    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
