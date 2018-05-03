package com.example.annotationdemo.test.zrl;

/**
 * Created by ex-zhoulai on 2018/5/3.
 */

public class ZRLRequest {

    private ZRLLevel level;

    public ZRLRequest(ZRLLevel level) {
        this.level = level;
    }

    public ZRLLevel getLevel() {
        return level;
    }

    public void setLevel(ZRLLevel level) {
        this.level = level;
    }
}
