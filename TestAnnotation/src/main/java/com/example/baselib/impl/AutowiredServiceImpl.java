package com.example.baselib.impl;

import android.util.Log;
import android.util.LruCache;

import com.example.baselib.base.AutowiredService;
import com.example.baselib.base.ISyringe;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ex-zhoulai on 2018/4/28.
 */

public class AutowiredServiceImpl implements AutowiredService {

    private List<String> blackList = new ArrayList<>();
    private LruCache<String, ISyringe> classCache = new LruCache<>(50);
    private static final String SUFFIX_AUTOWIRED = "$$Router$$Autowired";


    public static AutowiredService create() {
        return new AutowiredServiceImpl();
    }


    @Override
    public void inject(Object instance) {
        String className = instance.getClass().getName();
        if (!blackList.contains(className)) {
            ISyringe autowiredHelper = classCache.get(className);
            if (null == autowiredHelper) {  // No cache.
                try {
                    autowiredHelper = (ISyringe) Class.forName(instance.getClass().getName() + SUFFIX_AUTOWIRED)
                            .getConstructor().newInstance();
                    //注入Activity 其实目的是给成员变量赋值
                    autowiredHelper.inject(instance);
                    classCache.put(className, autowiredHelper);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            // TODO: 2017/12/21 change to specific log system
            Log.d("[DDComponent]", "[autowire] " + className + "is in blacklist, ignore data inject");
        }
    }
}
