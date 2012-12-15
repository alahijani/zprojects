package org.github.alahijani.zprojects.service;

import org.github.alahijani.zprojects.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Ali Lahijani
 */
@Repository
@Transactional
public class UserService {
    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    void postConstruct() throws SQLException {
    }

    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        return em.createQuery("select u from User u").getResultList();
    }

    /**
     * persists the given entity if it is transient, or updates the database if the entity is persistent.
     *
     * @param user the entity to save
     * @return the same entity after save
     */
    public User save(User user) {
        return em.merge(user);
    }

    /**
     * @param username the username to search for
     * @return the <code>User</code> having the given username
     * @throws NoResultException if no <code>User</code> in the database has the given username
     */
    public User findByUsername(String username) throws NoResultException {
        return (User)
                em.createQuery("select u from User u where u.username = :username")
                        .setParameter("username", username)
                        .setMaxResults(1)
                        .getSingleResult();
    }

    public User findById(String id) throws NoResultException {
        return (User)
                em.createQuery("select u from User u where u.id = :id")
                        .setParameter("id", id)
                        .setMaxResults(1)
                        .getSingleResult();
    }

    public boolean duplicateUsername(User user) {
        Query query;
        if (user.getId() == null) {
            query = em.createQuery("select count(u) from User u where u.username = :username")
                    .setParameter("username", user.getUsername());
        } else {
            query = em.createQuery("select count(u) from User u where u.username = :username and u.id <> :id")
                    .setParameter("username", user.getUsername())
                    .setParameter("id", user.getId());
        }

        Number count = (Number) query.getSingleResult();
        return count.intValue() > 0;
    }
}
