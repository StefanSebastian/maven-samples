package calculator;

import calculator.operations.Operator;

import java.util.*;

/**
 * Created by Sebi on 05-Oct-17.
 */
public class CalculatorInputParser {

    // accepted operators
    private Map<String, Operator> operatorMap;

    public CalculatorInputParser(){
        operatorMap = new HashMap<String, Operator>();
        operatorMap.put("+", Operator.ADD);
        operatorMap.put("-", Operator.SUBSTRACT);
        operatorMap.put("/", Operator.DIVIDE);
        operatorMap.put("*", Operator.MULTIPLY);
        operatorMap.put("min", Operator.MIN);
        operatorMap.put("max", Operator.MAX);
        operatorMap.put("sqrt", Operator.SQRT);
    }

    // checks if a string can be converted to double
    private Boolean checkDouble(String token){
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // checks if a string is an operator
    private Boolean checkOperator(String token) {
        return operatorMap.keySet().contains(token) ? true : false;
    }

    public Boolean checkValidOperation(String operation) {
        Boolean valid = true;
        int operatorCount = 0;
        int position = 0; // to get operator's position

        String[] args = operation.split(" ");
        for (String arg : args){
            // after breaking the operation by spaces we should only have numbers and operators
            if (!(checkDouble(arg) || checkOperator(arg))){
                valid = false;
            }

            // max one operator
            if (checkOperator(arg)){
                operatorCount += 1;
                if (operatorCount > 1){
                    valid = false;
                }
            }

            // check operator position
            position++;
            if (checkOperator(arg)){
                if (1 != position && ("min".equals(arg) || "max".equals(arg) || "sqrt".equals(arg))){
                    valid = false;
                }
                if (2 != position && ("+".equals(arg) || "-".equals(arg) || "/".equals(arg) || "*".equals(arg))){
                    valid = false;
                }
            }

        }

        if (operatorCount == 0){
            valid = false;
        }

        return valid;
    }

    // returns the list of operands
    // validation should be done first
    public List<Double> getOperands(String operation) {
        List<Double> operands = new ArrayList<>();

        String[] args = operation.split(" ");
        for (String arg : args) {
            try {
                operands.add(Double.parseDouble(arg));
            } catch (NumberFormatException ex){
            }
        }

        return operands;
    }

    // returns the operator
    // validation should be done first
    public Operator getOperator(String operation) {
        String[] args = operation.split(" ");
        for (String arg : args) {
            if (checkOperator(arg)){
                return operatorMap.get(arg);
            }
        }
        return null;
    }
}
