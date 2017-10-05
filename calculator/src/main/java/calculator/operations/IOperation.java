package calculator.operations;

import calculator.CalculatorException;

import java.util.List;

/**
 * Created by Sebi on 03-Oct-17.
 */
public interface IOperation {
    Double execute(List<Double> operands) throws CalculatorException;
}
