package com.example.dragger.dialogapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


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
        startService(new Intent(this,RestartService.class).setPackage(getPackageName()));
        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TwoActivity.class));
            }
        });

    }


//    public void showPopWindow(final View view, final List<String> mList) {
//        PopupWindow popupWindow = new PopupWindow();
//        View contentView = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_popup_content, null, false);
//        RecyclerView rvPopup = contentView.findViewById(R.id.rv_popup);
//        RecyclerView.Adapter<RecyclerView.ViewHolder> adapter = new RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//
//
//            @Override
//            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                TextView tv = new TextView(parent.getContext());
//                tv.setHeight(view.getHeight());
//                tv.setWidth(view.getWidth());
//                tv.setGravity(Gravity.CENTER_VERTICAL);
//                tv.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                tv.setTextSize(20);
//                return new RecyclerView.ViewHolder(tv) {
//                };
//            }
//
//            @Override
//            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//                ((TextView) holder.itemView).setText(mList.get(position));
//            }
//
//            @Override
//            public int getItemCount() {
//                return mList.size();
//            }
//
//        };
//        rvPopup.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
//        rvPopup.setAdapter(adapter);
//        popupWindow.setContentView(contentView);
//        popupWindow.setWidth(view.getMeasuredWidth() - 8);
//        popupWindow.setHeight(view.getMeasuredHeight() * 5);
//        popupWindow.setOutsideTouchable(true);
//        popupWindow.setFocusable(true);
//        popupWindow.setTouchable(true);
//        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
//        popupWindow.showAsDropDown(view, 4, 0);
//    }


//    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            if (null != intent && "com.lz.key.power".equals(intent.getAction())) {
//
//                long clickTime = System.currentTimeMillis();
//                if (Math.abs(clickTime - mCurrentKeyTime) < 1000) {
//                    Toast.makeText(context, "shot_down", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                mCurrentKeyTime = clickTime;
//                if (isScreenOn()) {
//                    DialogManager.newInstance().show(MainActivity.this, new DialogManager.PowerDialog.OnDialogItemClickListener() {
//                        @Override
//                        public void onItemClick(int position) {
//                            switch (position) {
//                                case DialogManager.PowerDialog.CLICK_SHOT_DOWN:
//                                    shotDown();
//                                    break;
//                                case DialogManager.PowerDialog.CLICK_SCREEN_OFF:
//                                    screenOffOrOn();
//                                    break;
//                                case DialogManager.PowerDialog.CLICK_RESTART:
//                                    reboot();
//                                    break;
//                            }
//                        }
//                    });
//                } else {
//                    screenOffOrOn();
//                }
//            }
//        }
//    };
//    IntentFilter filter = new IntentFilter();
//        filter.addAction("com.lz.key.power");
//        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(broadcastReceiver, filter);
}
