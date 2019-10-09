package com.example.processer;

import java.io.IOException;

/**
 * Desc: TODO
 * <p>
 * Author: meijie
 * PackageName: com.example.processer
 * ProjectName: ProjectTest01
 * Date: 2019/7/18 16:56
 */
public class OneInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        System.out.println("OneInterceptor start");
        Response proceed = chain.proceed(chain.request());
        System.out.println("OneInterceptor end");
        return proceed;
    }
}
