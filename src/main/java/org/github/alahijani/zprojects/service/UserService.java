package org.github.alahijani.zprojects.service;

import org.github.alahijani.zprojects.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Ali Lahijani
 */
@Repository
@Transactional
public class UserService extends BaseService<User> {

    public UserService() {
        super(User.class);
    }

    /**
     * any initialization logic should be put here.
     */
    @PostConstruct
    void postConstruct() {
    }

    /**
     * @return the collection of all users saved in the database, regardless of their
     *         {@link User#isEnabled() enabled} status
     */
    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        return em.createQuery("select u from User u").getResultList();
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

    /**
     * Called before a call to {@link #save(org.github.alahijani.zprojects.model.BaseEntity)}
     * to check if the {@code username} property of the {@code user} being saved is a duplicate.
     * <p/>
     * Returns true if <ul>
     * <li>the {@code user} is new (transient), and its {@code username} property is already
     * allotted to another user, or</li>
     * <li>the {@code user} is persistent and it's {@code username} property is being changed,
     * but its new {@code username} property is already allotted to another user.</li>
     * </ul>
     *
     * @param user the entity which is being validated before save
     * @return if the {@code username} property is a duplicate
     */
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

    public void deleteUser(String username) {
        em.createQuery("delete from User u where u.username = :username")
                .setParameter("username", username)
                .executeUpdate();
    }

}
