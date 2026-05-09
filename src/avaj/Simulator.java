package avaj;

import java.io.*;
import java.util.*;

public class Simulator {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java avaj.Simulator <scenario_file>");
            return;
        }

        WeatherTower weatherTower = new WeatherTower();

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            String firstLine = reader.readLine();
            if (firstLine == null) {
                System.out.println("Error: Empty file.");
                return;
            }

            int times;
            try {
                times = Integer.parseInt(firstLine.trim());
                if (times <= 0) throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.out.println("Error: First line must be a positive integer.");
                return;
            }

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.trim().split("\\s+");
                if (parts.length != 5) {
                    System.out.println("Error: Invalid format: " + line);
                    return;
                }

                String type = parts[0];
                String name = parts[1];
                int lon, lat, h;
                try {
                    lon = Integer.parseInt(parts[2]);
                    lat = Integer.parseInt(parts[3]);
                    h   = Integer.parseInt(parts[4]);
                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid coordinates: " + line);
                    return;
                }

                if (lon < 0 || lat < 0 || h < 0) {
                    System.out.println("Error: Coordinates must be positive.");
                    return;
                }
                h = Math.min(h, 100);

                Coordinates coords = new Coordinates(lon, lat, h);
                Flyable aircraft = AircraftFactory.getFactory().newAircraft(type, name, coords);
                if (aircraft == null) return;
                aircraft.registerTower(weatherTower);
            }

            for (int i = 0; i < times; i++)
                weatherTower.changeWeather();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
