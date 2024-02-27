package com.spotify.oauth2.api;


import com.spotify.oauth2.utils.ConfigLoader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.time.Instant;
import java.util.HashMap;

import static com.spotify.oauth2.api.SpecBuilder.getResponseSpec;

public class TokenManager {

    private static String access_token;
    private static Instant expiry_time;

    public static String getToken(){

        try{
            if(access_token == null || Instant.now().isAfter(expiry_time)){
                Response response = renewToken();
                access_token = response.path("access_token");
                long expiry_durationInSeconds = response.path("expries_in");
                expiry_time = Instant.now().plusSeconds(expiry_durationInSeconds - 300);
            } else {
                System.out.println("Token is good to use....");
            }
        }
        catch(Exception e){
                throw new RuntimeException("ABORT!! Failed to get the token..");
        }

        return access_token;
    }

    public static Response renewToken()
    {
        HashMap<String, String> formsParams = new HashMap<String, String>();

        formsParams.put("client_id", ConfigLoader.getInstance().getClientId());
        formsParams.put("client_secret",ConfigLoader.getInstance().getClientSecret());
        formsParams.put("grant_type",ConfigLoader.getInstance().getGrantType());
        formsParams.put("refresh_token", ConfigLoader.getInstance().getRefreshToken());

        Response response = RestAssured.given()
                .baseUri("http://accounts.spotify.com").
                formParams(formsParams).
                contentType(ContentType.URLENC).
                when().post("/api/token").
                then().spec(getResponseSpec()).extract()
                .response();

        if(response.statusCode() != 200) {
            throw new RuntimeException("ABORT !!! Renew Token failed...");
        }

        return response.path("access_token");


    }
}
