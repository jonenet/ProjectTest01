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
public class ThreeInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        System.out.println("ThreeInterceptor");
        return new Response();
    }
}
