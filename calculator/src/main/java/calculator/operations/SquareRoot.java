package calculator.operations;

import calculator.CalculatorException;

import java.util.List;

/**
 * Created by Sebi on 05-Oct-17.
 */
public class SquareRoot implements IOperation {
    @Override
    public Double execute(List<Double> operands) throws CalculatorException {
        if (operands.size() != 1){
            throw new CalculatorException("Invalid number of operands");
        }
        return Math.sqrt(operands.get(0));
    }
}
