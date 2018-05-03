package com.example.annotationdemo.test.daili;

/**
 * Created by ex-zhoulai on 2018/5/3.
 */

//代理类  实现同一个接口
public class LogProxy implements TicketManager {
    TicketManager ticketManager;//目标类的引用
    public LogProxy(TicketManager ticketManager){
        this.ticketManager=ticketManager;
    }
    @Override
    public void soldTicket() {
        ticketManager.soldTicket();
        log();//后置增强
    }

    @Override
    public void changeTicket() {
        ticketManager.changeTicket();
        log();
    }

    @Override
    public void returnTicket() {
        ticketManager.returnTicket();
        log();

    }
    //增强
    private void log() {
        System.out.println("日志...");

    }

}