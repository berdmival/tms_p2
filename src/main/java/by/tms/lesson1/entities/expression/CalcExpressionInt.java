package by.tms.lesson1.entities.expression;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CalcExpressionInt implements CalcExpression {
    private static final String DATE_TIME_HISTORY_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private Integer num1;
    private Integer num2;
    private String action;

    public CalcExpressionInt(Integer num1, Integer num2, String action) {
        this.num1 = num1;
        this.num2 = num2;
        this.action = action;
    }

    @Override
    public String resultToString() {
        return new StringBuilder()
                .append(LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_HISTORY_PATTERN))).append(": ")
                .append("num1 = ").append(num1)
                .append(", num2 = ").append(num2)
                .append(", action: ").append(action)
                .append(", result = ").append(calculate())
                .toString();
    }

    @Override
    public Integer calculate() {
        Integer result;
        switch (this.action) {
            case "sum":
                result = this.num1 + this.num2;
                break;
            case "diff":
                result = this.num1 - this.num2;
                break;
            case "mult":
                result = this.num1 * this.num2;
                break;
            case "div":
                result = this.num1 / this.num2;
                break;
            default:
                return null;
        }

        return result;
    }
}
