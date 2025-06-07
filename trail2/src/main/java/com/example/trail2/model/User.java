//package com.example.trail2.model;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//
//@Entity
//@Table(name = "users")
//public class User {
//    @Id
//    private String username;
//    private String password;
//    private String roles; // Comma-separated roles, e.g., "ROLE_USER,ROLE_ADMIN"
//
//    // Constructors
//    public User() {}
//
//    public User(String username, String password, String roles) {
//        this.username = username;
//        this.password = password;
//        this.roles = roles;
//    }
//
//    // Getters and setters
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getRoles() {
//        return roles;
//    }
//
//    public void setRoles(String roles) {
//        this.roles = roles;
//    }
//}


package com.example.trail2.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

    @Id
    private String username;
    private String password;
    private String roles; // e.g., "ROLE_USER,ROLE_ADMIN"

    // Constructors
    public User() {}

    public User(String username, String password, String roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
