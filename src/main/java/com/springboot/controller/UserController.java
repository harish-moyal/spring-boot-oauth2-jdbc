package com.springboot.controller;


import com.springboot.entity.User;
import com.springboot.entity.UserAuthority;
import com.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository userDao;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    /*@Autowired
    private JdbcUserDetailsManager userDetailsManager; // Create a new service and move it there*/

    @RequestMapping("/hello")
    public String createNewUser(Principal user1) {
        return "Protected Resource";
    }

    @RequestMapping("/create")
    public void createUser(Principal user1) {
        User user = new User();
        user.setUsername("hmoyal@mobiquityinc.com");
        user.setPassword(bCryptPasswordEncoder.encode("password"));
        user.setEnabled(true);
        user.setAnniversaryDate(new Date());
        user.setDateOfBirth(new Date());
        user.setDateOfJoining(new Date());
        user.setDepartment("Java");
        user.setFirstName("Harish");
        user.setLastName("Moyal");
        GrantedAuthority simpleGrantedAuthority = new UserAuthority(user.getUsername(), "ROLE_USER", user);
        ((Collection<GrantedAuthority>)user.getAuthorities()).add(simpleGrantedAuthority);
        userDao.saveAndFlush(user);
    }

}
