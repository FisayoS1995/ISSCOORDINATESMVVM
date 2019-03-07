package com.example.isscordinatesmvvm.REPO;

import com.example.isscordinatesmvvm.net.ISSSERVICE;

import java.util.ArrayList;
import java.util.Observable;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource extends Observable implements DataSource {


    private static final Object Constants = ;
    private final ISSSERVICE issService;



    public RemoteDataSource() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()

                .addInterceptor(new HttpLoggingInterceptor()

                        .setLevel(HttpLoggingInterceptor.Level.BODY))

                .build();



        Retrofit retrofit = new Retrofit.Builder()

                .client(okHttpClient)

                .baseUrl(Constants.BASE_URL)

                .addConverterFactory(GsonConverterFactory.create())

                .build();



        issService = retrofit.create(ISSSERVICE.class);

    }



    @Override

    public void getCoordinatesForLocation(String latitude, String longtitude) {



        final List<com.example.issservicemvvmretrofit.data.Response> responsesList = new ArrayList<>();

        issService.getCoordinates(latitude, longtitude).enqueue(new Callback<ISSRepo>() {

            @Override

            public void onResponse(Call<ISSRepo> call, Response<ISSRepo> response) {

                if(response.isSuccessful() && response.body().getResponse() != null){

                    responsesList.clear();

                    responsesList.addAll(response.body().getResponse());

                    setChanged();

                    notifyObservers(responsesList);

                }

            }




            @Override

            public void onFailure(Call<ISSRepo> call, Throwable t) {

                t.printStackTrace();

            }

        });

    }



    @Override

    public void setObserver(ISSRepository observer) {

        observer.addObserver();

    }

}