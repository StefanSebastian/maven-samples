import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Sebi on 07-Nov-17.
 */
public class BigDecimalGenerator {
    public static List<BigDecimal> getBigDecimalList(int size){
        List<BigDecimal> list = new LinkedList<>();

        Random random = new Random();

        for (int i = 0; i < size; i++){
            int nr = random.nextInt();
            if (nr < 0){
                nr = -nr;
            }

            list.add(new BigDecimal(nr + "." + nr % 100));
        }

        return list;
    }
}
