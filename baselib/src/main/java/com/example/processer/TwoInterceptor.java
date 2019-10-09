package com.example.processer;

import java.io.IOException;

/**
 * Desc: TODO
 * <p>
 * Author: meijie
 * PackageName: com.example.processer
 * ProjectName: ProjectTest01
 * Date: 2019/7/18 18:04
 */
public class TwoInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        System.out.println("TwoInterceptor start");
        Response proceed = chain.proceed(chain.request());
        System.out.println("TwoInterceptor end");
        return proceed;
    }
}
