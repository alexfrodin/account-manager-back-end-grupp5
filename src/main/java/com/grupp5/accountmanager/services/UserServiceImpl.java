package com.grupp5.accountmanager.services;

import com.grupp5.accountmanager.dao.UserDao;
import com.grupp5.accountmanager.models.UserM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserM user = userDao.findByUserEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException("User not found in the database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>(); // TODO: Fix roles in DB
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("admin"); // Now all goes as admin
        authorities.add(simpleGrantedAuthority);

        // Prepared code for roles:
        // user.getRoles().forEach(role -> { authorities.add(new SimpleGrantedAuthority(role)) });

        return new org.springframework.security.core.userdetails.User(user.getUserEmail(), user.getHashedPassword(), authorities);
    }

    @Override
    public UserM getUserById(Long id) {
        return userDao.findById(id).orElseThrow();
    }

    @Override
    public UserM createUser(UserM user) {
        String password = user.getHashedPassword();
        user.setHashedPassword(passwordEncoder.encode(password));
        userDao.save(user);
        return user;
    }
}
