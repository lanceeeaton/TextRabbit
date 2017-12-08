package com.Text_Rabbit.Text_Rabbit.Text_Rabbit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Highscores_Standard extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setTitle(Game.TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores__standard);

        ArrayList<String> userNames = Game.readFile(Highscores_Standard.this,Game.USERSFILENAME);
        ArrayList<String> userScores = Game.readFile(Highscores_Standard.this,Game.SCORESFILENAME);

        ArrayList<User> users = new ArrayList<User>();

        for (int x = 0; x < userNames.size(); x++)
        {
            users.add(new User(userNames.get(x),Float.parseFloat(userScores.get(x))));
        }

        users.sort(Comparator.comparing(User::getScore));
        Collections.reverse(users);

        ListView listView = (ListView) findViewById(R.id.listBox);
        ArrayAdapter<User> arrayAdapter = new ArrayAdapter<User>(this,android.R.layout.simple_list_item_1,users);
        listView.setAdapter(arrayAdapter);

    }
}
