package com.android.record;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.record.activity.GreenDaoActivity;
import com.android.record.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(R.layout.activity_main);
        setContentView(activityMainBinding.getRoot());
        activityMainBinding.toGreenDao.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.to_Green_Dao) {
            startActivity(new Intent(this, GreenDaoActivity.class));
        }
    }

}