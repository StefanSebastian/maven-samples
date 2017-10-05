package calculator.operations;

import calculator.CalculatorException;

import java.util.List;

/**
 * Created by Sebi on 03-Oct-17.
 */
public class Substraction implements IOperation {

    @Override
    public Double execute(List<Double> operands) throws CalculatorException {
        if (operands.size() != 2){
            throw new CalculatorException("Invalid arguments");
        }
        return operands.get(0) - operands.get(1);
    }
}
