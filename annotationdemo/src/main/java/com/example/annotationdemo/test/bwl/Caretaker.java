package com.example.annotationdemo.test.bwl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ex-zhoulai on 2018/5/3.
 */

public class Caretaker {


    private Map<String, Memento> memMap = new HashMap<String, Memento>();

    public Memento getMemento(String index) {
        return memMap.get(index);
    }

    public void setMemento(String index, Memento memento) {
        this.memMap.put(index, memento);
    }

}
