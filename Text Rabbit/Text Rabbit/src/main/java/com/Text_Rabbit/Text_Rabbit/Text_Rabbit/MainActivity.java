package com.Text_Rabbit.Text_Rabbit.Text_Rabbit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle(Game.TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void btnPlayClick(View v)
    {
        startActivity(new Intent(MainActivity.this,ModeSelect.class));
    }
    public void btnHighscoresClick(View v)
    {
        startActivity(new Intent(MainActivity.this,Highscores.class));
    }


}
