
package com.example.covid19.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covid19.R;
import com.example.covid19.data.ContentItem;
import com.example.covid19.data.service.CovidApi;
import com.example.covid19.data.service.CovidListener;
import com.example.covid19.ui.adapter.CovidAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView nama, nim, title;
    private RecyclerView rvkasusCovid;
    private CovidAdapter covidAdapter;
    BottomNavigationView menu;

    //inisialisasi callback
    CovidListener<ArrayList<ContentItem>> listCovidListener = new CovidListener<ArrayList<ContentItem>>() {
        @Override
        public void onSuccess(ArrayList<ContentItem> body) {
            covidAdapter.setContentItems(body);
        }

        @Override
        public void onFailed(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };


    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mengganti tulisan di action bar (Paling atas)
        getSupportActionBar().setTitle("Covid");

        //koneksikan variabel dengan view berdasarkan id
        nama = findViewById(R.id.tv_nama);
        nim = findViewById(R.id.tv_nim);
        title = findViewById(R.id.tv_title);
        menu = findViewById(R.id.bottom_navigation);

        //koneksikan variabel dengan view berdasarkan id
        rvkasusCovid = findViewById(R.id.rv_kasuscovid);

        //menyambungkan adapter yang telah dibuat ke dalam variabel
        covidAdapter = new CovidAdapter();

        rvkasusCovid.setLayoutManager(new LinearLayoutManager(this));
        rvkasusCovid.setHasFixedSize(true);
        rvkasusCovid.setAdapter(covidAdapter);
        rvkasusCovid.setLayoutManager(new LinearLayoutManager(this));

        menu.setOnNavigationItemSelectedListener(item -> {
            Intent home, rs;
            switch (item.getItemId()) {
                case R.id.kasus_covid:
                    break;
                case R.id.rs_rujukan:
                    rs = new Intent(MainActivity.this, RsActivity.class);
                    startActivity(rs);
                    finish();
                    break;
            }
            return true;
        });

        CovidApi covidApi = new CovidApi();
        covidApi.getListKasus(listCovidListener);

    }

}