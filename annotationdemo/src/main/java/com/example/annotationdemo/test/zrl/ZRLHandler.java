package com.example.annotationdemo.test.zrl;

/**
 * Created by ex-zhoulai on 2018/5/3.
 */

public abstract class ZRLHandler {

    private ZRLHandler mHandler;

    //如果这次结果没有处理，那么交给下一个处理器取处理
    public void setNextHandler(ZRLHandler mHandler) {
        this.mHandler = mHandler;
    }

    //所能处理的事情
    public abstract ZRLLevel getHandletLevel();


    public abstract ZRLResponse response(ZRLRequest request);

    //处理动作,成功处理后返回的结果
    public final ZRLResponse handleRequest(ZRLRequest request) {
        ZRLResponse zrlResponse;
        // 如果满足条件就处理
        if (getHandletLevel().above(request.getLevel())) {
            zrlResponse = response(request);
        } else {
            if (null != mHandler) {
                //交给下一个处理器去处理
                zrlResponse = mHandler.handleRequest(request);
            } else {
                System.out.println("没有合适的拦截器");
                zrlResponse = null;
            }
        }
        return zrlResponse;
    }

}
