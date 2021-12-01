package com.grupp5.accountmanager.services;

import com.grupp5.accountmanager.dao.UserDao;
import com.grupp5.accountmanager.exceptions.EntityNotFoundException;
import com.grupp5.accountmanager.exceptions.NonUniqueResultException;
import com.grupp5.accountmanager.models.UserM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserM> user = userDao.findByUserEmail(email);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found in the database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>(); // TODO: Fix roles in DB
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("admin"); // Now all goes as admin
        authorities.add(simpleGrantedAuthority);

        // Prepared code for roles:
        // user.getRoles().forEach(role -> { authorities.add(new SimpleGrantedAuthority(role)) });

        return new org.springframework.security.core.userdetails.User(user.get().getUserEmail(), user.get().getHashedPassword(), authorities);
    }


    @Override
    public List<UserM> getAllUsers() {
        List<UserM> userMList = new LinkedList<>();
        userDao.findAll().forEach(userMList::add);
        System.out.println(userMList);
        return userMList;
    }

    @Override
    public UserM getUserById(Long id) {
        return userDao.findById(id).orElseThrow();
    }

    @Override
    public UserM createUser(UserM user) {
        Optional<UserM> userM = userDao.findByUserEmail(user.getUserEmail());
        if (userM.isPresent()) {
            throw new NonUniqueResultException(user.getId());
        }
        String password = user.getHashedPassword();
        user.setHashedPassword(passwordEncoder.encode(password));
        userDao.save(user);
        return user;
    }

    @Override
    public UserM updateUser(Long id, UserM user) {

        Optional<UserM> userM = userDao.findById(id);

        if (!userM.isPresent()) {
            throw new EntityNotFoundException(user.getId());
        }

        return userDao.save(user);
    }

    @Override
    public UserM deleteUser(Long id) {
        Optional<UserM> userM = userDao.findById(id);
        if (!userM.isPresent()) {
            throw new EntityNotFoundException(id);
        }
        userDao.deleteById(id);
        return userM.get();
    }
}
