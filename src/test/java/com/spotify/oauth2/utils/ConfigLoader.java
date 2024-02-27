package com.spotify.oauth2.utils;

import java.util.Properties;

public class ConfigLoader {

    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader(){
        properties = PropertyUtils.propertyLoader("src/test/resources/config.properties");
    }

    public static ConfigLoader getInstance()
    {
        if(configLoader == null){
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getClientId()
    {
        String client_id = properties.getProperty("client_id");
        if(client_id != null){
            return client_id;
        } else{
            throw new RuntimeException("Property client_id is not specified in config.properties file");
        }
    }

    public String getGrantType()
    {
        String grant_type = properties.getProperty("grant_type");
        if(grant_type != null){
            return grant_type;
        } else{
            throw new RuntimeException("Property grant_type is not specified in config.properties file");
        }
    }

    public String getClientSecret()
    {
        String client_secret = properties.getProperty("client_secret");
        if(client_secret != null){
            return client_secret;
        } else{
            throw new RuntimeException("Property client_secret is not specified in config.properties file");
        }
    }

    public String getRefreshToken()
    {
        String refresh_token = properties.getProperty("refresh_token");
        if(refresh_token != null){
            return refresh_token;
        } else{
            throw new RuntimeException("Property refresh_token is not specified in config.properties file");
        }
    }

    public String getUser()
    {
        String user = properties.getProperty("user");
        if(user != null){
            return user;
        } else{
            throw new RuntimeException("Property user is not specified in config.properties file");
        }
    }
}
