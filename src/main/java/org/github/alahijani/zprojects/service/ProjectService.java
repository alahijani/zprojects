package org.github.alahijani.zprojects.service;

import org.github.alahijani.zprojects.model.Project;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Ali Lahijani
 */
@Repository
@Transactional
public class ProjectService {

    @PersistenceContext
    private EntityManager em;

    /**
     * any initialization logic should be put here.
     */
    @PostConstruct
    void postConstruct() {
    }

    /**
     * @return the collection of all projects saved in the database
     */
    @SuppressWarnings("unchecked")
    public List<Project> findAll() {
        return em.createQuery("select u from Project u").getResultList();
    }

    /**
     * persists the given entity if it is transient, or updates the database if the entity is persistent.
     *
     * @param project the entity to save
     * @return the same entity after save
     */
    public Project save(Project project) {
        return em.merge(project);
    }

    /**
     * @param code the code to search for
     * @return the <code>Project</code> having the given code
     * @throws javax.persistence.NoResultException
     *          if no <code>Project</code> in the database has the given code
     */
    public Project findByCode(String code) throws NoResultException {
        return (Project)
                em.createQuery("select u from Project u where u.code = :code")
                        .setParameter("code", code)
                        .setMaxResults(1)
                        .getSingleResult();
    }

    public Project findById(String id) throws NoResultException {
        return em.find(Project.class, id);
    }

    /**
     * Called before a call to {@link #save(Project)} to check if the {@code code} property of the
     * {@code project} being saved is a duplicate.
     * <p/>
     * Returns true if <ul>
     * <li>the {@code project} is new (transient), and its {@code code} property is already
     * allotted to another project, or</li>
     * <li>the {@code project} is persistent and it's {@code code} property is being changed,
     * but its new {@code code} property is already allotted to another project.</li>
     * </ul>
     *
     * @param project the entity which is being validated before save
     * @return if the {@code code} property is a duplicate
     */
    public boolean duplicateCode(Project project) {
        Query query;
        if (project.getId() == null) {
            query = em.createQuery("select count(u) from Project u where u.code = :code")
                    .setParameter("code", project.getCode());
        } else {
            query = em.createQuery("select count(u) from Project u where u.code = :code and u.id <> :id")
                    .setParameter("code", project.getCode())
                    .setParameter("id", project.getId());
        }

        Number count = (Number) query.getSingleResult();
        return count.intValue() > 0;
    }

}
