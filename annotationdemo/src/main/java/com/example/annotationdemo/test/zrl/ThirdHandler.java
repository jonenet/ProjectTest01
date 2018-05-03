package com.example.annotationdemo.test.zrl;

import com.example.annotationdemo.test.zrl.ZRLHandler;
import com.example.annotationdemo.test.zrl.ZRLLevel;
import com.example.annotationdemo.test.zrl.ZRLRequest;
import com.example.annotationdemo.test.zrl.ZRLResponse;

/**
 * Created by ex-zhoulai on 2018/5/3.
 */

public class ThirdHandler extends ZRLHandler {

    @Override
    public ZRLLevel getHandletLevel() {
        return new ZRLLevel(3);
    }

    @Override
    public ZRLResponse response(ZRLRequest request) {
        System.out.println("ThirdHandler 拦截了");
        return null;
    }
}
