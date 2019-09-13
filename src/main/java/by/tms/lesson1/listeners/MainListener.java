package by.tms.lesson1.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.List;

@WebListener()
public class MainListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    public MainListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("users", new ArrayList<>());
        sce.getServletContext().setAttribute("sessions", new ArrayList<>());
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        if (sbe.getName().equals("user")) {
            sbe.getSession().setAttribute("history", new ArrayList<String>());
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ((List<HttpSession>) se.getSession().getServletContext().getAttribute("sessions")).remove(se.getSession());
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
