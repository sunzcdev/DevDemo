package com.cnjaj.myapplication.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.FrameLayout;
import com.cnjaj.myapplication.R;
import com.cnjaj.myapplication.Test;
import com.cnjaj.myapplication.utils.MyLog;
import com.cnjaj.myapplication.utils.PreferencesBean;
import com.wilddog.client.*;
import com.wilddog.wilddogcore.WilddogApp;
import com.wilddog.wilddogcore.WilddogOptions;

public class WilddogActivity extends AppCompatActivity {

    private FrameLayout framgentLayout;
    private SyncReference wilddog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wilddog);
        framgentLayout = (FrameLayout) findViewById(R.id.activity_wilddog);
        initWilddog();
    }

    private void initWilddog() {
        WilddogOptions.Builder option = new WilddogOptions.Builder();
        option.setSyncUrl("https://jzjphonevehicle.wilddogio.com/");
        WilddogApp app = WilddogApp.initializeApp(getApplication(), option.build());

        String carLicenceCode = PreferencesBean.getCarLicence(this);
        if (TextUtils.isEmpty(carLicenceCode))
            return;
        SyncReference root = WilddogSync.getInstance(app).getReference(carLicenceCode);
        wilddog = root.child("LOG");
        final SyncReference debug = root.child("DEBUG");
        debug.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null)
                    if (dataSnapshot.getValue() == null) {
                        debug.setValue(Test.LOG);
                    } else {
                        Test.LOG = Boolean.valueOf(dataSnapshot.getValue().toString());
                    }
                MyLog.i("Wilddog", Test.LOG ? "LOG启用" : "LOG关闭");
            }

            @Override
            public void onCancelled(SyncError syncError) {

            }
        });
        final SyncReference faceRecognizeCore = root.child("FaceRecognizeCore");
        faceRecognizeCore.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    if (dataSnapshot.getValue() != null) {
                        PreferencesBean.save(cardApp, HWFace.FACE_SCORE, dataSnapshot.getValue().toString());
                    } else {
                        faceRecognizeCore.setValue(FaceRecognizer.RECOGNIZE_SCORE);
                    }
                }
            }

            @Override
            public void onCancelled(SyncError syncError) {

            }
        });
        init = true;
    }
}
