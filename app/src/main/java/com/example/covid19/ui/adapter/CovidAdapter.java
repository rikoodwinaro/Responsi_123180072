package com.example.covid19.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid19.R;
import com.example.covid19.data.ContentItem;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CovidAdapter extends RecyclerView.Adapter<CovidAdapter.ViewHolder> {

    private ArrayList<ContentItem> contentItems = new ArrayList<>();

    public void setContentItems(ArrayList<ContentItem> contentItems) {
        if (contentItems != null){
            this.contentItems.clear();
            this.contentItems.addAll(contentItems);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CovidAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // koneksi layout item kedalam adapter ini
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CovidAdapter.ViewHolder holder, int position) {
        holder.bind(contentItems.get(position));
    }

    @Override
    public int getItemCount() {
        return contentItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //deklarasi variabel view sesuai layout xml

        private TextView tvSembuh, tvMeninggal, tvKonfirmasi, tvTanggal;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvTanggal = itemView.findViewById(R.id.tv_tanggal);
            tvSembuh = itemView.findViewById(R.id.tv_sembuh);
            tvMeninggal = itemView.findViewById(R.id.tv_meninggal);
            tvKonfirmasi = itemView.findViewById(R.id.tv_konfirmasi);
        }

        public void bind(ContentItem contentItem) {
            tvTanggal.setText(String.valueOf(contentItem.getTanggal()));
            tvSembuh.setText(String.valueOf(contentItem.getConfirmation_selesai()));
            tvMeninggal.setText(String.valueOf(contentItem.getConfirmation_meninggal()));
            tvKonfirmasi.setText(String.valueOf(contentItem.getConfirmation()));
        }
    }
}
