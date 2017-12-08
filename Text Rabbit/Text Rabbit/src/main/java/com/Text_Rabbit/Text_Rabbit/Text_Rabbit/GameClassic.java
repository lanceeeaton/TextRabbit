package com.Text_Rabbit.Text_Rabbit.Text_Rabbit;

import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameClassic extends Game
{
    Boolean changeCommitted = true;
    int currentCharIndex = 2;
    Button currentButton;
    public CountDownTimer btnTimer; // timer used for buttons
    TextView txtMain;            // where the text goes when you type
    TextView lblPrompt; // where word to be typed is displayed

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        setContentView(R.layout.activity_game_classic);
        lblPrompt = (TextView)findViewById(R.id.lblPrompt);
        setTitle("Text Rabbit");
        usersFile = Game.C_USERSFILENAME;
        scoresFile = Game.C_SCORESFILENAME;
        super.onCreate(savedInstanceState);
        setLblTimer((TextView)findViewById(R.id.lblTimer));
        setLblCorrect((TextView)findViewById(R.id.lblCorrect));
        txtMain = (TextView)findViewById(R.id.txtMain);
        populatePrompt(lblPrompt);

        btnTimer = new CountDownTimer(500, 500)
        {
            @Override
            public void onTick(long l)
            {

            }

            @Override
            public void onFinish()
            {
                changeCommitted = true;
                currentCharIndex = 2;
            }
        };
    }
    public void backSpace(View v)
    {
        String currentText = txtMain.getText().toString();
        if (!currentText.equals(""))
        {
            txtMain.setText(currentText.substring(0,currentText.length()-1));
        }

    }
    public void ButtonOnClick(View sender) // click any of the num pad buttons
    {
        if (firstTextChange == false)
        {
            firstTextChange = true;
            timer.start();
            mediaPlayer.start();
        }

        if (txtMain.getText().length() > 24)
        {
            txtMain.setText("");
            changeCommitted = true;
            currentCharIndex = 2;

        }

        Button b = (Button)sender;

        if (b != currentButton)
        {
            currentButton = b;
            changeCommitted = true;
            currentCharIndex = 2;
            btnTimer.cancel();
        }
        if (currentCharIndex == b.getText().toString().length()) // if end of btn.txt set it to beginning
        {
            currentCharIndex = 2;
        }

        String text = txtMain.getText().toString();

        if (changeCommitted)
        {
            txtMain.setText(text + b.getText().toString().substring(currentCharIndex, currentCharIndex + 1));
        }
        else
        {
            txtMain.setText(text.substring(0, text.length() - 1) + b.getText().toString().substring(currentCharIndex, currentCharIndex + 1));
        }
        if (txtMain.getText().toString().equals(lblPrompt.getText().toString()))
        {
            populatePrompt(lblPrompt);
            txtMain.setText("");
        }

        currentCharIndex++;
        changeCommitted = false;
        btnTimer.cancel();
        btnTimer.start();
    }



}
