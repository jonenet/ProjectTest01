// IMyAidlInterface.aidl
package com.example.dragger.dialogapp;
import com.example.dragger.dialogapp.IListener;
import com.example.dragger.dialogapp.TestBean;
// Declare any non-default types here with import statements
/***
* 1.传递对象必须实现 Parcelable接口
* 2.定义对象aidl文件，添加加一行 parcelable TestBean;
* 3.aidl本生就是接口类型，aidl类型可直接传递
*/
interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void send(String name,IListener iPlayListener);

    TestBean getBean();

}
