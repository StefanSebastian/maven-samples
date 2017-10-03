import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Sebi on 03-Oct-17.
 */
public class CalculatorControllerTest {
    private CalculatorController calculatorController = new CalculatorController();
    @Test
    public void addTest() throws Exception {
        List<Double> operands = new ArrayList<Double>(asList(1.0,2.0));
        Double res = calculatorController.doOperation(operands, OPERATION.ADD);
        assertTrue(res == 3.0);
    }

    @Test
    public void subTest() throws Exception {
        List<Double> operands = new ArrayList<Double>(asList(8.0,2.0));
        Double res = calculatorController.doOperation(operands, OPERATION.SUBSTRACT);
        assertTrue(res == 6.0);
    }

}