package org.github.alahijani.zprojects.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Ali Lahijani
 */
@Entity
@Table(name = "Tasks")
public class Task {
    @Id
    private String id;
    @ManyToOne
    private Project project;

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
