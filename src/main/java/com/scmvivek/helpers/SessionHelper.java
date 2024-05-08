package com.scmvivek.helpers;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;

@Component
public class SessionHelper {
    public static void removeMessage(){
        try{
            System.out.println("Removing message from session");
            HttpSession session = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();// when we get HttSesion then only we can remove session or Object
            session.removeAttribute("message");
        }catch(Exception e){
            System.out.println("Session is not present: " + e);
            e.printStackTrace();
        }
    }
    
}
