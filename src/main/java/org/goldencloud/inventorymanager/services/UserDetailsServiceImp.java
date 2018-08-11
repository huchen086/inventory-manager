package org.goldencloud.inventorymanager.services;

import org.goldencloud.inventorymanager.models.dao.UserDao;
import org.goldencloud.inventorymanager.models.Role;
import org.goldencloud.inventorymanager.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

@Service
@Transactional
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            User user = userDao.findByEmail(email);
            if (user == null) {
                throw new UsernameNotFoundException("No user found with username: "+email);
            }
            boolean enabled = true;
            boolean accountNonExpired = true;
            boolean credentialsNonExpired = true;
            boolean accountNonLocked = true;
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    new BCryptPasswordEncoder().encode(user.getPassword()), //encode password
                    user.isEnabled(),
                    accountNonExpired,
                    credentialsNonExpired,
                    accountNonLocked,
                    getAuthorities(user.getRoles()));


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    //get a list of all roles in database table
    private static List<GrantedAuthority> getAuthorities(Collection<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return authorities;
    }

}
