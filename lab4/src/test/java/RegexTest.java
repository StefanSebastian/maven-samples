import org.junit.Test;

/**
 * Created by Sebi on 14-Nov-17.
 */
public class RegexTest {
    @Test
    public void test() throws Exception {
        String str = "Laszlo~Florian~Jurj~2220222496428~Laszlo.Florian.Jurj@yahoo.com%alexandru~Alexandru~CHELARIU~610626489692~Alexandruoutlookcom";
        for (String pers : str.split("%")){
            System.out.println(pers);

            for (String attr : pers.split("~")){
                System.out.println(attr);
            }
        }
    }

    @Test
    public void convert() throws Exception {
        String str = "01";
        Integer conv = Integer.parseInt(str);
        System.out.println(conv);
    }
}
