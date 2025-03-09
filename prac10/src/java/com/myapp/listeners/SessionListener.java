package com.myapp.listeners;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import jakarta.servlet.ServletContext;

@WebListener
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        ServletContext context = event.getSession().getServletContext();
        Integer activeUsers = (Integer) context.getAttribute("activeUsers");

        if (activeUsers != null && activeUsers > 0) {
            context.setAttribute("activeUsers", activeUsers - 1);
        }
    }
    
    @Override

    public void sessionCreated(HttpSessionEvent event) {
        // No action needed on session creation
    }
}
