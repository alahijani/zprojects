package org.github.alahijani.zprojects.service;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Ali Lahijani
 */
@ContextConfiguration
public class BaseServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Resource
    DataSource dataSource;

    @Test
    public void testDataSource() throws SQLException {
    }

    @PersistenceContext
    EntityManager em;

    @Test
    public void testEntityManager() {
        List resultList = em.createQuery("select x from User x").getResultList();
        System.out.println("resultList = " + resultList);
    }

}
