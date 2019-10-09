package com.example.processer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Desc: TODO
 * <p>
 * Author: meijie
 * PackageName: com.example.processer
 * ProjectName: ProjectTest01
 * Date: 2019/7/18 16:55
 */
public class Test {

    public static void main(String[] args) {
        try {
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void start() throws IOException {
        List<Interceptor> interceptorList = new ArrayList<>();
        OneInterceptor oneInterceptor = new OneInterceptor();
        TwoInterceptor twoInterceptor = new TwoInterceptor();
        ThreeInterceptor threeInterceptor = new ThreeInterceptor();

        interceptorList.add(oneInterceptor);
        interceptorList.add(twoInterceptor);
        interceptorList.add(oneInterceptor);
        interceptorList.add(twoInterceptor);
//        interceptorList.add(threeInterceptor);
        Request request = new Request();
        ChainImpl chain = new ChainImpl(interceptorList, 0, request);
        Response proceed = chain.proceed(request);
        System.out.println(proceed);

    }

}
