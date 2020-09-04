package com.example.shater.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shater.R;
import com.example.shater.adapter.OfferCustomerAdapter;
import com.example.shater.adapter.OfferServiceAdapter;
import com.example.shater.models.offerCustomerInfo;
import com.example.shater.models.serviceInfo;

import java.util.List;

public class OfferServiceFragment extends Fragment {


    private View view;
    RecyclerView rc_offerservice;
    RecyclerView.Adapter adapter ;
    RecyclerView.LayoutManager manager ;

    public OfferServiceFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_offer_service, container, false);
        return view ;
    }

    @Override
    public void onStart() {
        super.onStart();
        rc_offerservice = (RecyclerView) view.findViewById(R.id.rc_offerCust);
        rc_offerservice.setHasFixedSize(true);
        manager = new LinearLayoutManager(getContext());
        rc_offerservice.setLayoutManager(manager);
        List<serviceInfo> serviceInfos = null;
        rc_offerservice.setAdapter(new OfferServiceAdapter(serviceInfos , getContext()  ));
    }
}
