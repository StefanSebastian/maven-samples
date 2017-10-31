import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by Sebi on 31-Oct-17.
 */
public class TestBigDecimals {


    public List<BigDecimal> generateBigDecimals() throws Exception {
        List<BigDecimal> bigDecimals = new LinkedList<>();

        Random random = new Random();

        for (int i = 0; i < 100000; i++){
            int nr = random.nextInt();
            if (nr < 0){
                nr = -nr;
            }
            bigDecimals.add(new BigDecimal(nr + "." + nr % 1000));
        }

        return bigDecimals;
    }

    @Test
    public void bigDecimalsSum() throws Exception {
        System.out.println("Big decimals sum");
        List<BigDecimal> bigDecimals = generateBigDecimals();
        Optional<BigDecimal> res = bigDecimals
                .stream()
                .reduce(BigDecimal::add);

        if (res.isPresent()){
            System.out.println(res.get());
        }

        BigDecimal resIt = BigDecimal.ZERO;
        for (BigDecimal bigDecimal : bigDecimals){
            resIt = resIt.add(bigDecimal);
        }

        assertThat(res.get(), is(resIt));

    }

    @Test
    public void bigDecimalsAverage() throws Exception{
        System.out.println("Big decimals test");
        List<BigDecimal> bigDecimals = generateBigDecimals();
        Optional<BigDecimal> res = bigDecimals
                .stream()
                .reduce(BigDecimal::add);

        BigDecimal resIt = BigDecimal.ZERO;
        int count = 0;
        for (BigDecimal bigDecimal : bigDecimals){
            resIt = resIt.add(bigDecimal);
            count++;
        }
        resIt = resIt.divide(new BigDecimal(count));

        System.out.println(resIt);

        assertThat(res.get().divide(new BigDecimal(bigDecimals.size())), is(resIt));
    }

    @Test
    public void getFirstTenPercent() throws Exception {
        System.out.println("Get first ten percent");
        List<BigDecimal> bigDecimals = generateBigDecimals();
        bigDecimals
                .stream()
                .sorted(Comparator.reverseOrder())
                .limit((long)(0.1 * bigDecimals.size()))
                .forEach(System.out::println);

    }
}
