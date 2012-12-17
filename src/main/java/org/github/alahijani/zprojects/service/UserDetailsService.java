package org.github.alahijani.zprojects.service;

import org.github.alahijani.zprojects.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author Ali Lahijani
 */
@Service
public class UserDetailsService implements UserDetailsManager {
    @Resource
    private UserService userService;

    @Override
    public void createUser(org.springframework.security.core.userdetails.UserDetails details) {
        User user = new User();

        user.setFullName(details.getUsername());
        user.setUsername(details.getUsername());
        user.setPassword(details.getPassword());
        userService.save(user);
    }

    @Override
    public void updateUser(org.springframework.security.core.userdetails.UserDetails details) {
        String username = details.getUsername();
        User user = userService.findByUsername(username);

        user.setFullName(details.getUsername());
        user.setUsername(details.getUsername());
        user.setPassword(details.getPassword());
        userService.save(user);
    }

    @Override
    public void deleteUser(String username) {
        userService.deleteUser(username);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        throw new UnsupportedOperationException("changePassword");
    }

    @Override
    public boolean userExists(String username) {
        User user = new User();
        user.setUsername(username);
        return userService.duplicateUsername(user);
    }

    private final SimpleGrantedAuthority user = new SimpleGrantedAuthority("user");
    private final SimpleGrantedAuthority admin = new SimpleGrantedAuthority("admin");
    private final List<? extends GrantedAuthority> OrdinaryUser = Arrays.asList(user);
    private final List<? extends GrantedAuthority> Administrator = Arrays.asList(admin, user);

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                true,                           // accountNonExpired
                true,                           // credentialsNonExpired
                true,                           // accountNonLocked
                user.isAdmin() ? Administrator : OrdinaryUser);
    }

}


