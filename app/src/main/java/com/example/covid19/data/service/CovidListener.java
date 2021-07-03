package com.example.covid19.data.service;

public interface CovidListener<T> {
    void onSuccess(T body);
    void onFailed(String message);
}
