package com.example.dragger.dialogapp.myLifeRecycler;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;

/**
 * Desc: TODO
 * <p>
 * Author:
 * PackageName: com.example.dragger.dialogapp.myLifeRecycler
 * ProjectName: ProjectTest01
 * Date: 2019/7/8 17:07
 */

public class LifeRecyclerActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 这个就可以是我们的presenter
        MyObserver myObserver = new MyObserver(new CallBack() {
            @Override
            public void update(String str) {
                Toast.makeText(LifeRecyclerActivity.this, str, Toast.LENGTH_SHORT).show();
                Log.i("TAG===", str);
            }
        });
        Lifecycle lifecycle = getLifecycle();
        lifecycle.addObserver(myObserver);


    }




    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
