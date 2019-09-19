package by.tms.lesson1.listeners;

import by.tms.lesson1.entities.user.User;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;

@WebListener()
public class MainListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    public MainListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("users", new ArrayList<>());
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ((User) se.getSession().getAttribute("user")).logout();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {

    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }

}
