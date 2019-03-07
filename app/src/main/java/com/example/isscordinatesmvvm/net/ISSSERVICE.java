package com.example.isscordinatesmvvm.net;

import com.example.isscordinatesmvvm.REPO.ISSRepository;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ISSSERVICE {

    @GET(Constants.ENDPOINT)
    Call<ISSRepository> getCoordinates(@Query("lat") String lat, @Query("lon") String lon);

}
