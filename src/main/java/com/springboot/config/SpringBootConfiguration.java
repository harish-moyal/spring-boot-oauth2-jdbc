package com.springboot.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.servlet.Filter;
import javax.sql.DataSource;

@SpringBootApplication
@EnableResourceServer
@EntityScan(basePackages = "com.springboot.entity")
@EnableJpaRepositories(basePackages = "com.springboot.repository")
@ComponentScan(basePackages = {"com.springboot.security", "com.springboot.service", "com.springboot.controller"})
public class SpringBootConfiguration {

    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootConfiguration.class, args);
    }

    @Bean
    public JdbcTokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    /*@Bean
    public FilterRegistrationBean filterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(openEntityManagerInViewFilter());
        registration.addUrlPatterns("*//*");

        return registration;
    }

    @Bean
    public Filter openEntityManagerInViewFilter() {
        return new OpenEntityManagerInViewFilter();
    }*/

    /*
    @Bean
    public JdbcUserDetailsManager userDetailsManager() {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager();
        manager.setDataSource(dataSource);

        manager.setRolePrefix("ROLE_");
        return manager;
    }

    @Component
    public class DataLoader {

        private UserRepository userRepository;

        private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        @Autowired
        public DataLoader(UserRepository userRepository) {
            this.userRepository = userRepository;
            loadUsers();
        }

        private void loadUsers() {
            CustomUserDetails customUserDetails = new CustomUserDetails();
            customUserDetails.setUsername("harish");
            customUserDetails.setPassword(bCryptPasswordEncoder.encode("password"));
            customUserDetails.setEnabled(true);
            userRepository.save(customUserDetails);
        }
    }*/



}