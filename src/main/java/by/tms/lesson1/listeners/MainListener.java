package by.tms.lesson1.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

@WebListener()
public class MainListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    public MainListener() {
    }

    public void contextInitialized(ServletContextEvent sce) {
      sce.getServletContext().setAttribute("users", new ArrayList<>());
      sce.getServletContext().setAttribute("sessions", new HashMap<>());
      sce.getServletContext().setAttribute("css_path", "/styles/mainCSS.css");
    }

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      if(sbe.getName().equals("user")) {
          sbe.getSession().setAttribute("history", new LinkedList<String>());
      }
    }

}
