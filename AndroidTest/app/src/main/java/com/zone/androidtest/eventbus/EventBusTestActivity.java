package com.zone.androidtest.eventbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zone.androidtest.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by p_zoneguo on 2018/5/22.
 */

public class EventBusTestActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mSendBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_event_bus);

        init();
    }

    private void init() {
        initVars();
        initUI();
    }

    private void initVars() {
        EventBus.getDefault().register(this);
    }

    private void initUI() {
        mSendBtn = (Button) findViewById(R.id.btn_send);
        mSendBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MyEvent event = new MyEvent();
        event.setMsg("Custom Event");
        EventBus.getDefault().post(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 0, sticky = false)
    public void onReceiveEvent(MyEvent event) {
        Toast.makeText(EventBusTestActivity.this, event.getMsg(), Toast.LENGTH_SHORT).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 0, sticky = false)
    public void onReceiveEvent1(MyEvent event) {
        Toast.makeText(EventBusTestActivity.this, event.getMsg() + "heihei", Toast.LENGTH_SHORT).show();
    }
}
