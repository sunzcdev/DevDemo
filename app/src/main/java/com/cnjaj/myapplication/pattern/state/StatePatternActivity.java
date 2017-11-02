package com.cnjaj.myapplication.pattern.state;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.cnjaj.myapplication.R;

public class StatePatternActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_pattern);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void comment(View view) {
        StateManager.getUserState().comment(this, "这个商品挺不错的，值得买");
    }

    public void lookGoods(View view) {
        StateManager.getUserState().lookGood(this);
    }

    public void logout(View view) {
        StateManager.setUserState(new LogoutState());
    }
}
