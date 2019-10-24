// IListener.aidl
package com.example.dragger.dialogapp;

// Declare any non-default types here with import statements
import com.example.dragger.dialogapp.TestBean;

interface IListener {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
       void onError(int code);
       void onSuccess(int code,in TestBean book);
}
