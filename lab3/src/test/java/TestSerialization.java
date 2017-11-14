import org.junit.Test;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Sebi on 13-Nov-17.
 */
public class TestSerialization {
    @Test
    public void serialization() throws Exception {
        Path filePath = Paths.get("src/test/resources/serializeResults.txt");
        String path = filePath.toString();

        System.out.println(path);

        List<BigDecimal> bigDecimals = BigDecimalGenerator.getBigDecimalList(100000);

        BigDecimalsSerializer.serializeBigDecimals(bigDecimals, path);

        List<BigDecimal> deserialized = BigDecimalsSerializer.deserializeBigDecimals(path);

        assertThat(bigDecimals.size(), is(deserialized.size()));

        for (int i = 0; i < bigDecimals.size(); i++){
            assertThat(bigDecimals.get(i), is(deserialized.get(i)));
        }
    }
}
