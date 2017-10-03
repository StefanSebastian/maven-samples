import java.util.Scanner;

/**
 * Created by Sebi on 03-Oct-17.
 */
public class UI {
    private CalculatorController calculatorController;

    public UI(CalculatorController calculatorController){
        this.calculatorController = calculatorController;
    }

    void printMenu(){
        System.out.println("1 - add");
        System.out.println("2 - substract");
        System.out.println("3 - exit");
    }

    Double readNumber(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the value");
        return scan.nextDouble();
    }

    void run(){
        while (true) {
            printMenu();
            Double opt = readNumber();
            if (opt == 1){
                Double op1 = readNumber();
                Double op2 = readNumber();
                //System.out.println(calculatorController.doOperation(new ArrayList<>(asList(op1, op2)), OPERATION.ADD));
            } else if (opt == 2){

            } else {
                break;
            }

        }
    }
}
