package org.github.alahijani.zprojects.model;

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
    private Project project;
    private String title;
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
}
