package calculator;

import calculator.operations.Operator;
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
        Double res = calculatorController.doOperation(operands, Operator.ADD);
        assertTrue(res == 3.0);
    }

    @Test
    public void subTest() throws Exception {
        List<Double> operands = new ArrayList<Double>(asList(8.0,2.0));
        Double res = calculatorController.doOperation(operands, Operator.SUBSTRACT);
        assertTrue(res == 6.0);
    }

    @Test
    public void multiplyTest() throws Exception {
        List<Double> operands = new ArrayList<Double>(asList(8.0,10.0));
        Double res = calculatorController.doOperation(operands, Operator.MULTIPLY);
        assertTrue(res == 80.0);
    }

    @Test
    public void divideTest() throws Exception {
        List<Double> operands = new ArrayList<Double>(asList(12.0,10.0));
        Double res = calculatorController.doOperation(operands, Operator.DIVIDE);
        assertTrue(res == 1.2);
    }

    @Test
    public void minTest() throws Exception {
        List<Double> operands = new ArrayList<Double>(asList(12.0,10.0,5.6,4.3));
        Double res = calculatorController.doOperation(operands, Operator.MIN);
        assertTrue(res == 4.3);
    }

    @Test
    public void maxTest() throws Exception {
        List<Double> operands = new ArrayList<Double>(asList(12.0,10.0,5.6,4.3));
        Double res = calculatorController.doOperation(operands, Operator.MAX);
        assertTrue(res == 12.0);
    }

    @Test
    public void sqrtTest() throws Exception {
        List<Double> operands = new ArrayList<Double>(asList(16.0));
        Double res = calculatorController.doOperation(operands, Operator.SQRT);
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

    @Test
    public void invalidString() throws Exception {
        try {
            String op = "1 min 2";
            calculatorController.doOperation(op);
            assertTrue(false);
        } catch (CalculatorException ex){
            assertTrue(true);
        }
    }

    @Test
    public void invalidString2() throws Exception {
        try {
            String op = "+ 1 2";
            calculatorController.doOperation(op);
            assertTrue(false);
        } catch (CalculatorException ex){
            assertTrue(true);
        }
    }

    @Test
    public void invalidString3() throws Exception {
        try {
            String op = "1 + 2 3";
            calculatorController.doOperation(op);
            assertTrue(false);
        } catch (CalculatorException ex){
            assertTrue(true);
        }
    }

    @Test
    public void invalidString4() throws Exception {
        try {
            String op = "1 + 2 min";
            calculatorController.doOperation(op);
            assertTrue(false);
        } catch (CalculatorException ex){
            assertTrue(true);
        }
    }

    @Test
    public void parserOperator() throws Exception {
        String op = "1 + 2";
        CalculatorInputParser calculatorInputParser = new CalculatorInputParser();
        Operator operator = calculatorInputParser.getOperator(op);
        assertTrue(operator == Operator.ADD);
    }

    @Test
    public void parserOperator2() throws Exception {
        String op = "min 1 2";
        CalculatorInputParser calculatorInputParser = new CalculatorInputParser();
        Operator operator = calculatorInputParser.getOperator(op);
        assertTrue(operator == Operator.MIN);
    }

    @Test
    public void parserOperands() throws Exception {
        String op = "1 + 2";
        CalculatorInputParser calculatorInputParser = new CalculatorInputParser();
        List<Double> operands = calculatorInputParser.getOperands(op);
        assertTrue(operands.get(0) == 1);
        assertTrue(operands.get(1) == 2);
    }

    @Test
    public void parserOperands2() throws Exception {
        String op = "min 1 2 3 4";
        CalculatorInputParser calculatorInputParser = new CalculatorInputParser();
        List<Double> operands = calculatorInputParser.getOperands(op);
        assertTrue(operands.size() == 4);
    }

    @Test
    public void parserValidCheck() throws Exception {
        String op = "1 + 2";
        CalculatorInputParser calculatorInputParser = new CalculatorInputParser();
        Boolean valid = calculatorInputParser.checkValidOperation(op);
        assertTrue(valid);
    }

    @Test
    public void parserValidCheck2() throws Exception {
        String op = "1 + 2 -";
        CalculatorInputParser calculatorInputParser = new CalculatorInputParser();
        Boolean valid = calculatorInputParser.checkValidOperation(op);
        assertTrue(!valid);
    }
}