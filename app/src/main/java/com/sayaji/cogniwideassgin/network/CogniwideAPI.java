package com.sayaji.cogniwideassgin.network;

import com.sayaji.cogniwideassgin.datamodel.MovieResponseData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface CogniwideAPI {
    @Headers("Content-Type: application/json")
    @GET("/3/movie/popular?api_key=86d934334e66f32edf04d20eb9d22de7")
    Call<MovieResponseData> getMovieData(@Query("page") int page);
}
