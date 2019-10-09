package com.example.processer;

import java.io.IOException;
import java.util.List;

/**
 * Desc: TODO
 * <p>
 * Author: meijie
 * PackageName: com.example.processer
 * ProjectName: ProjectTest01
 * Date: 2019/7/18 16:58
 */
public class ChainImpl implements Chain {
    private List<Interceptor> mInterceptors;
    private int mTargetIndex;
    private Request mRequest;

    ChainImpl(List<Interceptor> interceptors, int targetIndex, Request request) {
        this.mInterceptors = interceptors;
        this.mTargetIndex = targetIndex;
        this.mRequest = request;
    }


    @Override
    public Response proceed(Request request) throws IOException {
        Response response = null;
        if (null != mInterceptors && mInterceptors.size() > mTargetIndex) {
            Interceptor interceptor = mInterceptors.get(mTargetIndex);
            response = interceptor.intercept(new ChainImpl(mInterceptors, mTargetIndex + 1, mRequest));
        }
        return response;
    }

    @Override
    public Request request() {
        return mRequest;
    }

    public List<Interceptor> getInterceptors() {
        return mInterceptors;
    }

    public void setInterceptors(List<Interceptor> interceptors) {
        mInterceptors = interceptors;
    }

    public int getTargetIndex() {
        return mTargetIndex;
    }

    public void setTargetIndex(int targetIndex) {
        mTargetIndex = targetIndex;
    }

    public Request getRequest() {
        return mRequest;
    }

    public void setRequest(Request request) {
        mRequest = request;
    }

}
