package com.example.shater.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.shater.R;
import com.example.shater.helper.CacheJson;
import com.example.shater.models.requestCustomerToService;
import com.example.shater.models.userInfo;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.shawnlin.numberpicker.NumberPicker;

import java.io.IOException;
import java.util.Calendar;

public class RequestActivity extends AppCompatActivity {
   // NumberPicker np_category ;
    Spinner sp_category;
    EditText edt_description , edt_time , edt_date;
    Button btn_addImage , btn_addVideo , btn_confirm ;
    MapView mv_location ;
    ImageView img_addImage , imv_time ,imv_date ;
    VideoView vid_addVideo;
    private int mYear , mMounth , mDay , mHour , mMinute ;
    String valueCategory , description , time , date , urlImage= null ;
    Double lat , lng;

    private StorageReference mStorageRef;
    private StorageTask task ;
    private DatabaseReference  databaseReference ;
    public Uri imageUri ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        mStorageRef = FirebaseStorage.getInstance().getReference("Images");

        sp_category = (Spinner) findViewById(R.id.sp_categray);
       // np_category = (NumberPicker) findViewById(R.id.np_category);
        edt_description = (EditText) findViewById(R.id.edt_description);
        btn_addImage = (Button) findViewById(R.id.btn_addimage);
        btn_addVideo =(Button) findViewById(R.id.btn_addvideo);
        btn_confirm = (Button) findViewById(R.id.btn_confirm);
        mv_location = (MapView) findViewById(R.id.mv_location);
        img_addImage = (ImageView) findViewById(R.id.img_add);
        vid_addVideo = (VideoView) findViewById(R.id.vi_add);

        edt_date = (EditText) findViewById(R.id.edt_date);
        edt_time = (EditText) findViewById(R.id.edt_time);
        imv_date = (ImageView) findViewById(R.id.imv_date);
        imv_time = (ImageView) findViewById(R.id.imv_time);

        btn_addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosImage();
            }
        });




        // set string value in numberpicker
        final String [] category = {"Home decor","Technology","Maintenance","Painting","Parking shades","Electricity"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,category);
        sp_category.setAdapter(adapter);

        sp_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                valueCategory = (String)adapterView.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                valueCategory = null;
            }
        });

//        np_category.setMinValue(1);
//        np_category.setMaxValue(category.length);
//        np_category.setDisplayedValues(category);
//        np_category.setScrollerEnabled(true);
//        np_category.setWrapSelectorWheel(true);
//
//        np_category.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
//            @Override
//            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
//                valueCategory = category[picker.getValue()];
//                Toast.makeText(RequestActivity.this, valueCategory +" from onChange", Toast.LENGTH_SHORT).show();
//            }
//        });
//        np_category.setOnScrollListener(new NumberPicker.OnScrollListener() {
//            @Override
//            public void onScrollStateChange(NumberPicker view, int scrollState) {
//                valueCategory= category[view.getValue()];
//                Toast.makeText(RequestActivity.this, valueCategory +" from onScroll", Toast.LENGTH_SHORT).show();
//
//            }
//        });

        imv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 final Calendar calendar = Calendar .getInstance();
                 mYear = calendar.get(Calendar.YEAR);
                 mMounth = calendar.get(Calendar.MONTH);
                 mDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(RequestActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int mounth, int day) {
                        date = day +"/"+(mounth+1)+"/"+year ;
                        edt_date.setText(date);

                    }
                },mYear , mMounth , mDay);
               dialog.show();
            }
        });

        imv_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar .getInstance();
                mHour = calendar.get(Calendar.HOUR_OF_DAY);
                mMinute = calendar.get(Calendar.MINUTE);

                TimePickerDialog pickerDialog = new TimePickerDialog(RequestActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        time = hour +":"+minute ;
                        edt_time.setText(time);
                    }
                },mHour , mMinute , false);
                pickerDialog.show();

            }
        });

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get description from edtitext
                description = edt_description.getText().toString().trim();
                if(task != null && task.isInProgress()){
                    Toast.makeText(RequestActivity.this, "upload in progress", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (!TextUtils.isEmpty(valueCategory) && !TextUtils.isEmpty(date) && !TextUtils.isEmpty(time) && !TextUtils.isEmpty(description) ){
                         sentRequest(valueCategory , date , time , description ,0.0 , 0.0);
                    }
                    else {
                        Toast.makeText(RequestActivity.this, " please Insert All Data !!", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    // Get Imag from your device
    private void choosImage (){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent , 1);
    }

    private String getExtension(Uri uri){
        ContentResolver resolver = getContentResolver();
        MimeTypeMap typeMap = MimeTypeMap.getSingleton();
        return typeMap.getExtensionFromMimeType(resolver.getType(uri));
    }

    private void sentRequest (String valueCategory , String date , String time , String description  , double lat , double lng ){

        // Get data of user from Cache
        userInfo userInfo = new userInfo();
        CacheJson cacheJson = new CacheJson();
        try {
            userInfo = (com.example.shater.models.userInfo) cacheJson.readObject(RequestActivity.this , "info");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // insert data in file JSON of requestCustomerToService
        String imageId = System.currentTimeMillis()+"."+getExtension(imageUri) ;
        requestCustomerToService  toService = new requestCustomerToService();
        toService.setCategory(valueCategory);
        toService.setTime(time);
        toService.setDate(date);
        toService.setId_customer(userInfo.getId());
        toService.setDescription(description);
        toService.setEmail_customer(userInfo.getEmail());
        toService.setName_customer(userInfo.getName());
        toService.setPhone_customer(userInfo.getPhone_number());
        toService.setLat(0.0);
        toService.setLng(0.0);
        toService.setUrlImage(imageId);


       // set data in firebasedatabase
       databaseReference = FirebaseDatabase.getInstance().getReference().child("Service").child(valueCategory);
       databaseReference.push().setValue(toService);

       // upload image in firebasStorage
        StorageReference reference = mStorageRef.child(imageId);
        task =reference.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                       // Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        Toast.makeText(RequestActivity.this, "image upload Successfully ", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(RequestActivity.this, "image upload Failure "+exception, Toast.LENGTH_SHORT).show();

                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode == RESULT_OK && data != null && data.getData() != null){

            imageUri = data.getData();
            img_addImage.setVisibility(View.VISIBLE);
            img_addImage.setImageURI(imageUri);
        }
    }
}
