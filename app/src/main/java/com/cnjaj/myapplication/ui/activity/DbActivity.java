package com.cnjaj.myapplication.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.cnjaj.myapplication.R;
import com.cnjaj.myapplication.dao.DBDao;
import com.cnjaj.myapplication.dao.DataBean;
import com.cnjaj.myapplication.dao.Wrapper;
import com.cnjaj.myapplication.utils.DateUtils;

import java.util.List;

public class DbActivity extends AppCompatActivity {

    private DBDao dbDao;
    private ArrayAdapter<DataBean> adapter;
    private byte abyte;
    private double adouble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        ListView listview = (ListView) findViewById(R.id.dataList);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listview.setAdapter(adapter);
        dbDao = new DBDao(this);
    }

    private void notifyListviewUpdate() {
        List<DataBean> beans = dbDao.queryAllByIsUpload(DataBean.class);
        adapter.clear();
        adapter.addAll(beans);
    }

    public void insertData(View view) {
        DataBean message = new DataBean();
        Wrapper wrapper = new Wrapper();
        wrapper.setTitle("Hello");
        wrapper.setMessage("World");
        message.setWrapper(wrapper);
        message.setStringValue(((Button) view).getText().toString());
        message.setBooleanValue(true);
        message.setByteValue(abyte++);
        message.setDoubleValue(adouble++);
        message.setStringValue(DateUtils.getCurrentTime());
        dbDao.insert(message);
        notifyListviewUpdate();
    }

    public void updateData(View view) {
        List<DataBean> beans = dbDao.queryAllByIsUpload(DataBean.class);
        if (!beans.isEmpty()) {
            DataBean bean = beans.get(0);
            bean.setStringValue(DateUtils.getCurrentTime());
            bean.setIsupload(1);
            dbDao.update(bean);
        }
        notifyListviewUpdate();
    }

    public void delData(View view) {
        DataBean bean = adapter.getItem(0);
        dbDao.delByBeans(DataBean.class, bean);
        notifyListviewUpdate();
    }
}
