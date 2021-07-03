package com.example.covid19.data.service;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.covid19.data.ContentItem;
import com.example.covid19.data.DataItem;
import com.example.covid19.data.Faskes;
import com.example.covid19.data.KasusCovid;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CovidApi {
    // membuat variabel untuk menampung URL dari API yang digunakan
    // public final String BASE_URL = "";

    //inisialisasi retrofit
    private Retrofit retrofit;

    public CovidApiInterface getCovidApi() {
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://covid19-public.digitalservice.id/api/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(CovidApiInterface.class);
    }

    public void getListKasus(final CovidListener<ArrayList<ContentItem>> listener) {
        getCovidApi().getListKasus().enqueue(new Callback<KasusCovid>() {
            @Override
            public void onResponse(Call<KasusCovid> call, Response<KasusCovid> response) {
                KasusCovid covidResponse = response.body();
                if (response.isSuccessful() && response.body() != null) {
                    assert covidResponse != null;
                    listener.onSuccess( covidResponse.getData().getContent());
                }
            }

            @Override
            public void onFailure(Call<KasusCovid> call, Throwable t) {
                listener.onFailed(t.getMessage());
                Log.d("TAG", t.getMessage());
            }
        });
    }

    public void getFaskes(final CovidListener<ArrayList<DataItem>> listener){
        getCovidApi().getFaskes().enqueue(new Callback<Faskes>() {
            @Override
            public void onResponse(Call<Faskes> call, Response<Faskes> response) {
                Faskes faskesRespone = response.body();
                if (faskesRespone!=null){
                    listener.onSuccess((ArrayList<DataItem>) faskesRespone.getData());
                }
            }

            @Override
            public void onFailure(Call<Faskes> call, Throwable t) {
                listener.onFailed(t.getMessage());
            }
        });
    }
}
