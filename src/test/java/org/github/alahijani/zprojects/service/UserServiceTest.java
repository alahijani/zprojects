package org.github.alahijani.zprojects.service;

import junit.framework.Assert;
import org.github.alahijani.zprojects.model.User;
import org.junit.Test;

import javax.annotation.Resource;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

/**
 * @author Ali Lahijani
 */
public class UserServiceTest extends BaseServiceTest {
    @Resource
    private UserService service;

    @Test(expected = PersistenceException.class)
    public void testNullUsername() throws Exception {
        User user = new User();
        service.save(user);

        em.flush();
    }

    @Test(expected = PersistenceException.class)
    public void testSaveDuplicateUsername() throws Exception {
        User user1 = new User();
        user1.setUsername("jimmy-123-1");
        service.save(user1);

        User user2 = new User();
        user2.setUsername("jimmy-123-1");
        service.save(user2);

        em.flush();
    }

    @Test
    public void testSaveAndFindByUsername() throws Exception {
        User user = new User();
        user.setUsername("jimmy-123-1");
        service.save(user);

        Assert.assertEquals(service.findByUsername("jimmy-123-1").getUsername(), "jimmy-123-1");
    }

    @Test
    public void testDuplicateUsername() throws Exception {
        User user1 = new User();
        user1.setUsername("jimmy-123-1");
        user1 = service.save(user1);

        User user2 = new User();
        user2.setUsername("jimmy-123-1");
        Assert.assertTrue(service.duplicateUsername(user2));
        Assert.assertFalse(service.duplicateUsername(user1));

        em.remove(user1);
        em.flush();
        Assert.assertFalse(service.duplicateUsername(user2));
    }

    @Test(expected = ConstraintViolationException.class)
    public void testIllegalCharacter() throws Exception {
        User user = new User();
        user.setUsername("man made");    // contains space
        service.save(user);

        em.flush();
    }

    @Test(expected = ConstraintViolationException.class)
    public void testShortUsername() throws Exception {
        User user = new User();
        user.setUsername("man");    // contains space
        service.save(user);

        em.flush();
    }

}
