package com.Text_Rabbit.Text_Rabbit.Text_Rabbit;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;

/**
 * Created by Lance Eaton on 10/8/2017.
 */

public class TextBox extends android.support.v7.widget.AppCompatEditText
{
    Context context;

    public TextBox(Context context, AttributeSet attributeSet)
    {
        super(context,attributeSet);
        this.context = context;
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent keyEvent)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK) // if back button is pressed
        {
           GameStandard.instance.finish();
        }
        return false;
    }
}
