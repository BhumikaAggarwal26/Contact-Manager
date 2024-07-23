package com.scm.contactManager.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;

@Component
public class SessionHelper {

    private static Logger logger = LoggerFactory.getLogger(SessionHelper.class);

    public static void removeMessage(){
        try{
                HttpSession session = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
                session.removeAttribute("message");
        } catch(Exception e) {
                logger.info("Error while removing Sign up message:" +e);
                e.printStackTrace();
        }
    }
}
