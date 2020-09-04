package com.example.shater.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.shater.R;
import com.example.shater.helper.CacheJson;
import com.example.shater.models.userInfo;

import java.io.IOException;

public class SplashActivity extends AppCompatActivity {

    Button btn_start;
    CacheJson cacheJson ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        btn_start = (Button) findViewById(R.id.btn_start);
        cacheJson = new CacheJson();

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cacheJson.fileExists(SplashActivity.this , "info")){
                    try {
                        userInfo info = (userInfo) cacheJson.readObject(SplashActivity.this , "info");
                        if(info.getUser().equals("customer")){
                            startActivity(new Intent(SplashActivity.this , HomeCustomerActivity.class));
                            finish();
                        }else if(info.getUser().equals("Service Provider")){
                            startActivity(new Intent(SplashActivity.this , HomeServiceActivity.class));
                            finish();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }else {
                    startActivity(new Intent(SplashActivity.this , UserActivity.class));
                    finish();
                }


            }
        });
    }
}
