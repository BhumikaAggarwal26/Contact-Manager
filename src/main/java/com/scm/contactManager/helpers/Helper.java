package com.scm.contactManager.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {

    public static String getEmailOfLoggedInUser(Authentication authentication) {

        Logger logger = LoggerFactory.getLogger(Helper.class);

        // If LoggedIn User signedUp from Google
        if (authentication instanceof OAuth2AuthenticationToken) {

            var oauth2User = (OAuth2User) authentication.getPrincipal();
            
            logger.info("Getting email from GOOGLE");

            String username = oauth2User.getAttribute("email").toString();

            return username;

        } else {  // If Registered from SELF

            logger.info("Getting email from SELF");
            return authentication.getName();
        }

    }
}
