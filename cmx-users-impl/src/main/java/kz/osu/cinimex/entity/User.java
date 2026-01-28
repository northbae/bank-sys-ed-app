package kz.osu.cinimex.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ocu_user")
public class User {
    @Id
    @Column(name = "login")
    private String login;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone")
    private String phone;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "ocu_user_role",
               joinColumns = @JoinColumn(name = "user_login"),
               inverseJoinColumns = @JoinColumn(name = "role_name"))
    private List<Role> roles = new ArrayList<>();
}
