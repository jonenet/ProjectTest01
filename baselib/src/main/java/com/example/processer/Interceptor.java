package com.example.processer;

import java.io.IOException;

/**
 * Desc: TODO
 * <p>
 * Author: meijie
 * PackageName: com.example.processer
 * ProjectName: ProjectTest01
 * Date: 2019/7/18 16:54
 */
public interface Interceptor {

    Response intercept(Chain chain) throws IOException;
}
