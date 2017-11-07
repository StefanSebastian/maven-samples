import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Sebi on 07-Nov-17.
 */
public class Main {
    public static void main(String[] args) throws IOException {

        String path = "E:\\Info\\anu3\\princip\\git\\maven-samples\\lab3\\src\\main\\java\\serializeResult.txt";
        System.out.println(path);

        List<BigDecimal> bigDecimals = BigDecimalGenerator.getBigDecimalList(10);

        BigDecimalsSerializer.serializeBigDecimals(bigDecimals, path);

        List<BigDecimal> deserialized = BigDecimalsSerializer.deserializeBigDecimals(path);

        System.out.println(bigDecimals);
        System.out.println(deserialized);

    }
}
