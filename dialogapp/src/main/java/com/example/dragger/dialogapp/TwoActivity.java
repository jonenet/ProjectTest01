package com.example.dragger.dialogapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.dragger.dialogapp.fragment.MenuFragment;

import androidx.annotation.Nullable;

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
    }

    @Override
    protected void onResume() {
        super.onResume();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MenuFragment menuFragment = new MenuFragment();
        fragmentTransaction.replace(R.id.fl_content, menuFragment);
        fragmentTransaction.commit();
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
