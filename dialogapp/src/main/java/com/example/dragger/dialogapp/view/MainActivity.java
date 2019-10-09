package com.example.dragger.dialogapp.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dragger.dialogapp.R;
import com.example.dragger.dialogapp.view.testActivity.FragmentProxyActivity;
import com.gala.tv.voice.VoiceClient;
import com.gala.tv.voice.VoiceEvent;
import com.gala.tv.voice.VoiceEventFactory;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private List<String> mList;
    private RecyclerView.Adapter<RecyclerView.ViewHolder> mAdapter;
//    private SlideInLeftAnimationAdapter mSlideInRightAnimationAdapter;

    VoiceClient mVoiceClient;


    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VoiceClient.initialize(this.getApplicationContext(), "com.gitvvideo.lanzheng"); // 此为测试数据，实际使用时填入指定要连接的server的apk包名
        mVoiceClient = VoiceClient.instance();
        Intent intent = new Intent();
        intent.setClass(this, FragmentProxyActivity.class);
        findViewById(R.id.search).setOnClickListener(v -> {
            intent.putExtra("name", "switch");
            intent.putExtra("one", "开关one");
            intent.putExtra("two", "开关two");
            startActivity(intent);
        });

        findViewById(R.id.play).setOnClickListener(v -> {
            intent.putExtra("name", "air");
            intent.putExtra("one", "空调one");
            intent.putExtra("two", "空调two");

            startActivity(intent);
        });

        findViewById(R.id.pause).setOnClickListener(v -> {
            new Thread(this::testKeyWord).start();
        });

        mVoiceClient.setListener(new VoiceClient.ConnectionListener() {
            @Override
            public void onDisconnected(int arg0) {
                Log.d(TAG, "onDisconnected, code=" + arg0);
            }

            @Override
            public void onConnected() {
                Log.d(TAG, "onConnected");

            }
        });
        mVoiceClient.connect();

    }


    private void testSearch() {
        VoiceEvent voiceEvent = VoiceEventFactory.createSearchEvent("何以琛");
        Log.d(TAG, "testSearch() event = " + voiceEvent);
        boolean handled = mVoiceClient.dispatchVoiceEvent(voiceEvent);
        Log.d(TAG, "testSearch() result =  " + handled);
    }

    private void testKeyWord() {
        String[] keywords = new String[]{"电视剧", "电影", "动漫", "综艺", "少儿", "娱乐", "音乐", "旅游", "纪录片", "搞笑", "教育", "资讯",
                "财经", "体育", "军事", "片花", "汽车", "时尚", "母婴", "脱口秀", "科技", "最近更新"};
        for (int i = 0; i < keywords.length; i++) {
            VoiceEvent voiceEvent = VoiceEventFactory.createKeywordsEvent(keywords[i]);// "影视""电视""游戏""应用""设置"
            Log.d(TAG, "testKeyWord() event = " + voiceEvent);
            boolean handled = mVoiceClient.dispatchVoiceEvent(voiceEvent);
            Log.d(TAG, "testKeyWord() result =  " + handled);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void testKeyEvent() {
        int[] keycodes = new int[]{KeyEvent.KEYCODE_DPAD_LEFT, KeyEvent.KEYCODE_DPAD_RIGHT, KeyEvent.KEYCODE_PAGE_UP,
                KeyEvent.KEYCODE_PAGE_DOWN};
        for (int keycode : keycodes) {
            VoiceEvent voiceEvent = VoiceEventFactory.createKeyEvent(keycode);
            Log.d(TAG, "testSearch() event = " + voiceEvent);
            boolean handled = mVoiceClient.dispatchVoiceEvent(voiceEvent);
            Log.d(TAG, "testSearch() result =  " + handled + ", keycode=" + keycode);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void testPlay() {
        VoiceEvent voiceEvent = VoiceEventFactory.createKeywordsEvent("播放");
        Log.d(TAG, "testPlay() event = " + voiceEvent);
        boolean handled = mVoiceClient.dispatchVoiceEvent(voiceEvent);
        Log.d(TAG, "testPlay() result =  " + handled);
    }

    private void testPause() {
        VoiceEvent voiceEvent = VoiceEventFactory.createKeywordsEvent("暂停");
        Log.d(TAG, "testPause() event = " + voiceEvent);
        boolean handled = mVoiceClient.dispatchVoiceEvent(voiceEvent);
        Log.d(TAG, "testPause() result =  " + handled);
    }

    private void testSeek_to() {
        VoiceEvent voiceEvent = VoiceEventFactory.createSeekToEvent(600000);
        Log.d(TAG, "testSeek_to() event = " + voiceEvent);
        boolean handled = mVoiceClient.dispatchVoiceEvent(voiceEvent);
        Log.d(TAG, "testSeek_to() result =  " + handled);
    }

    private void testSeek_offset() {
        VoiceEvent voiceEvent = VoiceEventFactory.createSeekOffsetEvent(-100000);
        Log.d(TAG, "testSeek_offset() event = " + voiceEvent);
        boolean handled = mVoiceClient.dispatchVoiceEvent(voiceEvent);
        Log.d(TAG, "testSeek_offset() result =  " + handled);
    }

    private void testPre() {
        VoiceEvent voiceEvent = VoiceEventFactory.createPreviousEpisodeEvent();
        Log.d(TAG, "testNext() event = " + voiceEvent);
        boolean handled = mVoiceClient.dispatchVoiceEvent(voiceEvent);
        Log.d(TAG, "testNext() result =  " + handled);
    }

    private void testNext() {
        VoiceEvent voiceEvent = VoiceEventFactory.createNextEpisodeEvent();
        Log.d(TAG, "testNext() event = " + voiceEvent);
        boolean handled = mVoiceClient.dispatchVoiceEvent(voiceEvent);
        Log.d(TAG, "testNext() result =  " + handled);
    }

    private void testSelect(int index) {
        VoiceEvent voiceEvent = VoiceEventFactory.createSelectEpisodeIndexEvent(index);
        Log.d(TAG, "testSelect() event = " + voiceEvent);
        boolean handled = mVoiceClient.dispatchVoiceEvent(voiceEvent);
        Log.d(TAG, "testSelect() result =  " + handled);
    }

    private void testQuit() {
        VoiceEvent voiceEvent = VoiceEventFactory.createKeywordsEvent("退出");
        Log.d(TAG, "testQuit() event = " + voiceEvent);
        boolean handled = mVoiceClient.dispatchVoiceEvent(voiceEvent);
        Log.d(TAG, "testQuit() result =  " + handled);
    }

    // 调起某个电视剧的某一集的 播放页面
    private void testPlayEvent() {
        VoiceEvent voiceEvent = VoiceEventFactory.createPlayEvent("锦绣未央").setChannelName("电视剧").setEpisodeIndex(2);
        Log.d(TAG, "testPlayEvent() event = " + voiceEvent);
        boolean handled = mVoiceClient.dispatchVoiceEvent(voiceEvent);
        Log.d(TAG, "testPlayEvent() result =  " + handled);
    }

    // 调起某个电影 的 播放页面
    private void testPlayEventForFilm() {
        VoiceEvent voiceEvent = VoiceEventFactory.createPlayEvent("战狼").setChannelName("电影");
        Log.d(TAG, "testPlayEvent() event = " + voiceEvent);
        boolean handled = mVoiceClient.dispatchVoiceEvent(voiceEvent);
        Log.d(TAG, "testPlayEvent() result =  " + handled);
    }

    // 调起全屏播放页面（6.4 和 7.5及以后版本支持）
    private void testPlayFullscreen() {
        VoiceEvent voiceEvent = VoiceEventFactory.createKeywordsEvent("全屏播放");
        Log.d(TAG, "testPlayFullscreen() event = " + voiceEvent);
        boolean handled = mVoiceClient.dispatchVoiceEvent(voiceEvent);
        Log.d(TAG, "testPlayFullscreen() result =  " + handled);
    }


}
