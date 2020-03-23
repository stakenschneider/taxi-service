package com.kspt.app.entities.actor;

import com.kspt.app.entities.Credentials;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Masha on 23.03.2020
 */
@Entity
@Table(name = "Role")
public class Role implements GrantedAuthority {
    @Id
    private Long id;
    private String name;
    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<Credentials> users;
    public Role() {
    }

    public Role(Long id) {
        this.id = id;
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Credentials> getUsers() {
        return users;
    }

    public void setUsers(Set<Credentials> users) {
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}