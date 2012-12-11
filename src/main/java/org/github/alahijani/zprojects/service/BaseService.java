package org.github.alahijani.zprojects.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author Ali Lahijani
 */
@Service
public class BaseService {

    @PostConstruct
    void postConstruct() {
    }

}
