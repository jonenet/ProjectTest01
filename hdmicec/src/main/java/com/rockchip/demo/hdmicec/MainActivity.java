package com.rockchip.demo.hdmicec;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.hardware.hdmi.HdmiControlManager;
import android.hardware.hdmi.HdmiDeviceInfo;
import android.hardware.hdmi.HdmiPlaybackClient;
import android.hardware.hdmi.HdmiPlaybackClient.OneTouchPlayCallback;
import android.hardware.hdmi.IHdmiControlService;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.ServiceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;


public class MainActivity extends Activity {
    public static final String TAG = "HDMI_MAIN";
    private ScreenReceiver mScreenReceiver;
    private PowerStatusChangeReceiver mPowerStatusChangeReceiver;
    //private DisplayManager mDisplayManager;
    private HdmiControlManager mHdmiControlManager;
    private IHdmiControlService mControlService;
    @SuppressLint("HandlerLeak") private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.i(TAG, "handleMessage");
            sendActiveSource();
//			executeOneTouchPlay();
        }
    };
    private HdmiPlaybackClient mHdmiPlayClient;
    private int mOldTvPowerStatus = -1;

    @ViewInject(R.id.list_cec)
    private ListView mListCec;

    @ViewInject(R.id.text_box_powerstatus)
    private TextView mTextBoxPowerStatus;

    @ViewInject(R.id.text_tv_powerstatus)
    private TextView mTextTvPowerStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x.view().inject(this);
        initViewAndData();
        initEvent();


        IntentFilter powerStatusFilter = new IntentFilter();
        powerStatusFilter.addAction(HdmiControlManager.POWER_STATUS_CHANGED);
        registerReceiver(mPowerStatusChangeReceiver, powerStatusFilter);

        executeOneTouchPlay();
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_CONFIGURATION_CHANGED);
        registerReceiver(mScreenReceiver, filter);


    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mScreenReceiver);

    }


    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mPowerStatusChangeReceiver);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.i(TAG, "keyCode:" + keyCode);
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN || keyCode == KeyEvent.KEYCODE_VOLUME_UP) {

        }
        return super.onKeyDown(keyCode, event);
    }

    public void initViewAndData() {
        IBinder playBinder = ServiceManager.getService(Context.HDMI_CONTROL_SERVICE);
        mControlService = IHdmiControlService.Stub.asInterface(playBinder);
        mHdmiControlManager = (HdmiControlManager) getSystemService(Context.HDMI_CONTROL_SERVICE);
        mScreenReceiver = new ScreenReceiver();
        mPowerStatusChangeReceiver = new PowerStatusChangeReceiver();
        mHdmiPlayClient = mHdmiControlManager.getPlaybackClient();
        ArrayAdapter<String> cecAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, new String[]{"StandBy", "TouchPlay", "VolumeAdd", "VolumeLess"});
        mListCec.setAdapter(cecAdapter);
        try {
//            List<HdmiDeviceInfo> deviceList = mControlService.getDeviceList();
//            HdmiDeviceInfo activeSource = mControlService.getActiveSource();
//            int devicePowerStatus = activeSource.getDevicePowerStatus();
//            Log.i(TAG, "devicePowerStatus:" + devicePowerStatus);
            String s = getDesForPowerStatus(mControlService.getPowerStatus());
//            Log.i(TAG, "devicePowerStatus:" + getDesForPowerStatus(devicePowerStatus));
            mTextBoxPowerStatus.setText("盒子电源状态:" + s);
        } catch (Exception e) {
            Log.i(TAG, "获取电源状态发生异常:" + e);
        }

    }


    public void initEvent() {
        mListCec.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position) {
                    case 0:
//                        sendStandBy();
//					mHandler.sendEmptyMessageDelayed(100,5000);

                        break;
                    case 1:
                        sendActiveSource();
                        executeOneTouchPlay();
                        break;
                    case 2:
                        volumeAdd();
                        break;
//                        GIVE DEVICE POWER STATUS 0x40 0x8F
//                        executeOneTouchPlay();
                    case 3:
                        volumeLess();
                        break;
                    default:
                        break;
                }
            }
        });

    }


    /**
     * 发送待机命令
     */
    public void sendStandBy() {
        try {
            mControlService.sendControlCommand(mControlService.getLocalAddress(HdmiDeviceInfo.DEVICE_PLAYBACK), 0, 0x36, new byte[]{});
        } catch (Exception e) {
            Log.i(TAG, "发送待机指令出错" + e);
        }
    }

    /**
     * 发送开机指令
     */
    public void sendActiveSource() {
        try {
            mControlService.sendControlCommand(0, mControlService.getLocalAddress(HdmiDeviceInfo.DEVICE_PLAYBACK), 0x85, new byte[]{});
            mControlService.sendControlCommand(mControlService.getLocalAddress(HdmiDeviceInfo.DEVICE_PLAYBACK), 0, 0x85, new byte[]{});
        } catch (Exception e) {
            Log.i(TAG, "发送开机指令出错" + e);
        }
    }


    /**
     * 音量调节相关
     */
    public void volumeAdd() {
        try {
            mControlService.sendControlCommand(mControlService.getLocalAddress(HdmiDeviceInfo.DEVICE_PLAYBACK), 0, 0x44, new byte[]{0x41});
            mControlService.sendControlCommand(mControlService.getLocalAddress(HdmiDeviceInfo.DEVICE_PLAYBACK), 0, 0x45, new byte[]{});
        } catch (Exception e) {
            Log.i(TAG, "发送音量控制指令出错" + e);
        }
    }

    private void volumeLess() {
        try {
            mControlService.sendControlCommand(mControlService.getLocalAddress(HdmiDeviceInfo.DEVICE_PLAYBACK), 0, 0x44, new byte[]{0x42});
            mControlService.sendControlCommand(mControlService.getLocalAddress(HdmiDeviceInfo.DEVICE_PLAYBACK), 0, 0x45, new byte[]{});
        } catch (Exception e) {
            Log.i(TAG, "发送音量控制指令出错" + e);
        }
    }


    /**
     * 显示电源状态
     */
    public void showPowerStatus(int hdmiCecType, int status) {
//        try {
        if (hdmiCecType == HdmiControlManager.TYPE_HDMI_CEC_BOX) {
//                mTextBoxPowerStatus.setText(mTextBoxPowerStatus.getText().toString() + "----->" + getDesForPowerStatus(status));
        } else if (hdmiCecType == HdmiControlManager.TYPE_HDMI_CEC_TV) {
            Log.d(TAG, "status = " + status);
            if (mOldTvPowerStatus != status) {
                if (mTextTvPowerStatus.getText().toString().equals("")) {
                    mTextTvPowerStatus.setText("电视状态：");
                    mTextTvPowerStatus.setText(mTextTvPowerStatus.getText().toString() + getDesForPowerStatus(status));
                } else {
                    mTextTvPowerStatus.setText(mTextTvPowerStatus.getText().toString() + "----->" + getDesForPowerStatus(status));
                }

                mOldTvPowerStatus = status;
            }

        }
//        } catch (Exception e) {
//            Log.i(TAG, "获取电源状态出错" + e);
//        }
    }

    /**
     * 获取电源状态描述
     *
     * @param status
     * @return
     */
    public String getDesForPowerStatus(int status) {
        //public static final int POWER_STATUS_UNKNOWN = -1;
        //public static final int POWER_STATUS_ON = 0;
        //public static final int POWER_STATUS_STANDBY = 1;
        //public static final int POWER_STATUS_TRANSIENT_TO_ON = 2;
        //public static final int POWER_STATUS_TRANSIENT_TO_STANDBY = 3;
        String des = "";
        switch (status) {
            case HdmiControlManager.POWER_STATUS_UNKNOWN:
                des = "未知";
                break;
            case HdmiControlManager.POWER_STATUS_ON:
                des = "可用";
                break;
            case HdmiControlManager.POWER_STATUS_STANDBY:
                des = "待机";
                break;
            case HdmiControlManager.POWER_STATUS_TRANSIENT_TO_ON:
                des = "转换至可用";
                break;
            case HdmiControlManager.POWER_STATUS_TRANSIENT_TO_STANDBY:
                des = "转换至待机";
                break;
            default:
                break;
        }
        Log.d(TAG, "DES = " + des);
        return des;
    }

    /**
     * ִ执行 oneTouchPlay
     */
    public void executeOneTouchPlay() {
        mHdmiPlayClient.oneTouchPlay(new OneTouchPlayCallback() {
            @Override
            public void onComplete(int result) {
                if (result != HdmiControlManager.RESULT_SUCCESS) {
                    //mHdmiPlayClient.oneTouchPlay(this);
                    Log.w(TAG, "One touch play failed: " + result);
                } else {
                    //Toast.makeText(MainActivity.this, "ִ��OneTouchPlay�ɹ�", Toast.LENGTH_SHORT).show();
                    Log.w(TAG, "One touch play succeed: " + result);

                }
            }
        });
    }


    class ScreenReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "接收到广播：" + intent.getAction());
            if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                sendStandBy();
                //showPowerStatus();
            } else if (intent.getAction().equalsIgnoreCase(Intent.ACTION_SCREEN_ON)) {
                sendActiveSource();
                executeOneTouchPlay();
                //showPowerStatus();
                //sendBroadcast(new Intent());
            }
        }
    }


    class PowerStatusChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "接收到广播：" + intent.getAction());
            showPowerStatus(intent.getIntExtra(HdmiControlManager.HDMI_CEC_DEVICE_TYPE, -1), intent.getIntExtra(HdmiControlManager.HDMI_CEC_POWER_STATUS, -1));
        }
    }

}
