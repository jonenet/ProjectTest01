package com.example.dragger.dialogapp.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.example.dragger.dialogapp.R;

/**
 * Desc: TODO
 * <p>
 * Author: meijie
 * PackageName: com.example.dragger.dialogapp.widget
 * ProjectName: ProjectTest01
 * Date: 2018/12/12 13:55
 */
public class DialogManager {


    private PowerDialog mPowerDialog;

    private static DialogManager instance;

    private DialogManager() {}

    public static DialogManager newInstance() {
        if (null == instance) {
            synchronized (PowerDialog.class) {
                if (null == instance) {
                    instance = new DialogManager();
                }
            }
        }
        return instance;
    }


    public PowerDialog getDialog(Context context) {
        if (null == mPowerDialog) {
            mPowerDialog = new PowerDialog(context);
        }
        return mPowerDialog;
    }


    public void show(Context context, PowerDialog.OnDialogItemClickListener listener) {
        PowerDialog dialogInstance = getDialog(context);
        if (!dialogInstance.isShowing()) {
            dialogInstance.show();
            dialogInstance.setOnItemClickListener(listener);
        }
    }


    @SuppressWarnings("all")
    public static class PowerDialog extends Dialog implements View.OnClickListener {

        public static final int CLICK_SHOT_DOWN = 0X000;
        public static final int CLICK_SCREEN_OFF = 0X001;
        public static final int CLICK_RESTART = 0X002;


        public static final int CANCEL_DIALOG = 0X100;
        Handler mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == CANCEL_DIALOG) {
                    dismiss();
                }
            }
        };


        private Context mContext;
        private PowerDialog.OnDialogItemClickListener mListener;

        public PowerDialog(@NonNull Context context) {
            super(context, R.style.dialog);
            mContext = context;
            initView();
            setCancelable(true);
            setCanceledOnTouchOutside(false);
            getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        }

        private void initView() {
//        View rootView = View.inflate(mContext, R.layout.dialog_power_key, null);
            View rootView = LayoutInflater.from(mContext).inflate(R.layout.dialog_power_key, null, true);
            rootView.findViewById(R.id.tv_shot_down).setOnClickListener(this);
            rootView.findViewById(R.id.tv_screen_off).setOnClickListener(this);
            rootView.findViewById(R.id.tv_restart).setOnClickListener(this);
            setContentView(rootView);
        }


        @Override
        public boolean dispatchKeyEvent(@NonNull KeyEvent event) {
            sendDismissHandler(5000);
            return super.dispatchKeyEvent(event);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tv_shot_down:
                    notifyClick(CLICK_SHOT_DOWN);
                    dismiss();
                    break;
                case R.id.tv_screen_off:
                    notifyClick(CLICK_SCREEN_OFF);
                    dismiss();
                    break;
                case R.id.tv_restart:
                    notifyClick(CLICK_RESTART);
                    dismiss();
                    break;
            }
        }

        @Override
        public void show() {
            super.show();
            sendDismissHandler(5000);
        }

        public void sendDismissHandler(int time) {
            if (mHandler.hasMessages(CANCEL_DIALOG)) {
                mHandler.removeMessages(CANCEL_DIALOG);
            }
            mHandler.sendEmptyMessageDelayed(CANCEL_DIALOG, time);
        }

        @Override
        public void dismiss() {
            super.dismiss();
            if (mHandler.hasMessages(CANCEL_DIALOG)) {
                mHandler.removeMessages(CANCEL_DIALOG);
            }
        }

        public void notifyClick(int position) {
            if (null != mListener) {
                mListener.onItemClick(position);
            }
        }

        public void setOnItemClickListener(PowerDialog.OnDialogItemClickListener listener) {mListener = listener;}

        public interface OnDialogItemClickListener {
            void onItemClick(int position);
        }

    }

}
