package kz.osu.cinimex.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
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

    @Column(name = "title")
    private String title;

    @ManyToMany()
    @JoinTable(name = "ocu_user_role",
               joinColumns = @JoinColumn(name = "user_login"),
               inverseJoinColumns = @JoinColumn(name = "role_name"))
    private List<Role> roles = new ArrayList<>();
}
