package org.github.alahijani.zprojects.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Ali Lahijani
 */
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue
    private String id;
    private String code;
    private String title;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
