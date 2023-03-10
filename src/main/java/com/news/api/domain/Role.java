package com.news.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    @ManyToMany
    @JoinTable(name = "roles_privileges",
                joinColumns = @JoinColumn(
                        name = "role_id", referencedColumnName = "id"
                ),
                inverseJoinColumns = @JoinColumn(
                        name = "privilege_id", referencedColumnName = "id"
                ))
    private Set<Privilege> privileges = new HashSet<>();
}
