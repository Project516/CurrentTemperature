package dev.project516.CurrentTemperature;

public class Main {
    public static void main(String[] args) {
        Location location = new Location();
        Temperature temperature = new Temperature(location.getLat(), location.getlon());
        System.out.println(
                "Current Temperature in "
                        + location.getCity()
                        + ": "
                        + temperature.getTemp()
                        + "°F");
    }
}
