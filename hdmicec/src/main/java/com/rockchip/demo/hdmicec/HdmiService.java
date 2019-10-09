package com.rockchip.demo.hdmicec;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.hardware.hdmi.HdmiControlManager;
import android.hardware.hdmi.HdmiDeviceInfo;
import android.hardware.hdmi.HdmiPlaybackClient;
import android.hardware.hdmi.IHdmiControlService;
import android.os.IBinder;
import android.os.ServiceManager;
import android.util.Log;
import android.view.Display;
import android.widget.Toast;



public class HdmiService extends Service {
	private static final String TAG = "HdmiService";
	private DisplayManager mDisplayManager;
	private HDMIListener mHdmiListener;
	private IHdmiControlService mControlService;
	private HdmiPlaybackClient mHdmiPlayClient;
	private HdmiControlManager mHdmiControlManager;
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onCreate() {  
		mHdmiControlManager = (HdmiControlManager)getSystemService(Context.HDMI_CONTROL_SERVICE);
		mDisplayManager = (DisplayManager)getSystemService(Context.DISPLAY_SERVICE);
		mHdmiListener = new HDMIListener();
		mDisplayManager.registerDisplayListener(mHdmiListener, null);
		mHdmiPlayClient = mHdmiControlManager.getPlaybackClient();
		IBinder playBinder = ServiceManager.getService(Context.HDMI_CONTROL_SERVICE);
    	mControlService = IHdmiControlService.Stub.asInterface(playBinder);
	}
	

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
    	// TODO Auto-generated method stub
    	return super.onStartCommand(intent, flags, startId);
    }
    
    
    @Override
    public void onDestroy() {
    	// TODO Auto-generated method stub
    	super.onDestroy();
    	mDisplayManager.unregisterDisplayListener(mHdmiListener);
    }
   
	
    /**
     * 执行oneTouchPlay
     */
    public void executeOneTouchPlay(){
 	   mHdmiPlayClient.oneTouchPlay(new HdmiPlaybackClient.OneTouchPlayCallback() {
 			@Override
 			public void onComplete(int result) {
 				if (result != HdmiControlManager.RESULT_SUCCESS) {
 					//mHdmiPlayClient.oneTouchPlay(this);
 					Log.w(TAG, "One touch play failed: " + result);
 				} else {
 					Toast.makeText(HdmiService.this, "执行OneTouchPlay成功", Toast.LENGTH_SHORT).show();

					Log.w(TAG, "One touch play succeed: " + result);
 				}
 			}
 		});
    }
    
    /**
     * 发送开机指令
     */
   public void sendActiveSource(){
	   try {
   		mControlService.sendControlCommand(0 ,mControlService.getLocalAddress(HdmiDeviceInfo.DEVICE_PLAYBACK),  0x85, new byte[]{});
   		mControlService.sendControlCommand(mControlService.getLocalAddress(HdmiDeviceInfo.DEVICE_PLAYBACK), 0 , 0x85, new byte[]{});
		} catch (Exception e) {
			Log.i(TAG, "发送开机指令出错" + e);
		}
   }
    
    class HDMIListener implements DisplayManager.DisplayListener {

		@Override
		public void onDisplayAdded(int displayId) {
			Display display = mDisplayManager.getDisplay(displayId);
			Log.i(TAG, "HDMI Add:" + display);
			sendActiveSource();
			executeOneTouchPlay();
		}

		@Override
		public void onDisplayRemoved(int displayId) {
			Display display = mDisplayManager.getDisplay(displayId);
			Log.i(TAG, "HDMI removed:" + display);
		}

		@Override
		public void onDisplayChanged(int displayId) {

		}

	}
    
}
