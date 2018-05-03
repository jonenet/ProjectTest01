package com.example.annotationdemo.test.daili;

/**
 * Created by ex-zhoulai on 2018/5/3.
 */

public class StaticProxyTicketManager implements TicketManager {
    TicketManager ticketManager;//目标对象的引用

    public StaticProxyTicketManager(TicketManager ticketManager) {
        this.ticketManager = ticketManager;
    }

    @Override
    public void soldTicket() {
        checkIdentity();
        ticketManager.soldTicket();
    }

    @Override
    public void changeTicket() {
        checkIdentity();
        ticketManager.changeTicket();
    }

    @Override
    public void returnTicket() {
        checkIdentity();
        ticketManager.changeTicket();
    }
    /**
     * 身份验证
     */
    public void checkIdentity(){
        System.out.println("身份验证--------------");
    }

}