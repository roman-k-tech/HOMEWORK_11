package TASK_03;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GetLinks {

    private HttpURLConnection httpURLConnection;
    private static String urlAddress = "https://stackoverflow.com/questions/5120171/extract-links-from-a-web-page";
    private static String outputFile = "output.txt";

    public GetLinks(HttpURLConnection httpURLConnection)
    {
        this.httpURLConnection = httpURLConnection;
    }

    public static void main(String[] args) {
        try {
            URL uRL = new URL(urlAddress);
            HttpURLConnection httpURLConnection = (HttpURLConnection) uRL.openConnection();

            GetLinks getLinks = new GetLinks(httpURLConnection);
            List<String> LinksList = getLinks.parse();

            LinksList.stream().forEach(a -> System.out.println(a));

            File output = new File(outputFile);
            PrintWriter printWriter = new PrintWriter(output);
            LinksList.stream().forEach(a -> printWriter.println(a));
            printWriter.close();
        }
        catch (MalformedURLException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
    }

    public List<String> parse ()
    {
        List<String> LinksList = null;
        try (
            InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
        )
        {
            LinksList = new ArrayList<String>();

            LinksList = bufferedReader.lines().filter(n -> n.contains("href=")).map(n -> n.trim()).collect(Collectors.toCollection(ArrayList::new));
        }
        catch (IOException e) { e.printStackTrace();}

        return LinksList;
    }
}
