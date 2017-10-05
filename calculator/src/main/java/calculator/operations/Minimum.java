package calculator.operations;

import calculator.CalculatorException;

import java.util.Collections;
import java.util.List;

/**
 * Created by Sebi on 05-Oct-17.
 */
public class Minimum implements IOperation {
    @Override
    public Double execute(List<Double> operands) throws CalculatorException {
        if (operands.size() == 0){
            throw new CalculatorException("Invalid operands");
        }
        return Collections.min(operands);
    }
}
