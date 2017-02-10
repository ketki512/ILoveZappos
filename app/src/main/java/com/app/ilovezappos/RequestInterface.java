package com.app.ilovezappos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ketkitrimukhe on 2/5/17.
 */

// the request for getting the data
public interface RequestInterface {

    @GET("Search?term=&key=b743e26728e16b81da139182bb2094357c31d331")

    Call<JSONResponse> getJSON(@Query("term") String term);
}