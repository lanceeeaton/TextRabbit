package com.Text_Rabbit.Text_Rabbit.Text_Rabbit;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by Lance Eaton on 11/18/2017.
 */

public abstract class Game extends AppCompatActivity
{
    public static final String TITLE = "Text Rabbit";
    protected String usersFile;
    protected String scoresFile;
    public static final String USERSFILENAME = "users.txt";
    public static final String SCORESFILENAME = "scores.txt";
    public static final String C_USERSFILENAME = "c_users.txt";
    public static final String C_SCORESFILENAME = "c_scores.txt";
    final String wordFile = "wordlist.txt";

    ArrayList<String> users = new ArrayList<String>();
    ArrayList<Float> scores = new ArrayList<Float>();
    ArrayList<String> wordList = new ArrayList<String>();

    public static Game instance; // hold current instance
    AlertDialog.Builder dialogBox;
    EditText txtName; // where user enters name in alert dialog
    protected CountDownTimer timer; // game timer

    MediaPlayer mediaPlayer;
    protected boolean firstTextChange = false;
    protected String name; // what was entered in txtName
    protected float wpmScore;
    protected int charCount = 0; // count of all chars entered that made up correctly typed words
    protected int placeInHighscores;
    protected TextView lblTimer;
    protected TextView lblCorrect;
    final Animation in = new AlphaAnimation(0.0f, 1.0f);
    final Animation out = new AlphaAnimation(1.0f, 0.0f);
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        instance = this;
        dialogBox = new AlertDialog.Builder(this); // creating the alert dialog
        dialogBox.setCancelable(false); // cant exit out
        txtName = new EditText(this);
        txtName.setInputType(InputType.TYPE_CLASS_TEXT);
        dialogBox.setView(txtName);
        wordList = readWordFile(wordFile);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.fastspeed);
        //mediaPlayer.setLooping(true);
        in.setDuration(1000);
        //out.setDuration(1000);

        in.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationEnd(Animation animation)
            {
                lblCorrect.setText("");
                //lblCorrect.startAnimation(out);

            }
            @Override
            public void onAnimationStart(Animation animation)
            {

            }
            @Override
            public void onAnimationRepeat(Animation animation)
            {

            }
        });


        dialogBox.setPositiveButton("Okay",new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                name = txtName.getText().toString();

                users = readFile(usersFile);
                if (name.trim().equals(""))
                {
                    name = "Anonymous";
                }
                users.add(name);
                // getting stuff out of scoresFile and putting it in scores
                scores = new ArrayList<Float>(readFile(scoresFile).stream().map(Float::parseFloat).collect(Collectors.toList()));
                scores.add(wpmScore);

                // adds score to list and file

                writeToFile(new ArrayList<String>(scores.stream().map(Object::toString).collect(Collectors.toList())),scoresFile);

                writeToFile(users,usersFile);

                Game.instance.finish();
            }
        });

        timer = new CountDownTimer(20000,1000)
        {
            @Override
            public void onTick(long l)
            {
                lblTimer.setText(Long.toString(l/1000));
            }
            @Override
            public void onFinish()
            {
                wpmScore = ((charCount/5)/(20.0F/60.0F)); // calc wpm
                ArrayList<Float> tempScores = new ArrayList<Float>();
                tempScores = new ArrayList<Float>(readFile(scoresFile).stream().map(Float::parseFloat).collect(Collectors.toList()));
                tempScores.add(wpmScore);

                Collections.sort(tempScores);
                Collections.reverse(tempScores);
                placeInHighscores = tempScores.indexOf(wpmScore) + 1;

                dialogBox.setTitle("Your WPM: " + Float.toString(wpmScore));
                dialogBox.setMessage("Your Place: " + Integer.toString(placeInHighscores) + "\n\nName:");

                AlertDialog alert = dialogBox.create();
                alert.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                alert.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

                dialogBox.show();

            }
        };
    }

    public ArrayList<String> readWordFile(String fileName)
    {
        ArrayList<String> list = new ArrayList<String>();
        AssetManager am = this.getAssets();
        InputStream is = null;
        try
        {
            is = am.open(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null )
            {
                list.add(line);
            }
            is.close();
        }
        catch(Exception e)
        {
            System.exit(1);
        }
        finally
        {
            try
            {
                if (is != null) is.close();
            }
            catch (Exception e)
            {
                // do nothing
            }

        }
        return list;
    }
    public static ArrayList<String> readFile(Context c,String fileName)
    {
        ArrayList<String> list = new ArrayList<String>();
        FileInputStream stream = null;
        try
        {
            stream = c.openFileInput(fileName);
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            list.clear();
            while ((line = bufferedReader.readLine()) != null)
            {
                list.add(line);
            }
        }
        catch(Exception e)
        {
            return list;
        }
        finally
        {
            try
            {
                if (stream != null) stream.close();
            }
            catch (Exception e)
            {
                // do nothing
            }

        }
        return list;
    }

    public ArrayList<String> readFile(String fileName)
    {
        return Game.readFile(this,fileName);
    }


    public void writeToFile(ArrayList<String> list, String fileName)
    {
        try
        {
            FileOutputStream stream = this.openFileOutput(fileName,Context.MODE_PRIVATE);

            for (String item : list)
            {
                stream.write((item + "\n").getBytes());
            }
            stream.close();
        }
        catch (Exception e)
        {

        }
    }

    public void populatePrompt(TextView lblPrompt) // populates the word prompt, updates charCount
    {
        if (lblPrompt.getText().toString() != "")
        {
            lblCorrect.setText("Correct!");
        }
        lblCorrect.startAnimation(in);
        charCount += lblPrompt.getText().toString().length();
        lblPrompt.setText(wordList.get(ran(0,wordList.size() - 1)));

    }
    public void setLblTimer(TextView txtview)
    {
        this.lblTimer = txtview;
    }
    public void setLblCorrect(TextView txtview)
    {
        this.lblCorrect = txtview;
    }
    public int ran(int min,int max)
    {
        Random r = new Random();
        return  r.nextInt((max-min)+1)+min;
    }

    protected void onDestroy()
    {
        super.onDestroy();
        if (mediaPlayer != null)
        {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        if(timer != null)
        {

            timer.cancel();
            timer = null;
        }
    }

}
