package com.example.dragger.dialogapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.dragger.dialogapp.IListener;
import com.example.dragger.dialogapp.IMyAidlInterface;
import com.example.dragger.dialogapp.TestBean;

/**
 * Desc: TODO
 * <p>
 * Author: zhoulai
 * PackageName: com.example.dragger.dialogapp.service
 * ProjectName: ProjectTest01
 * Date: 2019/9/4 14:21
 */
public class TestService extends Service {
    private static final String TAG = "TestService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        return new IMyAidlInterface.Stub() {


            @Override
            public void send(String name, IListener iListener) throws RemoteException {

            }

            @Override
            public TestBean getBean() throws RemoteException {
                return null;
            }

        };
    }


    @Override
    public void onRebind(Intent intent) {
        Log.i(TAG, "onRebind");
        super.onRebind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "IDeviceAuthImpService onUnbind");
        return super.onUnbind(intent);
    }


}
