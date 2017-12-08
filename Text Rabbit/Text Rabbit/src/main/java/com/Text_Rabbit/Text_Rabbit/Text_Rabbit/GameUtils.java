package com.Text_Rabbit.Text_Rabbit.Text_Rabbit;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class GameUtils
{



    public static void readFromFile(Context context,ArrayList<String> list, String fileName)
    {
        try
        {
            FileInputStream stream = context.openFileInput(fileName);
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            list.clear();
            while ((line = bufferedReader.readLine()) != null)
            {
                list.add(line);
            }


        }
        catch (Exception e)
        {
            //String tag = GameUtils.class.getSimpleName();
            //Log.d(tag, e.toString());
        }
    }
    public static void readFromFileFloat(Context context,ArrayList<Float> list, String fileName)
    {
        try
        {
            FileInputStream stream = context.openFileInput(fileName);
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            list.clear();
            while ((line = bufferedReader.readLine()) != null)
            {
                list.add(Float.parseFloat(line));
            }


        }
        catch (Exception e)
        {
            //String tag = GameUtils.class.getSimpleName();
            //Log.d(tag, e.toString());
        }
    }

    public static void writeToFile(Context context,ArrayList<String> list, String fileName)
    {
        try
        {
            FileOutputStream stream = context.openFileOutput(fileName,Context.MODE_PRIVATE);

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
    public static void writeToFileFloat(Context context,ArrayList<Float> list, String fileName)
    {
        try
        {
            FileOutputStream stream = context.openFileOutput(fileName,Context.MODE_PRIVATE);

            for (Float item : list)
            {
                stream.write((Float.toString(item) + "\n").getBytes());
            }
            stream.close();
        }
        catch (Exception e)
        {

        }
    }

    public static int ran(int min,int max)
    {
        Random r = new Random();
        return  r.nextInt((max-min)+1)+min;
    }


}
