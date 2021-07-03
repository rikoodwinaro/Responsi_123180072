package com.example.covid19.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid19.R;
import com.example.covid19.data.DataItem;
import com.example.covid19.data.service.CovidApi;
import com.example.covid19.data.service.CovidListener;
import com.example.covid19.ui.adapter.RsAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

@SuppressLint("NonConstantResourceId")
public class RsActivity extends AppCompatActivity {

    protected RecyclerView rvRumahSakit;
    protected RsAdapter rsAdapter;
    BottomNavigationView menu;

    CovidListener<ArrayList<DataItem>> listRsListener = new CovidListener<ArrayList<DataItem>>() {
        @Override
        public void onSuccess(ArrayList<DataItem> body) { rsAdapter.setDataRs(body); }

        @Override
        public void onFailed(String message) {
            Toast.makeText(RsActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        setContentView(R.layout.activity_rs);

        rvRumahSakit = findViewById(R.id.rv_rumahsakit);
        menu         = findViewById(R.id.bottom_navigation2);

        rsAdapter = new RsAdapter(this);
        rvRumahSakit.setLayoutManager(new LinearLayoutManager(this));
        rvRumahSakit.setHasFixedSize(true);
        rvRumahSakit.setAdapter(rsAdapter);

        menu.setOnNavigationItemSelectedListener(item -> {
            Intent home, rs;
            switch (item.getItemId()) {
                case R.id.kasus_covid:
                    home = new Intent(RsActivity.this, MainActivity.class);
                    startActivity(home);
                    finish();
                    break;
                case R.id.rs_rujukan:
                    break;
            }
            return true;
        });

        CovidApi covidApi = new CovidApi();
        covidApi.getFaskes(listRsListener);

    }
}
