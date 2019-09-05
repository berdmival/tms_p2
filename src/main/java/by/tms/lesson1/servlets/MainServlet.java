package by.tms.lesson1.servlets;

import by.tms.lesson1.users.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/index")
public class MainServlet extends HttpServlet {

    public static final String REGISTER_REQUEST = "<H1>Please, enter your name and age in the request /index/auth?name=[yourName]&age=[yourAge]</H1>";
    public static final String HTML_HEADER = "<!DOCTYPE html><html><head><title>Calculator with history</title></head><body>";
    public static final String HTML_FOOTER = "</body></html>";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(HTML_HEADER);

        HttpSession currentSession = req.getSession();

        if (currentSession.isNew()) {
            resp.getWriter().println(REGISTER_REQUEST);
        } else {
            User currentUser = (User) currentSession.getAttribute("user");
            if (currentUser != null) {
                resp.getWriter().println("<H1>Hello, " + currentUser.getName() + "</H1>");
                printCurrentSessionFromMapOfSessions(resp);
            } else {
                resp.getWriter().println(REGISTER_REQUEST);
            }
        }

        resp.getWriter().println(HTML_FOOTER);

    }

    private void printCurrentSessionFromMapOfSessions(HttpServletResponse resp) throws IOException {
        Map<String, HttpSession> listOfSessions = (Map<String, HttpSession>) getServletContext().getAttribute("sessions");
        if (listOfSessions != null) {
            listOfSessions.forEach((id, session) -> {
                try {
                    resp.getWriter().println("<H3>Session with id " + id + ": "
                            + ((User)(session.getAttribute("user"))).toString() + "</H3>");

                    printHistory(resp, session);

                    resp.getWriter().println("__________________________________________________________________________<br><br>");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } else {
            resp.getWriter().println("Sessions map is empty<br>");
        }
    }

    public static void printHistory(HttpServletResponse resp, HttpSession session) throws IOException {
        List<String> historyList = (List<String>) session.getAttribute("history");

        resp.getWriter().println("<H4>History:</H4>");

        if ((historyList != null) & (historyList.size() > 0)) {
            historyList.forEach(history -> {
                try {
                    resp.getWriter().println(history + "<br>");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } else {
            resp.getWriter().println("History is empty<br>");
        }
    }
}
