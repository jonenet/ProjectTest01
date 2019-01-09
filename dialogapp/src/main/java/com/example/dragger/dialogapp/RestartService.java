package com.example.dragger.dialogapp;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class RestartService extends Service {

    private Timer timer;
    private SimpleDateFormat simpleDateFormat;

    @SuppressLint("SimpleDateFormat")
    @Override
    public void onCreate() {
        super.onCreate();
        timer = new Timer();
        System.out.println("onCreate");
        simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                String format = simpleDateFormat.format(new Date());
                System.out.println(format);
            }
        }, 1000, 10000);
    }

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }
}
