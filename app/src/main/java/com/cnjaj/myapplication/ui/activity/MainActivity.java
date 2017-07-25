package com.cnjaj.myapplication.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.cnjaj.myapplication.R;
import com.cnjaj.myapplication.pattern.state.StatePatternActivity;
import com.cnjaj.myapplication.rx.weather.RxActivity;
import com.cnjaj.myapplication.service.MessengerActivity;
import com.jayfeng.lesscode.core.SharedPreferenceLess;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private List<NavigationItem> mNavigationList = new ArrayList<NavigationItem>() {
        {
            add(new NavigationItem("状态模式演示", StatePatternActivity.class));
            add(new NavigationItem("系统相册", AlbumActivity.class));
            add(new NavigationItem("自定义钟表", ClockActivity.class));
            add(new NavigationItem("Rx测试", RxActivity.class));
            add(new NavigationItem("服务测试", MessengerActivity.class));
            add(new NavigationItem("照相测试", CameraActivity.class));
            add(new NavigationItem("数据库测试", DbActivity.class));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView mNavigationPanel = (ListView) findViewById(R.id.navigation_panel);
        final NavigationAdapter navigationAdapter = new NavigationAdapter();
        mNavigationPanel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SharedPreferenceLess.$put(navigationAdapter.getItem(i).aClass.getSimpleName(), "onCreate");
                startActivity(new Intent(MainActivity.this, navigationAdapter.getItem(i).aClass));
            }
        });
        mNavigationPanel.setAdapter(navigationAdapter);
    }

    private static class NavigationItem {
        String title;
        Class<? extends Activity> aClass;

        NavigationItem(String key, Class<? extends Activity> clazz) {
            title = key;
            aClass = clazz;
        }
    }

    private class NavigationAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mNavigationList.size();
        }

        @Override
        public NavigationItem getItem(int i) {
            return mNavigationList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView textView;
            if (view == null) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(android.R.layout.simple_list_item_1, null);
            }
            textView = (TextView) view.findViewById(android.R.id.text1);
            textView.setText(getItem(i).title);
            return view;
        }
    }
}
