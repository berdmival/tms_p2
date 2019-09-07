package by.tms.lesson1.html_fragmets;

public interface Templates {
    String REGISTER_REQUEST = "<H1>Please, enter your name, age and password in the request http://yourserver:port/index/reg?name=[yourName]&age=[yourAge]&password=[yourPassword]</H1>";
    String HTML_HEADER = "<!DOCTYPE html><html><head><title>Calculator with history and authentication</title></head><body>";
    String HTML_FOOTER = "</body></html>";
    String CALC_REQUEST = "<H2>Enter request: http://yourserver:port/calc?num1=[value 1]&num2=[value 2]&action=[sum or diff or mult or div]</H2>";
    String CURRENT_REQUEST_IS_INCORRECT = "<H3>Current request is incorrect!</H3>";
    String SESSIONS_MAP_IS_EMPTY = "Sessions map is empty<br>";
    String LINE = "_____________________________________________________________________________________________________<br><br>";
    String HISTORY_IS_EMPTY = "History is empty<br>";
    String HISTORY = "<H4>History:</H4>";
    String AUTH_FAIL = "<H1>Name or password is incorrect!</H1>";
}
