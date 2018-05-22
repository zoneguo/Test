package com.zone.androidtest.volley;

/**
 * Created by p_zoneguo on 2018/5/22.
 */

public class TestItemBean {
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    private String desc;
    private Action action;

    public TestItemBean(String desc, Action action) {
        this.desc = desc;
        this.action = action;
    }

    public interface Action {
        void run();
    }
}
