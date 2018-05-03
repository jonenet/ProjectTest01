package com.example.annotationdemo.test.bwl;

import java.util.Map;

/**
 * Created by ex-zhoulai on 2018/5/3.
 */

public class Memento {

    private Map<String, Object> stateMap;

    public Memento(Map<String, Object> map){
        this.stateMap = map;
    }

    public Map<String, Object> getStateMap() {
        return stateMap;
    }

    public void setStateMap(Map<String, Object> stateMap) {
        this.stateMap = stateMap;
    }

}
