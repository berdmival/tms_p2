package by.tms.lesson1.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@WebListener()
public class MainListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    public MainListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("users", new ArrayList<>());
        sce.getServletContext().setAttribute("sessions", new HashMap<>());
        sce.getServletContext().setAttribute("css_path", "/styles/mainCSS.css");
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        if(sbe.getName().equals("user")) {
            sbe.getSession().setAttribute("history", new LinkedList<String>());
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ((Map<String, HttpSession>) se.getSession().getServletContext().getAttribute("sessions")).remove(se.getSession().getId());
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
