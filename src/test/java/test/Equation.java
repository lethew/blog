package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Equation {
    private Integer left;
    private Equation leftEquation;
    private Operator operator;
    private Integer right;
    private Equation rightEquation;
    private Integer result;

    private Equation(Integer left, Equation leftEquation, Operator operator, Integer right, Equation rightEquation, Integer result) {
        this.left = left;
        this.leftEquation = leftEquation;
        this.operator = operator;
        this.right = right;
        this.rightEquation = rightEquation;
        this.result = result;
    }

    private Equation copy(){
        return new Equation(left, leftEquation, operator, right, rightEquation, result);
    }

    public static Equation of(int left, Operator operator, int right){
        Optional<Integer> compute = operator.compute(left, right);
        return compute.map(integer -> new Equation(left, null, operator, right, null, integer)).orElse(null);
    }
    public static Equation of(Equation left, Operator operator, int right){
        Optional<Integer> compute = operator.compute(left.result, right);
        return compute.map(integer -> new Equation(left.result, left, operator, right, null, integer)).orElse(null);
    }
    public static Equation of(int left, Operator operator, Equation right){
        Optional<Integer> compute = operator.compute(left, right.result);
        return compute.map(integer -> new Equation(left, null, operator, right.result, right, integer)).orElse(null);
    }
    public static Equation of(Equation left, Operator operator, Equation right){
        Optional<Integer> compute = operator.compute(left.result, right.result);
        return compute.map(integer -> new Equation(left.result, left, operator, right.result, right, integer)).orElse(null);
    }

    public Integer getLeft() {
        return left;
    }

    public void setLeft(Integer left) {
        this.left = left;
    }

    public Equation getLeftEquation() {
        return leftEquation;
    }

    public void setLeftEquation(Equation leftEquation) {
        this.leftEquation = leftEquation;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Integer getRight() {
        return right;
    }

    public void setRight(Integer right) {
        this.right = right;
    }

    public Equation getRightEquation() {
        return rightEquation;
    }

    public void setRightEquation(Equation rightEquation) {
        this.rightEquation = rightEquation;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if(null != leftEquation) {
            builder.append(leftEquation.toString(operator, false));
        } else {
            builder.append(left);
        }

        builder.append(operator.getSymbol());

        if(null != rightEquation) {
            builder.append(rightEquation.toString(operator, true));
        } else {
            builder.append(right);
        }
        return builder.toString();
    }

    private String toString(Operator operator, boolean isRight) {
        Equation copy = copy();
        // 双减号变号
        if(isRight && operator == Operator.minus && copy.getOperator() == Operator.minus) {
            copy.setOperator(Operator.plus);
        }

        StringBuilder builder = new StringBuilder();
        if(copy.getOperator().getLevel() < operator.getLevel()) {
            builder.append('(');
        }
        builder.append(copy.toString());
        if(copy.getOperator().getLevel() < operator.getLevel()) {
            builder.append(')');
        }
        return builder.toString();
    }

    public List<Integer> getItem(){
        List<Integer> integers = new ArrayList<>();

        if(leftEquation == null) {
            integers.add(left);
        } else {
            integers.addAll(leftEquation.getItem());
        }

        if(rightEquation == null) {
            integers.add(right);
        } else {
            integers.addAll(rightEquation.getItem());
        }

        return integers;
    }
}
