package com.example.isscordinatesmvvm.Home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.isscordinatesmvvm.REPO.DataSource;
import com.example.isscordinatesmvvm.REPO.ISSRepository;
import com.example.isscordinatesmvvm.REPO.LocalDataSource;
import com.example.isscordinatesmvvm.REPO.RemoteDataSource;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import retrofit2.Response;

public class HomeViewModel implements Observer {



    private final DataSource issRepository;

    private final MutableLiveData<List<Response>> responseLiveData = new MutableLiveData<>();



    public HomeViewModel() {

        this.issRepository = new ISSRepository(new LocalDataSource(), new RemoteDataSource());

    }



    @Override

    public void update(Observable observable, Object result) {

        List<Response> responseList = (List<Response>) result;

        responseLiveData.setValue(responseList);

    }



    public LiveData<List<Response>> getResponseLiveData() {

        return responseLiveData;

    }



    public void getResponses(String latitude, String longtitude){

        issRepository.setObserver(this);

        issRepository.getCoordinatesForLocation(latitude,longtitude);

    }

}