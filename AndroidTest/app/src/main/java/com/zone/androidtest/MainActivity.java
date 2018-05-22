package com.zone.androidtest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.zone.androidtest.algorithm.SearchTest;
import com.zone.androidtest.eventbus.EventBusTestActivity;
import com.zone.androidtest.eventbus.MyEvent;
import com.zone.androidtest.finaltest.JavaConstantTest;
import com.zone.androidtest.volley.TestItemBean;
import com.zone.androidtest.volley.VolleyManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by p_zoneguo on 2018/4/10.
 */

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initUI();
        VolleyManager.getInstance().request1();
    }

    private void initUI() {
        initList();
    }

    private void initList() {
        mRecyclerView = findViewById(R.id.test_items_list);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter(buildTestItems());
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMsgEvent(MyEvent event) {

    }

    private void test1() {
        int [] inputs = new int[]{10, 8, 7, 1, 2, 4, 3, 5, 6, 9};
        int [] ouputs = SearchTest.calculate(inputs);
        for (int i = 0; i < ouputs.length; i++) {
            Log.d(TAG, inputs[i] + "-" + ouputs[i]);
        }
    }

    private void test2() {
        Intent intent = new Intent();
        intent.setClass(this, com.zone.androidtest.algorithm.MainActivity.class);
        startActivity(intent);
    }

    private void test3() {
        File dir = getDir("app", Context.MODE_PRIVATE);
        Log.d(TAG, "[test3] dir = " + dir);
    }

    private void test4() {
        Log.d(TAG, "[test4] CONSTANT1 = " + JavaConstantTest.CONSTANT1);
        Log.d(TAG, "[test4] CONSTANT = " + JavaConstantTest.sContant);
    }

    private List<TestItemBean> buildTestItems() {
        TestItemBean bean1 = new TestItemBean("test1", new TestItemBean.Action() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "test1", Toast.LENGTH_SHORT).show();
            }
        });

        TestItemBean bean2 = new TestItemBean("test2", new TestItemBean.Action() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "test2", Toast.LENGTH_SHORT).show();
            }
        });

        TestItemBean bean3 = new TestItemBean("EventBus Test", new TestItemBean.Action() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, EventBusTestActivity.class);
                startActivity(intent);
            }
        });

        List<TestItemBean> datas = new ArrayList<>();
        datas.add(bean1);
        datas.add(bean2);
        datas.add(bean3);

        return datas;
    }

}
