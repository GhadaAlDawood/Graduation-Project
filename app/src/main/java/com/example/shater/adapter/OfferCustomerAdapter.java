package com.example.shater.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.shater.R;
import com.example.shater.models.offerCustomerInfo;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OfferCustomerAdapter extends RecyclerView.Adapter<OfferCustomerAdapter.OfferCustomerViewHolder> {

    private Context context;

    private List<offerCustomerInfo>  offerCustomerInfos ;

    public OfferCustomerAdapter(Context context, List<offerCustomerInfo> offerCustomerInfos) {
        this.context = context;
        this.offerCustomerInfos = offerCustomerInfos;
    }

    @NonNull
    @Override
    public OfferCustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.content_item_offercustomer, parent, false);
        return new OfferCustomerAdapter.OfferCustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OfferCustomerViewHolder holder, int position) {

      //  final  serviceInfo info = offerCustomerInfos.get(position);
//
//        holder.tv_name.setText(info.getNameProvider());
//        holder.tv_price.setText(info.getPrice());
//        holder.rb_provider.setRating(info.getNumStart());

        holder.btn_accpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // set boolean accept true in database firebase
            }
        });

        holder.btn_decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // set boolean accept false in database firebase
            }
        });



    }

    @Override
    public int getItemCount() {
        return offerCustomerInfos.size();
    }

    public class OfferCustomerViewHolder extends RecyclerView.ViewHolder{


        TextView tv_name , tv_price ;
        RatingBar rb_provider;
        Button btn_accpet ,btn_decline ;

        public OfferCustomerViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            rb_provider = (RatingBar) itemView.findViewById(R.id.rb_provider);
            btn_accpet = (Button) itemView.findViewById(R.id.btn_accept);
            btn_decline = (Button) itemView.findViewById(R.id.btn_decline);

        }
    }
}
