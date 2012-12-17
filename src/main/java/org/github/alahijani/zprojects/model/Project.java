package org.github.alahijani.zprojects.model;

import org.hibernate.validator.constraints.Length;

import javax.annotation.Nonnull;
import javax.persistence.*;
import javax.validation.constraints.Pattern;

/**
 * @author Ali Lahijani
 */
@Entity
@Table(name = "projects")
public class Project extends BaseEntity {

    @Nonnull
    @Column(nullable = false, unique = true)
    @Pattern(regexp = "[\\w_\\-.$]*", message = "{format.project.code}")
    @Length(min = 5, message = "{length.project.code}")
    private String code;

    @Nonnull
    @Column(nullable = false)
    @Length(min = 5, message = "{length.title}")
    private String title;

    @Nonnull
    @Column(nullable = false)
    @Length(min = 15, message = "{length.description}")
    private String description;

    public Project() {
    }

    public Project(String id) {
        super(id);
    }

    @Nonnull
    public String getCode() {
        return code;
    }

    public void setCode(@Nonnull String code) {
        this.code = code;
    }

    @Nonnull
    public String getTitle() {
        return title;
    }

    public void setTitle(@Nonnull String title) {
        this.title = title;
    }

    @Nonnull
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nonnull String description) {
        this.description = description;
    }

}
