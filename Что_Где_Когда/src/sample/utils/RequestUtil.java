package sample.utils;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class RequestUtil  {
    /**
     * Отправка POST-запроса
     *
     * @param urlString - URL, куда отправляется запрос
     * @param paramsMap - параметры запроса
     * @return string
     */
    public static String sendPOST(String urlString, Map<String, String> paramsMap) {
        try {
            URL obj = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestMethod("POST");

            Gson gsonObj = new Gson();

            String jsonStr = gsonObj.toJson(paramsMap);

            con.setDoOutput(true);
            OutputStream stream = con.getOutputStream();

            stream.write(jsonStr.getBytes(StandardCharsets.UTF_8));
            stream.flush();
            stream.close();

            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();

            } else {
                System.out.println("POST получил код " + con.getResponseCode());
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Отпрвка PUT-запроса
     *
     * @param urlString  - URL, куда отправляется запрос
     * @param jsonString - строка с JSON для обновления данных
     * @return string
     */
    public static String sendPUT(String urlString, String jsonString) {
        try {
            URL obj = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestMethod("PUT");
            con.setDoOutput(true);
            OutputStream stream = con.getOutputStream();

            stream.write(jsonString.getBytes(StandardCharsets.UTF_8));
            stream.flush();
            stream.close();

            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();

            } else {
                System.out.println("PUT получил код " + con.getResponseCode());
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }


}
