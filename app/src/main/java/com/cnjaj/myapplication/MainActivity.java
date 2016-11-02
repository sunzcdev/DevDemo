package com.cnjaj.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void comment(View view) {
        ((MyApp) getApplication()).getCurrentState().comment(this, "这个商品挺不错的，值得买");
    }

    public void lookGoods(View view) {
        ((MyApp) getApplication()).getCurrentState().lookGood(this);
    }

    public void logout(View view) {
        ((MyApp) getApplication()).setUserState(new LogoutState());
    }

    public void openAlbum(View view) {
        startActivity(new Intent(this, AlbumActivity.class));
    }
}
