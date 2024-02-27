package com.spotify.oauth2.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.spotify.oauth2.api.Route.BASE_PATH;

public class SpecBuilder {

    static String access_token = "BQDtPo6vFAmladO2kURdyrLliHqmGL9--LTD_d8GA3868HfqZhsd5WyZZIViHlLG29mXsI4kaMxQzMlfxZr9ifVXLDXE255yCRMCGlElewnLzn9G_juRrL6vaasdqVnwqIWGOCDEzKGc_crTQfGJK4PFEykaNZiHz26xTVXCNxBWcPZzUEgyEyXCQBEjX7ze1u5A0kLtk2vrp7fZx7GdfHg7lcizTicemUn8cwVnvVgsrPpTDP7naeLZ5FGurq3Y-KUQNg";
    public static RequestSpecification getRequestSpec()
    {
        return new RequestSpecBuilder()
                .setBaseUri("https://api.spotify.com")
                .setBasePath(BASE_PATH)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    public static RequestSpecification getAccountRequestSpec()
    {
        return new RequestSpecBuilder()
                .setBaseUri("https://accounts.spotify.com")
                .setContentType(ContentType.URLENC)
                .log(LogDetail.ALL)
                .build();
    }


    public static ResponseSpecification getResponseSpec()
    {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }
}
