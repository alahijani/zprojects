package org.github.alahijani.zprojects.model;

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
    @Column(nullable = false, unique = true)
    private String username;
    private String fullName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
