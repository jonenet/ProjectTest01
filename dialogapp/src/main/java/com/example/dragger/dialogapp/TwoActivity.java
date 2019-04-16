package com.example.dragger.dialogapp;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dragger.dialogapp.widget.UnderLineTextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Desc: TODO
 * <p>
 * Author: zhoulai
 * PackageName: com.example.dragger.dialogapp
 * ProjectName: ProjectTest01
 * Date: 2018/12/19 15:52
 */
public class TwoActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        RecyclerView rvRecycler = findViewById(R.id.rv_recycler);

        List<String> titleList = new ArrayList<>();
        titleList.add("FIRST");
        titleList.add("SECOND");
        titleList.add("THIRD");
        titleList.add("FIVE");
        GridLayoutManager layout = new GridLayoutManager(this, 4);
        rvRecycler.setLayoutManager(layout);
        rvRecycler.setAdapter(new RecyclerView.Adapter<Holder>() {
            @NonNull
            @Override
            public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_title_item, parent, false));
            }

            @Override
            public void onBindViewHolder(@NonNull Holder holder, int position) {
                holder.itemView.setTag(position);
                if(position == 0){
                    holder.ivIcon.setVisibility(View.GONE);
                    holder.tvUnderLine.setVisibility(View.VISIBLE);
                }else{
                    holder.ivIcon.setVisibility(View.VISIBLE);
                    holder.tvUnderLine.setVisibility(View.GONE);
                }
                holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(hasFocus){
                            holder.ivIcon.setVisibility(View.GONE);
                            holder.tvUnderLine.setVisibility(View.VISIBLE);
                        }else{
                            holder.ivIcon.setVisibility(View.VISIBLE);
                            holder.tvUnderLine.setVisibility(View.GONE);
                        }
                    }
                });
                holder.tvUnderLine.setText(titleList.get(position));

            }

            @Override
            public int getItemCount() {
                return titleList.size();
            }
        });
    }


    static class Holder extends RecyclerView.ViewHolder {

        UnderLineTextView tvUnderLine;
        ImageView ivIcon;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tvUnderLine = itemView.findViewById(R.id.tv_underline);
            ivIcon = itemView.findViewById(R.id.iv_icon);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onResume() {
        super.onResume();

    }
}


//    private long mCurrentKeyTime;
//    private boolean mIsConnect;

//        findViewById(R.id.btn_power_dialog).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                long clickTime = System.currentTimeMillis();
//                if (Math.abs(clickTime - mCurrentKeyTime) < 1000) {
//                    Toast.makeText(getApplicationContext(), "shot_down", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                mCurrentKeyTime = clickTime;
//                if (isScreenOn()) {
//                    DialogManager.newInstance().show(TwoActivity.this, new DialogManager.PowerDialog.OnDialogItemClickListener() {
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
//        });
//
//
//        findViewById(R.id.btn_reboot).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                connectANVServer();
//            }
//        });
//
//        findViewById(R.id.btn_shotdown).
//
//                setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (mIsConnect) {
//                            setScreenState(0);
//                        }
//                    }
//                });
//
//        findViewById(R.id.btn_screen_off).
//
//                setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (mIsConnect) {
//                            setScreenState(1);
//                        }
//                    }
//                });
//    }
//
//    private void connectANVServer() {
//        SocketManager.instance().createSocket()
//                .setConnectCallback(new ClientSocket.IConnectCallback() {
//                    @Override
//                    public void onConnected() {
//                        mIsConnect = true;
//                        Toast.makeText(TwoActivity.this, "onConnected", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onDisconnected() {
//                        mIsConnect = false;
//                        Toast.makeText(TwoActivity.this, "onDisconnected", Toast.LENGTH_SHORT).show();
//                    }
//                }).setReceiveCallback(new ClientSocket.IReceiveCallback() {
//            @Override
//            public void onResult(JSONObject result) {//接收socket server端发回的消息
//                final String methodId = result.optString("methodId");
//                String msg = result.optString("msg");
//
//                if (TextUtils.equals(methodId, "SetScreenState")) {
//                    if (TextUtils.equals(msg, "success")) {
//                        //设置开关屏成功
//                    } else {
//                        //设置开关屏失败
//                    }
//                } else if (TextUtils.equals(methodId, "Switch3D")) {
//                    if (TextUtils.equals(msg, "success")) {
//                        //切换3d成功
//                    } else {
//                        //切换3d失败
//                    }
//                }
//            }
//
//            @Override
//            public void onError(String s) {
//                System.out.println("S = " + s);
//            }
//        }).connectServer();
//    }
//
//    private void shotDown() {
//        Intent i = new Intent("android.intent.action.ACTION_REQUEST_SHUTDOWN");
//        i.putExtra("android.intent.extra.KEY_CONFIRM", false);
//        startActivity(i);
//    }
//
//    private void reboot() {
//        Intent i = new Intent("android.intent.action.REBOOT");
//        // 立即重启：1
//        i.putExtra("nowait", 1);
//        // 重启次数：1
//        i.putExtra("interval", 1);
//        // 不出现弹窗：0
//        i.putExtra("window", 0);
//        startActivity(i);
//    }
//
//    private void screenOffOrOn() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Instrumentation mInst = new Instrumentation();
//                mInst.sendKeyDownUpSync(KeyEvent.KEYCODE_POWER);
//            }
//        }).start();
//    }
//
//
//    public void setScreenState(int state) {
//        SocketManager.instance()
//                .createSocket()
//                .setMethodId("SetScreenState")
//                .setParameter("state", state)//0是关，1是开
//                .send();
//    }
//
//
//    public boolean isScreenOn() {
//        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
//        //true为打开，false为关闭
//        return powerManager.isScreenOn();
//    }
