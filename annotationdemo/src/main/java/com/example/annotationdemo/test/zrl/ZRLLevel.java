package com.example.annotationdemo.test.zrl;

/**
 * Created by ex-zhoulai on 2018/5/3.
 */

public class ZRLLevel {

    private int level;

    public ZRLLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * @return {@link Boolean}
     */
    public boolean above(ZRLLevel level) {
        if (this.level >= level.level) {
            return true;
        }
        return false;
    }

}
