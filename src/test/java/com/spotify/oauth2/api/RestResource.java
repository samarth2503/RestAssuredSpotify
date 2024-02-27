package com.spotify.oauth2.api;

import com.spotify.oauth2.pojo.Playlist;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;

import java.util.HashMap;

import static com.spotify.oauth2.api.Route.API;
import static com.spotify.oauth2.api.Route.TOKEN;
import static com.spotify.oauth2.api.SpecBuilder.*;

public class RestResource {

    public static Response post(String path, String token, Object requestPlaylist)
    {
        return RestAssured.given(getRequestSpec())
                .body(requestPlaylist)
                .header("Authorization", "Bearer "+token)
                .when().post(path)
                .then().spec(getResponseSpec())
                .extract().response();
    }

    public static Response postAccount(HashMap<String,String> formParams)
    {
        return RestAssured.given(getAccountRequestSpec())
                .formParams(formParams).
                when().post(API + TOKEN)
                .then().spec(getResponseSpec())
                .extract().response();
    }

    public static Response get(String path, String token)
    {
        return RestAssured.given(getRequestSpec())
                .header("Authorization", "Bearer "+token)
                .when().post(path)
                .then().spec(getResponseSpec())
                .extract().response();
    }

    public static Response update(String path, String token, Object requestPlaylist)
    {
        return RestAssured.given(getRequestSpec())
                .header("Authorization", "Bearer "+token)
                .body(requestPlaylist)
                .when().put()
                .then().spec(getResponseSpec())
                .extract().response();
    }

}
