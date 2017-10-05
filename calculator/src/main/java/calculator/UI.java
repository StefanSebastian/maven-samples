package calculator;

import java.util.Scanner;

/**
 * Created by Sebi on 03-Oct-17.
 */
public class UI {
    private CalculatorController calculatorController;

    public UI(CalculatorController calculatorController){
        this.calculatorController = calculatorController;
    }


    String readInput(){
        Scanner scan = new Scanner(System.in);
        System.out.println("-- ");
        return scan.nextLine();
    }

    void run(){
        while (true) {
            String operation = readInput();
            try {
                Double res = calculatorController.doOperation(operation);
                System.out.println("Result is " + res);
            } catch (CalculatorException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}
