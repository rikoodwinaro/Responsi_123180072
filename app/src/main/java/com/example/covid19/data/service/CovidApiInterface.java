package com.example.covid19.data.service;

import com.example.covid19.data.Faskes;
import com.example.covid19.data.KasusCovid;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CovidApiInterface {
    // request ambil data dari API dengan link tertentu
    // GET sebagai metode akses yaitu mengambil data
    // Call untuk memanggil object untuk menampung data yang diambil
    // Path untuk membuat link menjadi dinamis
    @GET("rekapitulasi_v2/jabar/harian")
    Call<KasusCovid> getListKasus();

    @GET("sebaran_v2/jabar/faskes")
    Call<Faskes> getFaskes();
}
