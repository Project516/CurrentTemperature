package dev.project516.CurrentTemperature;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Temperature {

    double temp;

    public Temperature(double lat, double lon) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            ObjectMapper mapper = new ObjectMapper();

            String url =
                    "https://api.open-meteo.com/v1/forecast?latitude="
                            + lat
                            + "&longitude="
                            + lon
                            + "&current=temperature_2m&timezone=auto&temperature_unit=fahrenheit";

            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonNode root = mapper.readTree(response.body());

            temp = root.get("current").get("temperature_2m").asDouble();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double getTemp() {
        return temp;
    }
}
