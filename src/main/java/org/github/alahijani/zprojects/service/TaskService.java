package org.github.alahijani.zprojects.service;

import org.github.alahijani.zprojects.model.Project;
import org.github.alahijani.zprojects.model.Task;
import org.github.alahijani.zprojects.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Ali Lahijani
 */
@Repository
@Transactional
public class TaskService {

    @PersistenceContext
    private EntityManager em;

    @Resource
    private UserService userService;

    /**
     * any initialization logic should be put here.
     */
    @PostConstruct
    void postConstruct() {
    }

    /**
     * @return the collection of all tasks saved in the database
     */
    @SuppressWarnings("unchecked")
    public List<Task> findAll() {
        return em.createQuery("select u from Task u").getResultList();
    }

    /**
     * @return the collection of all tasks saved in the database
     */
    @SuppressWarnings("unchecked")
    public List<Task> findVisible(String username) {
        User user = userService.findByUsername(username);
        if (user.isAdmin()) {
            return findAll();
        } else {
            return em.createQuery("select u from Task u where u.assignee.id = :userId")
                    .setParameter("userId", user.getId())
                    .getResultList();
        }
    }

    /**
     * @return the collection of all tasks saved in the database
     */
    @SuppressWarnings("unchecked")
    public List<Task> findAll(Project project) {
        return em.createQuery("select u from Task u where u.project.id = :projectId")
                .setParameter("projectId", project.getId())
                .getResultList();
    }
    /**
     * @return the collection of all tasks saved in the database
     */
    @SuppressWarnings("unchecked")
    public List<Task> findVisible(Project project, String username) {
        User user = userService.findByUsername(username);
        if (user.isAdmin()) {
            return findAll(project);
        } else {
            return em.createQuery("select u from Task u where u.project.id = :projectId and u.assignee.id = :userId")
                    .setParameter("projectId", project.getId())
                    .setParameter("userId", user.getId())
                    .getResultList();
        }
    }

    /**
     * persists the given entity if it is transient, or updates the database if the entity is persistent.
     *
     * @param task the entity to save
     * @return the same entity after save
     */
    public Task save(Task task) {
        return em.merge(task);
    }

    public Task findById(String id) throws NoResultException {
        return em.find(Task.class, id);
    }

}
