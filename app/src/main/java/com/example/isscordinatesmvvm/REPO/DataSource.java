package com.example.isscordinatesmvvm.REPO;

public interface DataSource {

    void getCoordinatesForLocation(String latitude, String longtitude);

    void setObserver(ISSRepository observer);

}