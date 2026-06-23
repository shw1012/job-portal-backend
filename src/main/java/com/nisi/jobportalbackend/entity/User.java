package com.nisi.jobportalbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="users")//Hibernate is trying to create a table literally named user — and just like we discussed,
// user is a reserved keyword in PostgreSQL. So PostgreSQL itself rejects creating a table with that name.
// That's the entire error.The fix: tell Hibernate to name the table something else — users instead of user
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
