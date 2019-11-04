package com.example.dragger.dialogapp.view.testActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dragger.dialogapp.R;
import com.example.dragger.dialogapp.view.testActivity.poxy.FragmentProxyActivity;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private List<String> mList;
    private RecyclerView.Adapter<RecyclerView.ViewHolder> mAdapter;
//    private SlideInLeftAnimationAdapter mSlideInRightAnimationAdapter;


    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent();
        intent.setClass(this, FragmentProxyActivity.class);
        findViewById(R.id.search).setOnClickListener(v -> {
            intent.putExtra("name", "switch");
            intent.putExtra("one", "开关one");
            intent.putExtra("two", "开关two");
            startActivity(intent);
        });

        findViewById(R.id.play).setOnClickListener(v -> {
            intent.putExtra("name", "air");
            intent.putExtra("one", "空调one");
            intent.putExtra("two", "空调two");

            startActivity(intent);
        });

    }


}
