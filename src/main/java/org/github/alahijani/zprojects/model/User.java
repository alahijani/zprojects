package org.github.alahijani.zprojects.model;

import javax.annotation.Nonnull;
import javax.persistence.*;

/**
 * @author Ali Lahijani
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private String id;

    @Nonnull
    @Column(nullable = false, unique = true)
    private String username;

    @Nonnull
    private String fullName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Nonnull
    public String getUsername() {
        return username;
    }

    public void setUsername(@Nonnull String username) {
        this.username = username;
    }

    @Nonnull
    public String getFullName() {
        return fullName;
    }

    public void setFullName(@Nonnull String fullName) {
        this.fullName = fullName;
    }
}
