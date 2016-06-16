package java2;

import javax.swing.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

/**
 * Created by Наталья on 16.06.2016.
 */
public class YandexApi {


    static String APIKEY = "trnsl.1.1.20160612T132248Z.361089afbf8b9ba5.631c2650b19074f361aa0d830818111cca06aea2";
    static String url = "https://translate.yandex.net/api/v1.5/tr.json/translate?" + "key=" + APIKEY;
    static String lang = "en-ru";
    static String selectedText = "Hi";

    public static void main(String [] args)throws IOException, InterruptedException {
       /* JFrame frame1 = new JFrame();
        frame1.setSize(275,25);
        JTextField text1 = new JTextField();
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.add(text1);
        frame1.setUndecorated(true);
        frame1.setLocation(xPressed + 10,yPressed + 10);
        frame1.setVisible(true);*/


        String text = selectedText;
        URL urlobj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlobj.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        // List<NameValuePair> list = new ArrayList<NameValuePair>();//NameValuePair-связывает имя со значение ("имя",значение)
        DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
        dataOutputStream.writeBytes("&text=" + URLEncoder.encode(text, "UTF-8") + "&lang=" + lang  /*+ "&callback"*/);
        // connection.wait(1000);
        int code = connection.getResponseCode();
        if (code == 404) {
//TODO авторизация с другим переводчиком
            System.out.print("Превышена суточная норма или закончились слова.");
        }

        InputStream response = connection.getInputStream();
        String first = new Scanner(response).nextLine();
        //System.out.print(first);

        JFrame frame1 = new JFrame();
        frame1.setSize(first.length() * 4 + 25, 25);
        JTextField text1 = new JTextField();
        // frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.add(text1);
        frame1.setUndecorated(true);
        frame1.setLocation(10, 10);
        frame1.setVisible(true);

        int start = first.indexOf("[");
        int end = first.indexOf("]");
        String translated = first.substring(start + 2, end - 1);
        text1.setText(translated);
        System.out.print(translated);

    }
}
