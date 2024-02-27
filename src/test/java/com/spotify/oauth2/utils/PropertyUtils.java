package com.spotify.oauth2.utils;

import java.io.*;
import java.util.Properties;

public class PropertyUtils {

    public static Properties propertyLoader(String filePath)
    {
        Properties properties = new Properties();
        BufferedReader reader;

        try{
            reader = new BufferedReader(new FileReader(filePath));
            properties.load(reader);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Properties file not found "+ filePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load Properties file "+ filePath);
        }

        return properties;

    }
}
