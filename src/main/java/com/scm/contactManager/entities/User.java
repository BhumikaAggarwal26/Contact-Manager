package com.scm.contactManager.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {
    
    private String userId;
    private String name;
    private String email;
    private String password;
    private String about;
    private String phoneNumber;
    private String profilePic;

    private boolean enabled=false;
    private boolean emailVerified=false;
    private boolean phoneVerified=false;

    private Providers provider = Providers.SELF;
    private String providerUserId;
}
