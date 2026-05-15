package com.avaj.simulator;

import java.io.*;

public class Simulator 
{
    public static void main(String[] args) 
    {
        if (args.length != 1) {
            System.out.println("Usage: java com.avaj.simulator.Simulator <scenario_file>");
            return;
        }

        WeatherTower weatherTower = new WeatherTower();

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            String firstLine = reader.readLine();
            if (firstLine == null) {
                throw new InvalidScenarioException("Empty file.");
            }

            int times;
            try {
                times = Integer.parseInt(firstLine.trim());
                if (times <= 0) throw new InvalidScenarioException("First line must be a positive integer.");
            } catch (NumberFormatException e) {
                throw new InvalidScenarioException("First line must be a positive integer.");
            }

            String line;
            while ((line = reader.readLine()) != null) 
            {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.trim().split("\\s+");
                if (parts.length != 5) {
                    throw new InvalidScenarioException("Invalid format: " + line);
                }

                String type = parts[0];
                String name = parts[1];
                int lon, lat, h;
                try {
                    lon = Integer.parseInt(parts[2]);
                    lat = Integer.parseInt(parts[3]);
                    h   = Integer.parseInt(parts[4]);
                } catch (NumberFormatException e) {
                    throw new InvalidCoordinatesException("Invalid coordinates: " + line);
                }

                if (lon < 0 || lat < 0 || h < 0) {
                    throw new InvalidCoordinatesException("Coordinates must be positive.");
                }
                h = Math.min(h, 100);

                Coordinates coords = new Coordinates(lon, lat, h);
                Flyable aircraft = AircraftFactory.getFactory().newAircraft(type, name, coords);
                if (aircraft == null) {
                    throw new InvalidScenarioException("Unknown aircraft type: " + type);
                }
                aircraft.registerTower(weatherTower);
            }

            for (int i = 0; i < times; i++)
                weatherTower.changeWeather();

        } catch (InvalidScenarioException | InvalidCoordinatesException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
