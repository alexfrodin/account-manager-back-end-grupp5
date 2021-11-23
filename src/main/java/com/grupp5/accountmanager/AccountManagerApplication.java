package com.grupp5.accountmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication()
public class AccountManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountManagerApplication.class, args);
    }

}
