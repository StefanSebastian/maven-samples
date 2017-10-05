package calculator;

/**
 * Created by Sebi on 03-Oct-17.
 */
public class Main {
    public static void main(String[] args) {
        UI ui = new UI(new CalculatorController());
        ui.run();
    }
}
