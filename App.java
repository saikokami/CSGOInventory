import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class App {

    /*
     * TODO - Get Json Data - handle json into something i can work with - calc inv
     * value -> Save inv or maybe import savestate?
     * 
     */
    public static void main(String[] args) throws Exception {
        // get file function
        String[][] price = new String[10][3];
        double sum = 0.0;
        System.out.println("Fetching case prices....");
        curl("http://csgobackpack.net/api/GetItemPrice/?currency=EUR&id=Danger%20Zone%20Case&time=7&icon=1", "danger");
        price[0][0] = "Danger Zone Case: ";
        price[0][2] = "1302";
        curl("http://csgobackpack.net/api/GetItemPrice/?currency=EUR&id=Spectrum%202%20Case&time=7&icon=1",
                "spectrum2");
        price[1][0] = "Spektrum 2 Case: ";
        price[1][2] = "1004";
        curl("http://csgobackpack.net/api/GetItemPrice/?currency=EUR&id=Gamma%202%20Case&time=7&icon=1", "gamma2");
        price[2][0] = "Gamma 2 Case: ";
        price[2][2] = "1001";
        curl("http://csgobackpack.net/api/GetItemPrice/?currency=EUR&id=Chroma%203%20Case&time=7&icon=1", "chroma3");
        price[3][0] = "Chroma 3 Case: ";
        price[3][2] = "513";
        curl("http://csgobackpack.net/api/GetItemPrice/?currency=EUR&id=Operation%20Phoenix%20Weapon%20Case&time=7&icon=1",
                "phoenix");
        price[4][0] = "Operation Phoenix Case: ";
        price[4][2] = "168";
        curl("http://csgobackpack.net/api/GetItemPrice/?currency=EUR&id=Glove%20Case&time=7&icon=1", "glove");
        price[5][0] = "Glove Case: ";
        price[5][2] = "136";
        curl("http://csgobackpack.net/api/GetItemPrice/?currency=EUR&id=Operation%20Vanguard%20Weapon%20Case&time=7&icon=1",
                "vanguard");
        price[6][0] = "Operation Vanguard Case: ";
        price[6][2] = "124";
        curl("http://csgobackpack.net/api/GetItemPrice/?currency=EUR&id=Shattered%20Web%20Case&time=7&icon=1",
                "shattered");
        price[7][0] = "Shattered Web Case: ";
        price[7][2] = "89";
        curl("http://csgobackpack.net/api/GetItemPrice/?currency=EUR&id=Operation%20Breakout%20Weapon%20Case&time=7&icon=1",
                "breakout");
        price[8][0] = "Operation Breakout Case: ";
        price[8][2] = "47";
        curl("http://csgobackpack.net/api/GetItemPrice/?currency=EUR&id=Falchion%20Case&time=7&icon=1", "falchion");
        price[9][0] = "Falchion Case: ";
        price[9][2] = "34";

        System.out.println("Done!");
        System.out.println("Calculating....");
        // Sleep prevent causing error
        // Get Prices
        Thread.sleep(2000);
        price[0][1] = getItemInfo("danger");
        price[1][1] = getItemInfo("spectrum2");
        price[2][1] = getItemInfo("gamma2");
        price[3][1] = getItemInfo("chroma3");
        price[4][1] = getItemInfo("phoenix");
        price[5][1] = getItemInfo("glove");
        price[6][1] = getItemInfo("vanguard");
        price[7][1] = getItemInfo("shattered");
        price[8][1] = getItemInfo("breakout");
        price[9][1] = getItemInfo("falchion");

        for (int i = 0; i < price.length - 1; i++) {
            System.out.println("-----------------------");
            System.out.println(price[i][0]);
            System.out.println("Value:" + price[i][1]);
            System.out.println("Amount:" + price[i][2]);
            System.out.println("Sum: " + Double.parseDouble(price[i][1]) * Double.parseDouble(price[i][2]));
            sum = sum + Double.parseDouble(price[i][1]) * Double.parseDouble(price[i][2]);
        }
        System.out.println("-----------------------");
        System.out.println("Total Value: " + sum);

    }

    public static void curl(String link, String name) throws Exception {
        String editLink = "curl -o " + name + ".json \"" + link + "\"";
        Process p = Runtime.getRuntime().exec(editLink);
    }

    public static String getItemInfo(String name) throws Exception {
        // parsing file "JSONExample.json"
        Object obj = new JSONParser().parse(new FileReader(name + ".json"));
        // typecasting obj to JSONObject
        JSONObject jo = (JSONObject) obj;
        // getting firstName and lastName
        String avgPrice = (String) jo.get("average_price");

        return avgPrice;
    }
}
