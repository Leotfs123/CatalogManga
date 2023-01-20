package com.leo.catalogmanga.Util;


import android.app.Activity;
import android.content.SharedPreferences;

public class Prefs
{
    SharedPreferences sharedPreferences;
    public Prefs(Activity activity)
    {
        sharedPreferences = activity.getPreferences(activity.MODE_PRIVATE);
    }
    public void setSearch(String search)
    {
        sharedPreferences.edit().putString("search",search);
    }
    public String getSearch()
    {
        return sharedPreferences.getString("search","Solo Leveling");
    }
}