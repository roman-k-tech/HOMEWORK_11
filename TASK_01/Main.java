package TASK_01;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        AddressCheck ac = new AddressCheck();
        File file = new File("address.txt");
        ac.loadFromFile(file);
        ac.checkAllAddress();
        ac.getAddressMap().forEach((a, b) -> System.out.println(a + "  ->  " + b));
    }
}

class AddressCheck {

    private Map<String, String> addressMap = new HashMap<>();

    public void loadFromFile(File file) {
        try {
            Files.lines(Paths.get(file.getAbsolutePath())).forEach(a -> addressMap.put(a, ""));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkAllAddress() {
        addressMap.replaceAll((key, value) -> checkOneAddress(key));
    }

    public Map<String, String> getAddressMap() {
        return addressMap;
    }

    private String checkOneAddress(String adress) {
        try {
            URL url = new URL(adress);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            int responseCode = con.getResponseCode();
            if (responseCode == 200) {
                return " OK";
            } else {
                return "" + responseCode + " " + con.getResponseMessage();
            }
        } catch (IOException e) {
            return " NOT AVIABLE";
        }
    }
}
