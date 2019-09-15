package by.tms.lesson1.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CalcExpressionInt implements CalcExpression {
    private static final String DATE_TIME_HISTORY_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private Number num1;
    private Number num2;
    private String action;

    public CalcExpressionInt(Number num1, Number num2, String action) {
        this.num1 = num1;
        this.num2 = num2;
        this.action = action;
    }

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
    public Number calculate() {
        Number result;
        switch (this.action) {
            case "sum":
                result = this.num1.intValue() + this.num2.intValue();
                break;
            case "diff":
                result = this.num1.intValue() - this.num2.intValue();
                break;
            case "mult":
                result = this.num1.intValue() * this.num2.intValue();
                break;
            case "div":
                result = this.num1.intValue() / this.num2.intValue();
                break;
            default:
                return null;
        }

        return result;
    }
}
