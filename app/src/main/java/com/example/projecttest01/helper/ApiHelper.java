package com.example.projecttest01.helper;

import com.example.projecttest01.callback.CallBack;
import com.example.projecttest01.coreapi.Fun;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

/**
 * Created by ex-zhoulai on 2018/3/29.
 */

public class ApiHelper {

    public static final String SERVER = "http://server.jeasonlzy.com/OkHttpUtils/";
    //        public static final String SERVER = "http://192.168.1.121:8080/OkHttpUtils/";
    public static final String URL_METHOD = SERVER + "method";
    public static final String URL_CACHE = SERVER + "cache";
    public static final String URL_IMAGE = SERVER + "image";
    public static final String URL_JSONOBJECT = SERVER + "jsonObject";
    public static final String URL_JSONARRAY = SERVER + "jsonArray";
    public static final String URL_FORM_UPLOAD = SERVER + "upload";
    public static final String URL_TEXT_UPLOAD = SERVER + "uploadString";
    public static final String URL_DOWNLOAD = SERVER + "download";
    public static final String URL_REDIRECT = SERVER + "redirect";
    public static final String URL_GANK_BASE = "http://gank.io/api/data/";
    private static final String TAG = "ApiHelper";


    //这回参数被省略了
    public static AysncJob<Response<String>> getNewList(String... result) {

        return new AysncJob<Response<String>>() {

            @Override
            public void start(final CallBack<Response<String>> callBack) {
                //这里包含了我的处理逻辑
                OkGo.<String>delete(URL_METHOD)//
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                callBack.onSuccess(response);
                            }
                        });
            }
        };


    }

    public static AysncJob<String> saveTitle(final String... result) {
        return new AysncJob<String>() {
            @Override
            public void start(CallBack<String> callBack) {
                OkGo.<String>delete(URL_METHOD)//
                        .execute(new StringCallback(){

                            @Override
                            public void onSuccess(Response<String> response) {

                            }
                        });
            }
        };
    }

    public  static <T> AysncJob<T> create(final T t) {
        return new AysncJob<T>() {
            @Override
            public void start(CallBack<T> callBack) {
               callBack.onSuccess(t);
            }
        };
    }

    public void mapTest(){
           create("1").map(new Fun<String, Integer>() {
               @Override
               public Integer call(String s) {
                   return Integer.valueOf(s);
               }
           }).start(new CallBack<Integer>() {
               @Override
               public void onSuccess(Integer success) {

               }
           });

    }


}
