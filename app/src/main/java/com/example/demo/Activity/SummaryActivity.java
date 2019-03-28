package com.example.demo.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.Database.ChatDBHelper;
import com.example.demo.Database.ChatDBUtility;
import com.example.demo.Database.CommonConstants;
import com.example.demo.Model.DataList;
import com.example.demo.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * for summary page
 * on finish click-clears all activity from stack and goes to Homepage
 */

public class SummaryActivity extends AppCompatActivity {

    TextView name_show,answer,answer_flag,header_title;
    Button finish;


    ChatDBHelper chatDBHelper;
    ChatDBUtility chatDBUtility;

    int id;

    ArrayList<DataList> dataLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        /**
         * actionbar setting
         */
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);


        chatDBUtility = new ChatDBUtility();
        chatDBHelper = chatDBUtility.CreateChatDB(SummaryActivity.this);

        initializeView();//for initializing  views
        initializeListener();//all clickListeners on views
        GetSharedPreference();//getting values from SharedPreference
        setData();//for setting data



    }

    private void setData() {


        /**
         * fetching current data from database ,based on latest id
         */
        dataLists=chatDBUtility.GetDataList(chatDBHelper,id);


        answer.setText(getString(R.string.answer)+dataLists.get(0).getFav_cricketer());
        answer_flag.setText(getString(R.string.answer)+dataLists.get(0).getColors());
        name_show.setText(getString(R.string.hello)+dataLists.get(0).getName());

        header_title.setText("Summary Page");

    }

    private void initializeListener() {

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDateAndTime();

                /**
                 * i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);- for clearing activity stack
                 */

                Toast.makeText(SummaryActivity.this, "Data has been saved successfully ", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(SummaryActivity.this, HomepageActivity.class);

                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(CommonConstants.USER_SETTINGS_PREFERENCE, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt(CommonConstants.ID,  0);
                editor.commit();
                startActivity(i);
            }
        });

    }

    /**
     * updateDateAndTime - for getting current date and time
     */

    private void updateDateAndTime() {

        Date date = new Date();
        Calendar cal=Calendar.getInstance();
        cal.setTime(date);

        String dateAndTime=getFormattedDate(cal.getTime());

        /**
         * updating current date and time to database
         */
        chatDBUtility.UpdateDate(chatDBHelper,dateAndTime,id);

    }

    private void initializeView() {

        name_show=(TextView)findViewById(R.id.game);
        answer=(TextView)findViewById(R.id.answer);
        answer_flag=(TextView)findViewById(R.id.answer_flag);
        finish=(Button) findViewById(R.id.finish);
        header_title=(TextView)findViewById(R.id.header_title);
    }


    public void GetSharedPreference()
    {
        SharedPreferences userSettings;
        userSettings = getSharedPreferences(CommonConstants.USER_SETTINGS_PREFERENCE, Context.MODE_PRIVATE);
        id = userSettings.getInt(CommonConstants.ID, 0);

    }

    /**
     *
      * @param date
     * @return reqiured date format
     */
    public static String getFormattedDate(Date date){
        Calendar cal=Calendar.getInstance();
        cal.setTime(date);
        //2nd of march 2015
        int day=cal.get(Calendar.DATE);

        if(!((day>10) && (day<19)))
            switch (day % 10) {
                case 1:
                    return new SimpleDateFormat("d'st'  MMMM hh:mm aaa").format(date);
                case 2:
                    return new SimpleDateFormat("d'nd'  MMMM hh:mm aaa ").format(date);
                case 3:
                    return new SimpleDateFormat("d'rd'  MMMM hh:mm aaa").format(date);
                default:
                    return new SimpleDateFormat("d'th'  MMMM hh:mm aaa").format(date);
            }
        return new SimpleDateFormat("d'th'  MMMM hh:mm aaa").format(date);
    }

}
