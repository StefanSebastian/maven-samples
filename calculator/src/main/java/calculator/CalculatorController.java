package calculator;

import calculator.operations.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sebi on 03-Oct-17.
 */
public class CalculatorController {
    private Map<Operator, IOperation> operationMap;

    public CalculatorController(){
        operationMap = new HashMap<>();
        operationMap.put(Operator.ADD, new Addition());
        operationMap.put(Operator.DIVIDE, new Division());
        operationMap.put(Operator.MULTIPLY, new Multiply());
        operationMap.put(Operator.DIVIDE, new Division());
        operationMap.put(Operator.SUBSTRACT, new Substraction());
        operationMap.put(Operator.MAX, new Maximum());
        operationMap.put(Operator.MIN, new Minimum());
        operationMap.put(Operator.SQRT, new SquareRoot());
    }

    public Double doOperation(List<Double> arguments, Operator operation) throws CalculatorException{
        IOperation selectedOperation = operationMap.get(operation);
        return selectedOperation.execute(arguments);
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
