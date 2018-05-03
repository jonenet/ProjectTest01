package com.example.annotationdemo.test.daili;

/**
 * Created by ex-zhoulai on 2018/5/3.
 */

public class TicketManagerImpl implements TicketManager {

    @Override
    public void soldTicket() {
        //checkIdentity();
        System.out.println("售票");
    }

    @Override
    public void changeTicket(){
        //checkIdentity();
        System.out.println("改签");
    }

    @Override
    public void returnTicket() {
        //checkIdentity();
        System.out.println("退票");
    }

    /**
     * 身份验证
     */
    public void checkIdentity(){
        System.out.println("身份验证");
    }
}
