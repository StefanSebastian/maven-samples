package calculator;

import calculator.operations.*;

import java.util.List;

/**
 * Created by Sebi on 03-Oct-17.
 */
public class CalculatorController {
    public Double doOperation(List<Double> arguments, OPERATOR operation) throws CalculatorException{
        if (operation == OPERATOR.ADD){
            Addition addition = new Addition();
            return addition.execute(arguments);
        }
        else if (operation == OPERATOR.SUBSTRACT){
            Substraction substraction = new Substraction();
            return substraction.execute(arguments);
        }
        else if (operation == OPERATOR.MULTIPLY){
            Multiply multiply = new Multiply();
            return multiply.execute(arguments);
        }
        else if (operation == OPERATOR.DIVIDE){
            Division division = new Division();
            return division.execute(arguments);
        }
        else if (operation == OPERATOR.MIN){
            Minimum minimum = new Minimum();
            return minimum.execute(arguments);
        }
        else if (operation == OPERATOR.MAX){
            Maximum maximum = new Maximum();
            return maximum.execute(arguments);
        }
        else if (operation == OPERATOR.SQRT){
            SquareRoot squareRoot = new SquareRoot();
            return squareRoot.execute(arguments);
        }
        else {
            throw new CalculatorException("Invalid operation");
        }
    }

    public Double doOperation(String operation) throws CalculatorException {
        CalculatorInputParser calculatorInputParser = new CalculatorInputParser();
        if (!calculatorInputParser.checkValidOperation(operation)){
            throw new CalculatorException("Invalid operation");
        }

        return doOperation(calculatorInputParser.getOperands(operation),
                calculatorInputParser.getOperator(operation));
    }
}
