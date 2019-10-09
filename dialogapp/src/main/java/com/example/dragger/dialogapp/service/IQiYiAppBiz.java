package com.example.dragger.dialogapp.service;

import android.util.Log;
import android.view.KeyEvent;

import com.gala.tv.voice.VoiceClient;
import com.gala.tv.voice.VoiceEvent;
import com.gala.tv.voice.VoiceEventFactory;

/**
 * Desc: TODO
 * <p>
 * Author: meijie
 * PackageName: com.lz.m02.launcher.xiri
 * ProjectName: TrunkTag1927
 * Date: 2019/7/22 17:47
 */
public class IQiYiAppBiz {

    public static final String TAG = "IQiYiAppBiz";

    public static void testSearch(VoiceClient mVoiceClient) {
        VoiceEvent voiceEvent = VoiceEventFactory.createSearchEvent("何以琛");
        Log.d(TAG, "testSearch() event = " + voiceEvent);
        boolean handled = mVoiceClient.dispatchVoiceEvent(voiceEvent);
        Log.d(TAG, "testSearch() result =  " + handled);
    }

    public static void testKeyWord(VoiceClient mVoiceClient, String keyword) {
//        String[] keywords = new String[]{"电视剧", "电影", "动漫", "综艺", "少儿", "娱乐", "音乐", "旅游", "纪录片", "搞笑", "教育", "资讯",
//                "财经", "体育", "军事", "片花", "汽车", "时尚", "母婴", "脱口秀", "科技", "最近更新"};
//        for (int i = 0; i < keywords.length; i++) {
        VoiceEvent voiceEvent = VoiceEventFactory.createKeywordsEvent(keyword);// "影视""电视""游戏""应用""设置"
        Log.d(TAG, "testKeyWord() event = " + voiceEvent);
        boolean handled = mVoiceClient.dispatchVoiceEvent(voiceEvent);
        Log.d(TAG, "testKeyWord() result =  " + handled);
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public static void testKeyEvent(VoiceClient mVoiceClient) {
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
//
//    public static void testPlay(VoiceClient mVoiceClient) {
//        VoiceEvent voiceEvent = VoiceEventFactory.createKeywordsEvent("播放");
//        Log.d(TAG, "testPlay() event = " + voiceEvent);
//        boolean handled = mVoiceClient.dispatchVoiceEvent(voiceEvent);
//        Log.d(TAG, "testPlay() result =  " + handled);
//    }

    public static void testOther(VoiceClient mVoiceClient, String key) {
        VoiceEvent voiceEvent = VoiceEventFactory.createKeywordsEvent(key);
        Log.d(TAG, "testPlay() event = " + voiceEvent);
        boolean handled = mVoiceClient.dispatchVoiceEvent(voiceEvent);
        Log.d(TAG, "testPlay() result =  " + handled);
    }

//    public static void testPause(VoiceClient mVoiceClient) {
//        VoiceEvent voiceEvent = VoiceEventFactory.createKeywordsEvent("暂停");
//        Log.d(TAG, "testPause() event = " + voiceEvent);
//        boolean handled = mVoiceClient.dispatchVoiceEvent(voiceEvent);
//        Log.d(TAG, "testPause() result =  " + handled);
//    }

    public static void testSeek_to(VoiceClient mVoiceClient,int seek) {
        VoiceEvent voiceEvent = VoiceEventFactory.createSeekToEvent(seek);
        Log.d(TAG, "testSeek_to() event = " + voiceEvent);
        boolean handled = mVoiceClient.dispatchVoiceEvent(voiceEvent);
        Log.d(TAG, "testSeek_to() result =  " + handled);
    }

    public static void testSeek_offset(VoiceClient mVoiceClient, long time) {
        VoiceEvent voiceEvent = VoiceEventFactory.createSeekOffsetEvent(-time);
        Log.d(TAG, "testSeek_offset() event = " + voiceEvent);
        boolean handled = mVoiceClient.dispatchVoiceEvent(voiceEvent);
        Log.d(TAG, "testSeek_offset() result =  " + handled);
    }

    public static void testPre(VoiceClient mVoiceClient) {
        VoiceEvent voiceEvent = VoiceEventFactory.createPreviousEpisodeEvent();
        Log.d(TAG, "testNext() event = " + voiceEvent);
        boolean handled = mVoiceClient.dispatchVoiceEvent(voiceEvent);
        Log.d(TAG, "testNext() result =  " + handled);
    }

    public static void testNext(VoiceClient mVoiceClient) {
        VoiceEvent voiceEvent = VoiceEventFactory.createNextEpisodeEvent();
        Log.d(TAG, "testNext() event = " + voiceEvent);
        boolean handled = mVoiceClient.dispatchVoiceEvent(voiceEvent);
        Log.d(TAG, "testNext() result =  " + handled);
    }

    public static void testSelect(VoiceClient mVoiceClient, int index) {
        VoiceEvent voiceEvent = VoiceEventFactory.createSelectEpisodeIndexEvent(index);
        Log.d(TAG, "testSelect() event = " + voiceEvent);
        boolean handled = mVoiceClient.dispatchVoiceEvent(voiceEvent);
        Log.d(TAG, "testSelect() result =  " + handled);
    }

    // 调起某个电视剧的某一集的 播放页面
    public static void testPlayEvent(VoiceClient mVoiceClient) {
//        VoiceEvent voiceEvent = VoiceEventFactory.createPlayEvent("锦绣未央").setChannelName("电视剧").setEpisodeIndex(2);
        VoiceEvent voiceEvent = VoiceEventFactory.createPlayEvent("战狼");
        Log.d(TAG, "testPlayEvent() event = " + voiceEvent);
        boolean handled = mVoiceClient.dispatchVoiceEvent(voiceEvent);
        Log.d(TAG, "testPlayEvent() result =  " + handled);
    }
//    public static void testQuit(VoiceClient mVoiceClient) {
//        VoiceEvent voiceEvent = VoiceEventFactory.createKeywordsEvent("退出");
//        Log.d(TAG, "testQuit() event = " + voiceEvent);
//        boolean handled = mVoiceClient.dispatchVoiceEvent(voiceEvent);
//        Log.d(TAG, "testQuit() result =  " + handled);
//    }



//    // 调起某个电影 的 播放页面
//    public static void testPlayEventForFilm(VoiceClient mVoiceClient) {
//        VoiceEvent voiceEvent = VoiceEventFactory.createPlayEvent("战狼").setChannelName("电影");
//        Log.d(TAG, "testPlayEvent() event = " + voiceEvent);
//        boolean handled = mVoiceClient.dispatchVoiceEvent(voiceEvent);
//        Log.d(TAG, "testPlayEvent() result =  " + handled);
//    }
//
//    // 调起全屏播放页面（6.4 和 7.5及以后版本支持）
//    public static void testPlayFullscreen(VoiceClient mVoiceClient) {
//        VoiceEvent voiceEvent = VoiceEventFactory.createKeywordsEvent("全屏播放");
//        Log.d(TAG, "testPlayFullscreen() event = " + voiceEvent);
//        boolean handled = mVoiceClient.dispatchVoiceEvent(voiceEvent);
//        Log.d(TAG, "testPlayFullscreen() result =  " + handled);
//    }
}
