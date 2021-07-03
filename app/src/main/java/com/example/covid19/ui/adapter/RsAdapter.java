package com.example.covid19.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid19.R;
import com.example.covid19.data.DataItem;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RsAdapter extends RecyclerView.Adapter<RsAdapter.ViewHolder> {
    protected Context context;
    public RsAdapter(Context context) {
        this.context = context;
    }

    private ArrayList<DataItem> dataItems = new ArrayList<>();

    public void setDataRs(ArrayList<DataItem> dataItems){
        if (dataItems != null) {
            this.dataItems.clear();
            this.dataItems.addAll(dataItems);
        }
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_rumahsakit, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.bind(dataItems.get(position));
        holder.maps.setOnClickListener(v -> {
                    Uri gmmIntentUri = Uri.parse(
                            "geo:"+ dataItems.get(position).getLatitude() +
                                    "," + dataItems.get(position).getLongitude() +
                                    "?q="+dataItems.get(position).getNama());
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    context.startActivity(mapIntent);
        }
        );

    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nama_rs, alamat;
        private Button maps;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            nama_rs = itemView.findViewById(R.id.tv_rumahsakit);
            alamat = itemView.findViewById(R.id.tv_alamat);
            maps = itemView.findViewById(R.id.btn_maps);
        }

        public void bind(DataItem dataItem) {
            nama_rs.setText(dataItem.getNama());
            alamat.setText(dataItem.getAlamat());


        }
    }
}
