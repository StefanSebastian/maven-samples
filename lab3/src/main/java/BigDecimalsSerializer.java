import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebi on 07-Nov-17.
 */
public class BigDecimalsSerializer {
    public static void serializeBigDecimals(List<BigDecimal> bigDecimals, String path){

        try(FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream)){
            for (BigDecimal bigDecimal : bigDecimals){
                outputStream.writeObject(bigDecimal);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<BigDecimal> deserializeBigDecimals(String path){
        List<BigDecimal> bigDecimals = new ArrayList<>();

        try(FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream)){
                while(true){
                    bigDecimals.add((BigDecimal) inputStream.readObject());
                }
        } catch (EOFException e){

        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return bigDecimals;
    }
}
