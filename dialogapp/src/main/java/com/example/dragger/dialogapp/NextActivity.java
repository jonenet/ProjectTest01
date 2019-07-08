package com.example.dragger.dialogapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GallerySnapHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dragger.dialogapp.widget.MyAdapter;

/**
 * Created by ex-zhoulai on 2018/4/28.
 */

public class NextActivity extends AppCompatActivity {

//    @TestAnnoAware
//    public String text;
//
//    @TestAnnoAware
//    public String name;

    @SuppressLint("ObjectAnimatorBinding")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        RecyclerView rvRecycler = (RecyclerView) findViewById(R.id.rv_recycler);
        rvRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,false));
//        new SmartSnapHelper().attachToRecyclerView(rvRecycler);
        new GallerySnapHelper().attachToRecyclerView(rvRecycler);
//        new LinearSnapHelper().attachToRecyclerView(rvRecycler);
        rvRecycler.setAdapter(new MyAdapter());
    }

}
