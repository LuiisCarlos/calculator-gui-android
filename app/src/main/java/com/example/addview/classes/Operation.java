package com.example.addview.classes;

public class Operation {
    private Double x;
    private Double y;
    private Character operator;

    public Operation() { }

    public Operation(Double x, Double y, Character operator) {
        this.x = x;
        this.y = y;
        this.operator = operator;
    }

    public Double getX() { return x; }
    public void setX(Double x) { this.x = x; }

    public Double getY() { return y; }
    public void setY(Double y) { this.y = y; }

    public Character getOperator() { return operator; }
    public void setOperator(Character operation) { this.operator = operation; }

    public String resolve() {
        double result = 0.0;
        switch (operator) {
            case '+':
                result = x + y;
                break;
            case '-':
                result = x - y;
                break;
            case 'x':
                result = x * y;
                break;
            case '/':
                result = x / y;
                break;
        }

        return removeZero(result);
    }

    private String removeZero(Double number) {
        return number % 1 == 0 ? String.valueOf(number.intValue()) : number.toString();
    }
}

