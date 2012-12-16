package org.github.alahijani.zprojects.model;

import org.hibernate.validator.constraints.Length;

import javax.annotation.Nonnull;
import javax.persistence.*;

/**
 * @author Ali Lahijani
 */
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue
    private String id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Project project;

    @Nonnull
    @Column(nullable = false)
    @Length(min = 5, message = "{length.title}")
    private String title;

    @Nonnull
    @Column(nullable = false)
    @Length(min = 15, message = "{length.description}")
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
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
