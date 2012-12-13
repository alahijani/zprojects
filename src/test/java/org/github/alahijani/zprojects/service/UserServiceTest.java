package org.github.alahijani.zprojects.service;

import org.github.alahijani.zprojects.model.User;
import org.junit.Test;

import javax.annotation.Resource;
import javax.persistence.PersistenceException;

/**
 * @author Ali Lahijani
 */
public class UserServiceTest extends BaseServiceTest {

    @Test(expected = PersistenceException.class)
    public void testNullUsername() throws Exception {
        User user = new User();
        service.save(user);

        em.flush();
    }

    @Test(expected = PersistenceException.class)
    public void testDuplicateUsername() throws Exception {
        User user1 = new User();
        user1.setUsername("jimmy");
        service.save(user1);

        User user2 = new User();
        user2.setUsername("jimmy");
        service.save(user2);

        em.flush();
    }

    @Resource
    private UserService service;
    public void testSaveAndFindByUsername() throws Exception {
        User user = new User();
        service.save(user);
    }
}
