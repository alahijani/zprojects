package org.github.alahijani.zprojects.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * @author Ali Lahijani
 */
@Service
public class UserService {
    @Resource
    private BaseService baseService;

    @PostConstruct
    void postConstruct() throws SQLException {
    }

}
