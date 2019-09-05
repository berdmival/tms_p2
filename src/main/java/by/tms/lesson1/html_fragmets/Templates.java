package by.tms.lesson1.html_fragmets;

public interface Templates {
    String REGISTER_REQUEST = "<H1>Please, enter your name and age in the request /index/auth?name=[yourName]&age=[yourAge]</H1>";
    String HTML_HEADER = "<!DOCTYPE html><html><head><title>Calculator with history</title></head><body>";
    String HTML_FOOTER = "</body></html>";
    String CALC_REQUEST = "<H2>Enter request: http://yourserver:port/calc?num1=[value 1]&num2=[value 2]&action=[sum or diff or mult or div]</H2>";
    String CURRENT_REQUEST_IS_INCORRECT = "<H3>Current request is incorrect!</H3>";
    String SESSIONS_MAP_IS_EMPTY = "Sessions map is empty<br>";
    String LINE = "_____________________________________________________________________________________________________<br><br>";
    String HISTORY_IS_EMPTY = "History is empty<br>";
    String HISTORY = "<H4>History:</H4>";
}
