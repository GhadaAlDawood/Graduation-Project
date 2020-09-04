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
import com.example.shater.models.offerCustomerInfo;

import java.util.List;


public class OfferCustomerFragment extends Fragment {
    private View view;
    RecyclerView rc_offerCust;
    RecyclerView.Adapter adapter ;
    RecyclerView.LayoutManager manager ;

    public OfferCustomerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_offer_customer, container, false);
        return view ;
    }

    @Override
    public void onStart() {
        super.onStart();
        rc_offerCust = (RecyclerView) view.findViewById(R.id.rc_offerCust);
        rc_offerCust.setHasFixedSize(true);
        manager = new LinearLayoutManager(getContext());
        rc_offerCust.setLayoutManager(manager);
        List<offerCustomerInfo>  offerCustomerInfos = null;
        rc_offerCust.setAdapter(new OfferCustomerAdapter(getContext() ,offerCustomerInfos ));
    }
}
