package by.tms.lesson1.html_fragmets;

public interface Templates {
    String HTML_HEADER = "<!DOCTYPE html>" +
            "<html>" +
            "<head>" +
            "<title>Calculator with history and authentication</title>" +
            "<style>" +
            "   .logout_button {" +
            "    border: 1px solid #333;" +
            "    display: inline-block;" +
            "    padding: 5px 15px;" +
            "    text-decoration: none;" +
            "    color: #000;" +
            "  }" +
            "   .logout_button:hover {" +
            "    box-shadow: 0 0 5px rgba(0,0,0,0.3);" +
            "    background: linear-gradient(to bottom, #fcfff4, #e9e9ce);" +
            "    color: #a00;" +
            "   }" +
            "  </style></head><body>";
    String HTML_FOOTER = "</body></html>";
    String CALC_REQUEST = "<H2>Enter request: http://yourserver:port/calc?num1=[value 1]&num2=[value 2]&action=[sum or diff or mult or div]</H2>";
    String REGISTER_REQUEST = "<H1>Please, enter your name, age and password in the request http://yourserver:port/index/reg?name=[yourName]&age=[yourAge]&password=[yourPassword]</H1>";
    String CURRENT_REQUEST_IS_INCORRECT = "<H3>Current request is incorrect!</H3>";
    String SESSIONS_MAP_IS_EMPTY = "Sessions map is empty<br>";
    String LINE = "<hr size=\"2\" color=\"black\">";
    String HISTORY_IS_EMPTY = "History is empty<br>";
    String HISTORY = "<H4>History:</H4>";
    String AUTH_FAIL = "<H1>Name or password is incorrect!</H1>";
    String LOGOUT_BUTTON = "<a href=\"/index/logout\" class=\"logout_button\"><b>Logout</b></a>";
    String NOW_YOU_CAN = "<H2>Now you can use the calculator and view the history of other users.</H2>";
}
