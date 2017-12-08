package com.Text_Rabbit.Text_Rabbit.Text_Rabbit;

/**
 * Created by Lance Eaton on 10/23/2017.
 */

public class User
{
    private String name;
    private Float score;

    public User(String name,Float score)
    {
        this.name = name;
        this.score = score;
    }

    public String getName()
    {
        return this.name;
    }

    public Float getScore()
    {
        return this.score;
    }
    @Override
    public String toString()
    {
        return this.name + ": " + Float.toString(this.score) + " wpm";
    }
}
