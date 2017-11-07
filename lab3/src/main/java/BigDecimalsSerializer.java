import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sebi on 07-Nov-17.
 */
public class BigDecimalsSerializer {
    public static void serializeBigDecimals(List<BigDecimal> bigDecimals, String path){

        try(FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream)){

            int progress = 1;
            for (BigDecimal bigDecimal : bigDecimals){
                outputStream.writeObject(bigDecimal);
                System.out.println("Serialized " + progress);
                progress += 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<BigDecimal> deserializeBigDecimals(String path){
        List<BigDecimal> bigDecimals = new LinkedList<>();

        try(FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream)){

            int progress = 1;
            while(true){
                bigDecimals.add((BigDecimal) inputStream.readObject());
                System.out.println("Deserialized " + progress);
                progress += 1;
            }
        } catch (EOFException e){

        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return bigDecimals;
    }
}
