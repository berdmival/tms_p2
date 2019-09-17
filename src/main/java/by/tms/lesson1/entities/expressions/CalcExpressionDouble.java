package by.tms.lesson1.entities.expressions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CalcExpressionDouble implements CalcExpression {
    public static void setDateTimeHistoryPattern(String dateTimeHistoryPattern) {
        CalcExpressionDouble.dateTimeHistoryPattern = dateTimeHistoryPattern;
    }

    private static String dateTimeHistoryPattern = "yyyy-MM-dd HH:mm:ss";

    private Double num1;
    private Double num2;
    private String action;
    private Double result;
    private String calcDateTime;

    public CalcExpressionDouble(Double num1, Double num2, String action) {
        this.num1 = num1;
        this.num2 = num2;
        this.action = action;
        this.calcDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateTimeHistoryPattern));
        this.result = calculate();
    }

    @Override
    public String resultToString() {
        return new StringBuilder()
                .append(this.calcDateTime).append(": ")
                .append("num1 = ").append(this.num1)
                .append(", num2 = ").append(this.num2)
                .append(", action: ").append(this.action)
                .append(", result = ").append(this.result)
                .toString();
    }

    public Double getNum1() {
        return num1;
    }

    public Double getNum2() {
        return num2;
    }

    public String getAction() {
        return action;
    }

    public Double getResult() {
        return result;
    }

    public String getCalcDateTime() {
        return calcDateTime;
    }

    @Override
    public Double calculate() {
        Double result;
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
