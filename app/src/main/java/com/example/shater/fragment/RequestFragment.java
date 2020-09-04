package com.example.shater.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.VideoView;

import com.example.shater.R;
import com.google.android.gms.maps.MapView;
import com.shawnlin.numberpicker.NumberPicker;


public class RequestFragment extends Fragment {


    private View view ;
    NumberPicker np_category ;
    EditText edt_description;
    Button btn_addImage , btn_addVideo , btn_confirm ;
    MapView mv_location ;
    ImageView img_addImage ;
    VideoView vid_addVideo;

    public RequestFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_request, container, false) ;
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        np_category = (NumberPicker) view.findViewById(R.id.np_category);
        edt_description = (EditText) view.findViewById(R.id.edt_description);
        btn_addImage = (Button) view.findViewById(R.id.btn_addimage);
        btn_addVideo =(Button) view.findViewById(R.id.btn_addvideo);
        btn_confirm = (Button) view.findViewById(R.id.btn_confirm);
        mv_location = (MapView) view.findViewById(R.id.mv_location);
        img_addImage = (ImageView) view.findViewById(R.id.img_add);
        vid_addVideo = (VideoView) view.findViewById(R.id.vi_add);


        // set string value in numberpicker
        String [] category = {"Home decor","Technology","Maintenance","Painting","Parking shades","Electricity"};
        np_category.setMinValue(1);
        np_category.setMaxValue(category.length);
        np_category.setDisplayedValues(category);
        np_category.setScrollerEnabled(true);
        np_category.setWrapSelectorWheel(true);

    }
}
