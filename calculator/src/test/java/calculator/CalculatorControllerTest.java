package calculator;

import calculator.operations.OPERATOR;
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
        Double res = calculatorController.doOperation(operands, OPERATOR.ADD);
        assertTrue(res == 3.0);
    }

    @Test
    public void subTest() throws Exception {
        List<Double> operands = new ArrayList<Double>(asList(8.0,2.0));
        Double res = calculatorController.doOperation(operands, OPERATOR.SUBSTRACT);
        assertTrue(res == 6.0);
    }

    @Test
    public void multiplyTest() throws Exception {
        List<Double> operands = new ArrayList<Double>(asList(8.0,10.0));
        Double res = calculatorController.doOperation(operands, OPERATOR.MULTIPLY);
        assertTrue(res == 80.0);
    }

    @Test
    public void divideTest() throws Exception {
        List<Double> operands = new ArrayList<Double>(asList(12.0,10.0));
        Double res = calculatorController.doOperation(operands, OPERATOR.DIVIDE);
        assertTrue(res == 1.2);
    }

    @Test
    public void minTest() throws Exception {
        List<Double> operands = new ArrayList<Double>(asList(12.0,10.0,5.6,4.3));
        Double res = calculatorController.doOperation(operands, OPERATOR.MIN);
        assertTrue(res == 4.3);
    }

    @Test
    public void maxTest() throws Exception {
        List<Double> operands = new ArrayList<Double>(asList(12.0,10.0,5.6,4.3));
        Double res = calculatorController.doOperation(operands, OPERATOR.MAX);
        assertTrue(res == 12.0);
    }

    @Test
    public void sqrtTest() throws Exception {
        List<Double> operands = new ArrayList<Double>(asList(16.0));
        Double res = calculatorController.doOperation(operands, OPERATOR.SQRT);
        assertTrue(res == 4.0);
    }

    @Test
    public void addTestString() throws Exception {
        String op = "1 + 4";
        Double res = calculatorController.doOperation(op);
        assertTrue(res == 5.0);
    }

    @Test
    public void substractTestString() throws Exception {
        String op = "3.5 - 2";
        Double res = calculatorController.doOperation(op);
        assertTrue(res == 1.5);
    }

    @Test
    public void minTestString() throws Exception {
        String op = "min 2 3 1 5";
        Double res = calculatorController.doOperation(op);
        assertTrue(res == 1.0);
    }

}