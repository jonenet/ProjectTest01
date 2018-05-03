package com.example.annotationdemo.test.daili;

/**
 * Created by ex-zhoulai on 2018/5/3.
 */

public interface  TicketManager {
    /**
     * 售票
     */
    public  void  soldTicket();
    /**
     * 改签
     */
    public void changeTicket();
    /**
     * 退票
     */
    public void returnTicket();
}