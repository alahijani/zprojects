package org.github.alahijani.zprojects.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.annotation.Nonnull;
import javax.persistence.*;
import javax.validation.constraints.Pattern;

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
    @Pattern(regexp = "[\\w_\\-.$]*", message = "{format.user.username}")
    @Length(min = 5, message = "{length.user.username}")
    private String username;

    @Nonnull
    @Column(nullable = false)
    @Length(min = 5, message = "{length.user.password}")
    private String password;

    private boolean enabled;
    private boolean admin;

    @Nonnull
    @NotBlank
    private String fullName;

    public User() {
    }

    /**
     * Used by {@link org.springframework.beans.TypeConverterDelegate} to convert from <code>String</code>
     * to <code>User</code> for rendering a combo-box of users.
     *
     * @param id database identifier of this <code>User</code>
     * @see org.github.alahijani.zprojects.controller.TaskBean#getAllUsers()
     */
    public User(String id) {
        this.id = id;
    }

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

    @Nonnull
    public String getPassword() {
        return password;
    }

    public void setPassword(@Nonnull String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
