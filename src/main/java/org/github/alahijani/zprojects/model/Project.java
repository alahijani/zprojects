package org.github.alahijani.zprojects.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Ali Lahijani
 */
@Entity
@Table(name = "Projects")
public class Project {
    private String id;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
