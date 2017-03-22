package com.cnjaj.myapplication.service;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Messenger;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.cnjaj.myapplication.R;

public class MessengerActivity extends BaseActivity {

    private TextView mInfoTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        mInfoTv = (TextView) findViewById(R.id.info_tv);
    }

    private void showDialog(final ISend send) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("发消息");
        final EditText view = new EditText(this);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        builder.setView(view);
        builder.setPositiveButton("发送", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String text = view.getText().toString();
                send.send(text);
            }
        });
        builder.show();
    }

    @Override
    public void onSessionCreate(Messenger messenger) {
        alert("连接成功");
    }

    private void alert(String alert) {
        mInfoTv.append(alert + "\n");
    }

    @Override
    public void onSend(String obj) {
        alert("当前发送数据:" + obj);
    }

    @Override
    public void onReceive(String obj, Messenger replyTo) {
        alert("当前接收数据" + obj);
    }

    @Override
    public void onSessionClosed() {
        alert("会话关闭");
    }

    private interface ISend {
        void send(String text);
    }

    public void sendSelfMsg(View view) {
        showDialog(new ISend() {
            @Override
            public void send(String text) {
                mClientBridge.sendSelfMsg(text);
            }
        });
    }

    public void sendServiceMsgAndReply(View view) {
        showDialog(new ISend() {
            @Override
            public void send(String text) {
                mClientBridge.sendByReply(text);
            }
        });
    }

    public void sendServiceMsg(View view) {
        showDialog(new ISend() {
            @Override
            public void send(String text) {
                mClientBridge.send(text);
            }
        });
    }

}
