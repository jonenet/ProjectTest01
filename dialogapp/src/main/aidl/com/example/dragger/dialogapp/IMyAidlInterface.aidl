// IMyAidlInterface.aidl
package com.example.dragger.dialogapp;

// Declare any non-default types here with import statements
import com.example.dragger.dialogapp.IListener;
import com.example.dragger.dialogapp.TestBean;
interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     void send(String name,IListener iPlayListener);

     TestBean getBean();
}



