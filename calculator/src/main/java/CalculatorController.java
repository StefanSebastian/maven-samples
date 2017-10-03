import java.util.List;

/**
 * Created by Sebi on 03-Oct-17.
 */
public class CalculatorController {
    Double doOperation(List<Double> arguments, OPERATION operation) throws CalculatorException{
        if (operation == OPERATION.ADD){
            Addition addition = new Addition();
            return addition.execute(arguments);
        }
        else if (operation == OPERATION.SUBSTRACT){
            Substraction substraction = new Substraction();
            return substraction.execute(arguments);
        }
        else if (operation == OPERATION.MULTIPLY){
            Multiply multiply = new Multiply();
            return multiply.execute(arguments);
        }
        else if (operation == OPERATION.DIVIDE){
            Division division = new Division();
            return division.execute(arguments);
        } else {
            throw new CalculatorException("Invalid operation");
        }

    }
}
