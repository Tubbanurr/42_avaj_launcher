package com.avaj.simulator;

public class AircraftFactory 
{
    private static AircraftFactory factory;
    private static long idCounter = 0;

    private AircraftFactory() {}

    public static AircraftFactory getFactory() 
    {
        if (factory == null)
            factory = new AircraftFactory();
        return factory;
    }

    public Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) 
    {
        idCounter++;
        switch (p_type) {
            case "Helicopter": return new Helicopter(idCounter, p_name, p_coordinates);
            case "JetPlane":   return new JetPlane(idCounter, p_name, p_coordinates);
            case "Balloon":    return new Balloon(idCounter, p_name, p_coordinates);
            default:
                System.out.println("Unknown aircraft type: " + p_type);
                return null;
        }
    }
}
