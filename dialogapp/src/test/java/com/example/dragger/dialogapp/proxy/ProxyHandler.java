package com.example.dragger.dialogapp.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Desc: TODO
 * <p>
 * Author: zhoulai
 * PackageName: com.example.dragger.dialogapp.proxy
 * ProjectName: ProjectTest01
 * Date: 2019/10/17 14:02
 */
public class ProxyHandler implements InvocationHandler {

    private Object proxied;

    public ProxyHandler( Object proxied )
    {
        this.proxied = proxied;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 执行
        System.out.println("准备工作之前：");
        Object object=   method.invoke( proxied, args);
        System.out.println("工作已经做完了！");
        return object;
    }
}
