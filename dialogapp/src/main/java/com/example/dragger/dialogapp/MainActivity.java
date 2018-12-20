package com.example.dragger.dialogapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import jp.wasabeef.recyclerview.adapters.SlideInLeftAnimationAdapter;
import jp.wasabeef.recyclerview.animators.SlideInRightAnimator;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private List<String> mList;
    private RecyclerView.Adapter<RecyclerView.ViewHolder> mAdapter;
    private SlideInLeftAnimationAdapter mSlideInRightAnimationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TwoActivity.class));
            }
        });
        findViewById(R.id.btn_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int posotion = mList.size() - 1;
                mList.clear();
                mAdapter.notifyDataSetChanged();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        List<String> temp = new ArrayList<>();
                        temp.add("1111111111111111");
                        temp.add("1111111111111111");
                        temp.add("1111111111111111");
                        temp.add("1111111111111111");
                        temp.add("1111111111111111");
                        temp.add("1111111111111111");
                        mList.addAll(temp);
                        mAdapter.notifyItemRangeInserted(mList.size() - temp.size(), mList.size());
                    }
                }, 300);

            }
        });

        RecyclerView rvRecycler = findViewById(R.id.rv_recycler);
        mList = new ArrayList<>();
        mAdapter = new RecyclerView.Adapter<RecyclerView.ViewHolder>() {

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                TextView tv = new TextView(parent.getContext());
                tv.setGravity(Gravity.CENTER_VERTICAL);
                tv.setPadding(10, 10, 10, 10);
                tv.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                tv.setTextSize(20);
                return new RecyclerView.ViewHolder(tv) {
                };
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                ((TextView) holder.itemView).setText(mList.get(position));
            }

            @Override
            public int getItemCount() {
                return mList.size();
            }
        };

        rvRecycler.setLayoutManager(new LinearLayoutManager(this));
        SlideInRightAnimator slideInLeftAnimator = new SlideInRightAnimator();
        slideInLeftAnimator.setAddDuration(500);
        slideInLeftAnimator.setRemoveDuration(500);
        rvRecycler.setItemAnimator(slideInLeftAnimator);
        rvRecycler.setAdapter(mAdapter);
//        rvRecycler.setAdapter(mSlideInRightAnimationAdapter);
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
