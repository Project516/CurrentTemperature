package dev.project516.CurrentTemperature;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Location {

    double lat;
    double lon;
    String city;

    public Location() {
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

            lat = locationNode.get("lat").asDouble();
            lon = locationNode.get("lon").asDouble();
            city = locationNode.get("city").asText();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    double getLat() {
        return lat;
    }

    double getLon() {
        return lon;
    }

    String getCity() {
        return city;
    }
}
