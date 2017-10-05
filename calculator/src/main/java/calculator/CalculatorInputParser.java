package calculator;

import calculator.operations.OPERATOR;

import java.util.*;

/**
 * Created by Sebi on 05-Oct-17.
 */
public class CalculatorInputParser {

    // accepted operators
    private Map<String, OPERATOR> operatorMap;

    public CalculatorInputParser(){
        operatorMap = new HashMap<String, OPERATOR>();
        operatorMap.put("+", OPERATOR.ADD);
        operatorMap.put("-", OPERATOR.SUBSTRACT);
        operatorMap.put("/", OPERATOR.DIVIDE);
        operatorMap.put("*", OPERATOR.MULTIPLY);
        operatorMap.put("min", OPERATOR.MIN);
        operatorMap.put("max", OPERATOR.MAX);
        operatorMap.put("sqrt", OPERATOR.SQRT);
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
                if (1 != position && (arg.equals("min") || arg.equals("max") || arg.equals("sqrt"))){
                    valid = false;
                }
                if (2 != position && (arg.equals("+") || arg.equals("-") || arg.equals("/") || arg.equals("*"))){
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
    public OPERATOR getOperator(String operation) {
        String[] args = operation.split(" ");
        for (String arg : args) {
            if (checkOperator(arg)){
                return operatorMap.get(arg);
            }
        }
        return null;
    }
}
