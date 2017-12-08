package com.Text_Rabbit.Text_Rabbit.Text_Rabbit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Highscores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setTitle(Game.TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);
    }
    public void btnStandardClick(View v)
    {
        startActivity(new Intent(Highscores.this,Highscores_Standard.class));
    }
    public void btnClassicClick(View v)
    {
        startActivity(new Intent(Highscores.this,Highscores_Classic.class));
    }
}
