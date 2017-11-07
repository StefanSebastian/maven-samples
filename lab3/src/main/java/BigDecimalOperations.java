import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Sebi on 07-Nov-17.
 */
public class BigDecimalOperations {
    public static BigDecimal sumOfBigDecimals(List<BigDecimal> numbers){
        Optional<BigDecimal> res = numbers
                .stream()
                .reduce(BigDecimal::add);
        return res.orElse(null);
    }

    public static BigDecimal averageOfBigDecimals(List<BigDecimal> numbers){
        Optional<BigDecimal> res = numbers
                .stream()
                .reduce(BigDecimal::add);
        return res.isPresent() ? res.get().divide(new BigDecimal(numbers.size())) : null;
    }

    public static List<BigDecimal> getTheBiggest(List<BigDecimal> numbers){
        return numbers
                .stream()
                .sorted(Comparator.reverseOrder())
                .limit((long)(0.1 * numbers.size()))
                .collect(Collectors.toList());
    }
}
