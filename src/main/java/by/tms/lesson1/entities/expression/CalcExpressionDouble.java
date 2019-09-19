package by.tms.lesson1.entities.expression;

import by.tms.lesson1.action.ActionTypeEnum;
import by.tms.lesson1.util.Validator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CalcExpressionDouble implements CalcExpression {
    public static void setDateTimeHistoryPattern(String dateTimeHistoryPattern) {
        CalcExpressionDouble.dateTimeHistoryPattern = dateTimeHistoryPattern;
    }

    private static String dateTimeHistoryPattern = "yyyy-MM-dd HH:mm:ss";

    private Double num1;
    private Double num2;
    private ActionTypeEnum actionType;
    private Double result;
    private String calcDateTime;

    public CalcExpressionDouble(Double num1, Double num2, ActionTypeEnum actionType) {
        this.num1 = num1;
        this.num2 = num2;
        this.actionType = actionType;
        this.calcDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(CalcExpressionDouble.dateTimeHistoryPattern));
        this.result = calculate();
    }

    public Double getNum1() {
        return num1;
    }

    public Double getNum2() {
        return num2;
    }

    public ActionTypeEnum getAction() {
        return actionType;
    }

    public String getCalcDateTime() {
        return calcDateTime;
    }

    public Double getResult() {
        return result;
    }

    @Override
    public Double calculate() {
        Double result;
        switch (this.actionType) {
            case SUM:
                result = sum(this.num1, this.num2);
                break;

            case DIFF:
                result = diff(this.num1, this.num2);
                break;

            case MULT:
                result = mult(this.num1, this.num2);
                break;

            case DIV:
                if (Validator.isValidExpression(this.num1, this.num2, this.actionType)) {
                    result = div(this.num1, this.num2);
                } else {
                    result = null;
                }
                break;

            default:
                result = null;
        }

        return result;
    }

    private Double sum(Double num1, Double num2) {
        return num1 + num2;
    }

    private Double diff(Double num1, Double num2) {
        return num1 - num2;
    }

    private Double mult(Double num1, Double num2) {
        return num1 * num2;
    }

    private Double div(Double num1, Double num2) {
        return num1 / num2;
    }
}
