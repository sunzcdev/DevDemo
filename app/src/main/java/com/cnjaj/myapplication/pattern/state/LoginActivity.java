package com.cnjaj.myapplication.pattern.state;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import com.cnjaj.myapplication.R;

import java.util.Random;

public class LoginActivity extends AppCompatActivity {

    private EditText mUserNameEt, mPasswordEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mUserNameEt = (EditText) findViewById(R.id.user_name);
        mPasswordEt = (EditText) findViewById(R.id.password);
    }

    public void login(View view) {
        //TODO 开始调用登录接口进行登录校验，登录成功后设置当前状态为登录状态，并把服务器返回的用户数据保存下来
        String userName = mUserNameEt.getText().toString();
        StateManager.setUserState(new LoginState(
                this,
                new User(userName, new Random().nextInt(60), String.valueOf(System.currentTimeMillis())))
        );
        finish();
    }

    public void switchAccount(View view) {
    }
}
