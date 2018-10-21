package com.example.dragger.dialogapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {


    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        startActivity(new Intent(this, NextActivity.class).putExtra("text", "123456789"));
        list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("test  = " + i);
        }
        findViewById(R.id.bottomsheet_fragment_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showPopWindow(v, list);
                startActivity(new Intent(MainActivity.this, NextActivity.class));
            }
        });

    }


    public void showPopWindow(final View view, final List<String> list) {
        PopupWindow popupWindow = new PopupWindow();
        View contentView = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_popup_content, null, false);
        RecyclerView rvPopup = contentView.findViewById(R.id.rv_popup);
        RecyclerView.Adapter<RecyclerView.ViewHolder> adapter = new RecyclerView.Adapter<RecyclerView.ViewHolder>() {


            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                TextView tv = new TextView(parent.getContext());
                tv.setHeight(view.getHeight());
                tv.setWidth(view.getWidth());
                tv.setGravity(Gravity.CENTER_VERTICAL);
                tv.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                tv.setTextSize(20);
                return new RecyclerView.ViewHolder(tv) {
                };
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                ((TextView) holder.itemView).setText(list.get(position));
            }

            @Override
            public int getItemCount() {
                return list.size();
            }

        };
        rvPopup.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
        rvPopup.setAdapter(adapter);
        popupWindow.setContentView(contentView);
        popupWindow.setWidth(view.getMeasuredWidth() - 8);
        popupWindow.setHeight(view.getMeasuredHeight() * 5);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.showAsDropDown(view,4,0);
    }
}
