package com.example.dragger.dialogapp;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.example.dragger.dialogapp.widget.DialogManager;
import com.inovel.inovelsocketlib.ClientSocket;
import com.inovel.inovelsocketlib.SocketManager;

import org.json.JSONObject;

public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private long mCurrentKeyTime;
    private boolean mIsConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        startActivity(new Intent(this, NextActivity.class).putExtra("text", "123456789"));
        findViewById(R.id.btn_power_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long clickTime = System.currentTimeMillis();
                if (Math.abs(clickTime - mCurrentKeyTime) < 1000) {
                    Toast.makeText(getApplicationContext(), "shot_down", Toast.LENGTH_SHORT).show();
                    return;
                }
                mCurrentKeyTime = clickTime;
                if (isScreenOn()) {
                    DialogManager.newInstance().show(MainActivity.this, new DialogManager.PowerDialog.OnDialogItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            switch (position) {
                                case DialogManager.PowerDialog.CLICK_SHOT_DOWN:
                                    shotDown();
                                    break;
                                case DialogManager.PowerDialog.CLICK_SCREEN_OFF:
                                    screenOffOrOn();
                                    break;
                                case DialogManager.PowerDialog.CLICK_RESTART:
                                    reboot();
                                    break;
                            }
                        }
                    });
                } else {
                    screenOffOrOn();
                }
            }
        });


        findViewById(R.id.btn_reboot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectANVServer();
            }
        });

        findViewById(R.id.btn_shotdown).

                setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(mIsConnect){
                            setScreenState(0);
                        }
                    }
                });

        findViewById(R.id.btn_screen_off).

                setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(mIsConnect){
                            setScreenState(1);
                        }
                    }
                });
    }

    private void connectANVServer() {
        SocketManager.instance().createSocket()
                .setConnectCallback(new ClientSocket.IConnectCallback() {
                    @Override
                    public void onConnected() {
                        mIsConnect = true;
                        Toast.makeText(MainActivity.this, "onConnected", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDisconnected() {
                        mIsConnect = false;
                        Toast.makeText(MainActivity.this, "onDisconnected", Toast.LENGTH_SHORT).show();
                    }
                }).setReceiveCallback(new ClientSocket.IReceiveCallback() {
            @Override
            public void onResult(JSONObject result) {//接收socket server端发回的消息
                final String methodId = result.optString("methodId");
                String msg = result.optString("msg");

                if (TextUtils.equals(methodId, "SetScreenState")) {
                    if (TextUtils.equals(msg, "success")) {
                        //设置开关屏成功
                    } else {
                        //设置开关屏失败
                    }
                } else if (TextUtils.equals(methodId, "Switch3D")) {
                    if (TextUtils.equals(msg, "success")) {
                        //切换3d成功
                    } else {
                        //切换3d失败
                    }
                }
            }

            @Override
            public void onError(String s) {
                System.out.println("S = "+s);
            }
        }).connectServer();
    }

    private void shotDown() {
        Intent i = new Intent("android.intent.action.ACTION_REQUEST_SHUTDOWN");
        i.putExtra("android.intent.extra.KEY_CONFIRM", false);
        startActivity(i);
    }

    private void reboot() {
        Intent i = new Intent("android.intent.action.REBOOT");
        // 立即重启：1
        i.putExtra("nowait", 1);
        // 重启次数：1
        i.putExtra("interval", 1);
        // 不出现弹窗：0
        i.putExtra("window", 0);
        startActivity(i);
    }

    private void screenOffOrOn() {
//        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
//        PowerManager.WakeLock wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "TAG");
//        wakeLock.acquire();
//        new Handler().postDelayed(new Runnable(){
//            public void run(){
//                wakeLock.release();
//            }
//        }, 10*1000);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Instrumentation mInst = new Instrumentation();
                mInst.sendKeyDownUpSync(KeyEvent.KEYCODE_POWER);
            }
        }).start();
    }


    public void setScreenState(int state) {
        SocketManager.instance()
                .createSocket()
                .setMethodId("SetScreenState")
                .setParameter("state", state)//0是关，1是开
                .send();
    }


    public boolean isScreenOn() {
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        //true为打开，false为关闭
        return powerManager.isScreenOn();
    }




//    public void showPopWindow(final View view, final List<String> list) {
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
//                ((TextView) holder.itemView).setText(list.get(position));
//            }
//
//            @Override
//            public int getItemCount() {
//                return list.size();
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
