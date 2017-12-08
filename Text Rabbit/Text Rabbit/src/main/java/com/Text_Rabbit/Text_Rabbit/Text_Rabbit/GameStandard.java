package com.Text_Rabbit.Text_Rabbit.Text_Rabbit;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class GameStandard extends Game
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_game_standard);
        setTitle("Text Rabbit");
        TextView lblPrompt; // where word to be typed is displayed
        usersFile = Game.USERSFILENAME;
        scoresFile = Game.SCORESFILENAME;
        setLblTimer((TextView)findViewById(R.id.lblTimer));
        setLblCorrect((TextView)findViewById(R.id.lblCorrect));
        super.onCreate(savedInstanceState);


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);


        lblPrompt = (TextView)findViewById(R.id.lblPrompt);
        populatePrompt(lblPrompt);
        final EditText txtMain = (EditText)findViewById(R.id.txtMain);
        txtMain.requestFocus();
        txtMain.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                if (firstTextChange == false)
                {
                    firstTextChange = true;
                    timer.start();
                    mediaPlayer.start();
                }
                if (txtMain.getText().toString().equals(lblPrompt.getText().toString()))
                {

                    populatePrompt(lblPrompt);
                    txtMain.setText("");
                }
            }
        });
    }
}
