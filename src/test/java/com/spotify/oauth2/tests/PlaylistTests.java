package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.applicationApi.PlaylistApi;
import com.spotify.oauth2.pojo.Error;
import com.spotify.oauth2.pojo.Playlist;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.SpecBuilder.getRequestSpec;
import static com.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class PlaylistTests {

    public Playlist playListBuilder(String name,String description, boolean _public)
    {
        return new Playlist()
                .setName(name)
                .setDescription(description)
                .setPublic(_public);
    }

    @Test
    public void createPlayList()
    {
        Playlist requestPlaylist = playListBuilder("New Playlist","New playlist description",false);
        Response response = PlaylistApi.post(requestPlaylist);
        assertThat(response.getStatusCode(),equalTo(200));

        Playlist resposnePlaylist = response.as(Playlist.class);

        assertThat(resposnePlaylist.getName(),equalTo(resposnePlaylist.getName()));
        assertThat(resposnePlaylist.getDescription(),equalTo(resposnePlaylist.getDescription()));
        assertThat(resposnePlaylist.getPublic(), equalTo(resposnePlaylist.getPublic()));

    }

    @Test
    public void getAPIPlaylist()
    {
        Playlist requestPlaylist = new Playlist();

        requestPlaylist.setName("New Playlist");
        requestPlaylist.setDescription("New playlist description");
        requestPlaylist.setPublic(false);

        Response response = PlaylistApi.get("31erq56brecy6qx6zrostoy3ay6y");
        assertThat(response.getStatusCode(),equalTo(201));

        Playlist resposnePlaylist = response.as(Playlist.class);

        assertThat(resposnePlaylist.getName(),equalTo(resposnePlaylist.getName()));
        assertThat(resposnePlaylist.getDescription(),equalTo(resposnePlaylist.getDescription()));
        assertThat(resposnePlaylist.getPublic(), equalTo(resposnePlaylist.getPublic()));
    }

    @Test
    public void updateAPIPlaylist()
    {
        Playlist requestPlaylist = new Playlist();

        requestPlaylist.setName("Updated Playlist");
        requestPlaylist.setDescription("Updated playlist description");
        requestPlaylist.setPublic(false);

        Response res = PlaylistApi.update("31erq56brecy6qx6zrostoy3ay6y",requestPlaylist);
        assertThat(res.getStatusCode(), equalTo(200));

    }

    @Test
    public void shouldNotAbleToCreatePlaylistWithName()
    {
        Playlist requestPlaylist = new Playlist();

        requestPlaylist.setName("");
        requestPlaylist.setDescription("New playlist description");
        requestPlaylist.setPublic(false);

        Error err = RestAssured.given(getRequestSpec())
                .body(requestPlaylist)
                .when().put("/users/31erq56brecy6qx6zrostoy3ay6y")
                .then().spec(getResponseSpec())
                .assertThat()
                .extract()
                .as(Error.class);

        assertThat(err.getError().getStatus(), equalTo(400));
        assertThat(err.getError().getMessage(), equalTo("Missing required field: name"));

    }

    @Test
    public void shouldNotAbleToCreatePlaylistWithExpiredToken()
    {
        Playlist requestPlaylist = new Playlist();

        requestPlaylist.setName("New Playlist");
        requestPlaylist.setDescription("New playlist description");
        requestPlaylist.setPublic(false);

        Error err = RestAssured.given()
                .baseUri("https://api.spotify.com")
                .basePath("/v1")
                .header("Authorization", "Bearer "+"123")
                .contentType("application/json")
                .body(requestPlaylist)
                .when().put("/users/31erq56brecy6qx6zrostoy3ay6y")
                .then().spec(getResponseSpec())
                .assertThat()
                .extract()
                .as(Error.class);

        assertThat(err.getError().getStatus(), equalTo(401));
        assertThat(err.getError().getMessage(), equalTo("Invalid access token"));

    }
    
}
