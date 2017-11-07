import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Sebi on 07-Nov-17.
 */
public class Test2 {
    @Test
    public void add(){
        List<BigDecimal> bigDecimals = BigDecimalGenerator.getBigDecimalList(10000);

        BigDecimal resIt = BigDecimal.ZERO;
        for (BigDecimal bigDecimal : bigDecimals){
            resIt = resIt.add(bigDecimal);
        }

        BigDecimal res = BigDecimalOperations.sumOfBigDecimals(bigDecimals);

        assertThat(res, is(resIt));
    }

    @Test
    public void avg(){
        List<BigDecimal> bigDecimals = BigDecimalGenerator.getBigDecimalList(10000);

        BigDecimal resIt = BigDecimal.ZERO;
        int count = 0;
        for (BigDecimal bigDecimal : bigDecimals){
            resIt = resIt.add(bigDecimal);
            count++;
        }
        resIt = resIt.divide(new BigDecimal(count));

        BigDecimal res = BigDecimalOperations.averageOfBigDecimals(bigDecimals);

        assertThat(res, is(resIt));
    }

    @Test
    public void topPercent() {
        List<BigDecimal> bigDecimals = BigDecimalGenerator.getBigDecimalList(10000);

        List<BigDecimal> streams = BigDecimalOperations.getTheBiggest(bigDecimals);

        // non streams
        List<BigDecimal> nonStream = new ArrayList<>();
        bigDecimals.forEach(x -> nonStream.add(new BigDecimal(x.toString())));
        nonStream.sort(Comparator.reverseOrder());
        List<BigDecimal> top = new ArrayList<>();
        int dim = (int) (0.1 * bigDecimals.size());
        top = nonStream.subList(0, dim);

        for (int i = 0; i < dim; i++){
            assertThat(streams.get(i), is(top.get(i)));
        }

    }
}
