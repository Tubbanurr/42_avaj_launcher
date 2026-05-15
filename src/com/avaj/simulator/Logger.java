package com.avaj.simulator;

import java.io.*;

public class Logger 
{
    private static Logger instance;
    private BufferedWriter writer;

    private Logger() 
    {
        try {
            writer = new BufferedWriter(new FileWriter("simulation.txt"));
        } catch (IOException e) {
            System.out.println("Error creating simulation.txt: " + e.getMessage());
        }
    }

    public static Logger getInstance() 
    {
        if (instance == null)
            instance = new Logger();
        return instance;
    }

    public static void log(String message) 
    {
        try {
            getInstance().writer.write(message);
            getInstance().writer.newLine();
            getInstance().writer.flush();
        } catch (IOException e) {
            System.out.println("Error writing log: " + e.getMessage());
        }
    }
}
