package com.example.dragger.dialogapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    private MyDialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        findViewById(R.id.view).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myDialog = new MyDialog(MainActivity.this);
//                myDialog.show();
//            }
//        });
        startActivity(new Intent(this, NextActivity.class).putExtra("text", "123456789"));

    }
}
