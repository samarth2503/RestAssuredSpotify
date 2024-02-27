package com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.pojo.Playlist;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.Route.PLAYLISTS;
import static com.spotify.oauth2.api.Route.USERS;
import static com.spotify.oauth2.api.SpecBuilder.getRequestSpec;
import static com.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static com.spotify.oauth2.api.TokenManager.getToken;
import static com.spotify.oauth2.api.TokenManager.renewToken;

public class PlaylistApi {

    public static String access_token = "BQD4v0ONTXL19ahBl3W6xXaRO3pFb2_xkAlw3v0Wp9OStIxwZbxK8oB7tKpF6E7UfP3JbW4vB6rA8EDoAT87VmLjG9pvoB8LJBWnEybLSDDdbu14SeqGBTmLFRItizC-iSsSoDUUHEoXsBAgdMpDXNLw6BMgfKAEikUnLPLtCgnhW4DU0WyJDN8u8ap5hRqal_EvvzZ0e7HftU6X8UjR1m9u8UNlYbII0oqqQSB_6ErTs-JQkCXuvOOOkN2fgi-uWVMSCw";
    public static Response post(Playlist requestPlaylist)
    {
        return RestResource.post(USERS+"/31erq56brecy6qx6zrostoy3ay6y"+PLAYLISTS,getToken(),requestPlaylist);

    }

    public static Response post(String token,Playlist requestPlaylist)
    {
        return RestResource.post(USERS+"/31erq56brecy6qx6zrostoy3ay6y"+PLAYLISTS,token,requestPlaylist);

    }

    public static Response get(String playList)
    {
        return RestResource.get(PLAYLISTS + "/" + playList,getToken());
    }

    public static Response update(String playlistId,Playlist requestPlaylist)
    {
        return RestResource.update(PLAYLISTS + "/" +playlistId, getToken(), requestPlaylist);

    }
}
