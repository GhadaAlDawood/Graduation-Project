package com.example.shater.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shater.R;
import com.example.shater.models.serviceInfo;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OfferServiceAdapter extends RecyclerView.Adapter<OfferServiceAdapter.OfferServiceViewHolder> {

    private  List<serviceInfo> serviceInfos ;
    private Context context ;

    public OfferServiceAdapter(List<serviceInfo> serviceInfos, Context context) {
        this.serviceInfos = serviceInfos;
        this.context = context;
    }

    @NonNull
    @Override
    public OfferServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.content_item_offerservice, parent, false);
        return new OfferServiceAdapter.OfferServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OfferServiceViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return serviceInfos.size();
    }

    public class OfferServiceViewHolder extends RecyclerView.ViewHolder{


        public OfferServiceViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
