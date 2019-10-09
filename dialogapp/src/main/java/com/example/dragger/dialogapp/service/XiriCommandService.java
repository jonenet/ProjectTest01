package com.example.dragger.dialogapp.service;

import android.content.Intent;
import android.util.Log;

import com.gala.tv.voice.VoiceClient;
import com.iflytek.xiri.AppService;
import com.iflytek.xiri.Feedback;
import com.lunzn.xiriparsejar.model.xiri.ResponseModel;
import com.lunzn.xiriparsejar.parse.ParseResponseBiz;

import java.util.HashMap;

public class XiriCommandService extends AppService {

    private static final String TAG = "XiriCommandService";


    private ParseResponseBiz mParseResponseBiz;

    boolean iQiYiConnect = false;

    VoiceClient mVoiceClient;
    Feedback mFeedback;


    @Override
    public void onCreate() {
        super.onCreate();
        //初始化工具
        initData();
        //初始化语音监听
        initListen();

        //初始化8兼容8.0
        mParseResponseBiz = new ParseResponseBiz(this);

        VoiceClient.initialize(this.getApplicationContext(), "com.gitvvideo.lanzheng"); // 此为测试数据，实际使用时填入指定要连接的server的apk包名
        mVoiceClient = VoiceClient.instance();
        mVoiceClient.setListener(new VoiceClient.ConnectionListener() {
            @Override
            public void onDisconnected(int arg0) {
                Log.d(TAG, "onDisconnected, code=" + arg0);
            }

            @Override
            public void onConnected() {
                Log.d(TAG, "onConnected");
                iQiYiConnect = true;

            }
        });
        mVoiceClient.connect();
    }


    /**
     * 初始化 语音监听
     */
    private void initListen() {
        // 应用
        setAppListener(myAppListener);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mFeedback = new Feedback(XiriCommandService.this);
    }

    @Override
    protected void onInit() {
    }

    /**
     * 全程语音监听
     */
    IAppListener myAppListener = new IAppListener() {

        @Override
        public void onExecute(Intent intent) {
            mFeedback.begin(intent);
            Log.d("TTTT", "appservice:" + intent.toURI());
            //兼容7+语点
            String semantic = intent.getStringExtra("semantic");
            Log.d("TTTT#onExecute", "semantic = " + semantic);
            String command = "";
            if (null != semantic) {
                ResponseModel responseModel = mParseResponseBiz.parseResponse(intent, semantic, initXiriMap());
                if (null != responseModel) {
                    command = responseModel.getCommand();
                }
            } else {
                if (intent.hasExtra("_command")) {
                    command = intent.getStringExtra("_command");
                }
            }
            if (iQiYiConnect) {
                switch (command) {
                    case "订阅":
                    case "取消订阅":
                    case "高清":
                    case "流畅":
                    case "关闭跳过片头片尾":
                    case "开启跳过片头片尾":
                    case "全屏":
                    case "取消全屏":
                    case "上一集":
                    case "下一集":
                    case "最后一集":
                    case "播放":
                    case "暂停":
                    case "退出":
                    case "快进":
                    case "快退":
                        IQiYiAppBiz.testOther(mVoiceClient, command);
                        break;
                    case "搜索":
                        IQiYiAppBiz.testSearch(mVoiceClient);
                        break;
                    case "播放第三集":
                        IQiYiAppBiz.testSelect(mVoiceClient, 3);
                        break;
                    case "播放进度向后":
                        IQiYiAppBiz.testSeek_offset(mVoiceClient, 5 * 1000);
                        break;
                    case "播放进度向前":
                        IQiYiAppBiz.testSeek_offset(mVoiceClient, -5 * 1000);
                        break;
                    case "播放到":
                        IQiYiAppBiz.testSeek_to(mVoiceClient, 5 * 60 * 1000);
                        break;
                    case "播放战狼":
                        IQiYiAppBiz.testPlayEvent(mVoiceClient);
                        break;
                }
            }
            mFeedback.feedback(String.valueOf(command), Feedback.DIALOG);
        }

        @Override
        public void onTextFilter(Intent intent) {
        }
    };


    private HashMap<String, String> initXiriMap() {
        HashMap<String, String> xiriText = new HashMap<>();
        xiriText.put("news", "新闻");
        xiriText.put("cache", "清除缓存");
        xiriText.put("game", "游戏");
        xiriText.put("education", "教育");
        xiriText.put("sports", "体育");
        xiriText.put("album", "影集");
        xiriText.put("tourism", "旅游");
        xiriText.put("k4", "4K专区");
        xiriText.put("morden", "时尚");
        xiriText.put("nba", "NBA");
        xiriText.put("life", "生活");
        xiriText.put("home", "主页");
        xiriText.put("playrecord", "播放记录");
        xiriText.put("special", "专题");
        xiriText.put("car", "汽车");
        xiriText.put("ronmei", "融媒视界");
        xiriText.put("accelerate", "一键加速");
        xiriText.put("vip", "VIP");
        xiriText.put("selected", "精选");
        xiriText.put("documentary", "纪录片");
        xiriText.put("child", "少儿");
        xiriText.put("amusement", "娱乐");
        xiriText.put("netspeed", "网络测速");
        return xiriText;
    }
}
