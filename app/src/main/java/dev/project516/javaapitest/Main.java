package dev.project516.javaapitest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) {
        try {

            HttpClient client = HttpClient.newHttpClient();
            ObjectMapper mapper = new ObjectMapper();

            HttpRequest locationRequest =
                    HttpRequest.newBuilder()
                            .uri(URI.create("http://ip-api.com/json/"))
                            .GET()
                            .build();

            HttpResponse<String> locactionResponse =
                    client.send(locationRequest, HttpResponse.BodyHandlers.ofString());

            JsonNode locationNode = mapper.readTree(locactionResponse.body());

            double lat = locationNode.get("lat").asDouble();
            double lon = locationNode.get("lon").asDouble();
            String city = locationNode.get("city").asText();

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

            double temp = root.get("current").get("temperature_2m").asDouble();

            System.out.println("Current Temperature in " + city + ": " + temp + "°F");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
