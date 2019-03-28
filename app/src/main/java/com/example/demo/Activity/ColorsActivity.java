package com.example.demo.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.Adapter.CricketerAdapter;
import com.example.demo.Database.ChatDBHelper;
import com.example.demo.Database.ChatDBUtility;
import com.example.demo.Database.CommonConstants;
import com.example.demo.Model.CricketerDataRecords;
import com.example.demo.R;
import com.example.demo.Utility.Utility;

import java.util.ArrayList;

/**
 * This activity is for adding colors and selecting colors
 * on next click from ColorsActivity To SummaryActivity
 * On Add click - popup for adding colors
 * previous activity - CricketerActivity
 */

public class ColorsActivity extends AppCompatActivity {


    Button next;
    TextView header_title,history;
    ListView listView;

    ChatDBHelper chatDBHelper;
    ChatDBUtility chatDBUtility;

    ArrayList<CricketerDataRecords> colorsDataRecords;

   public ArrayList<String> name=new ArrayList<String>();

    int id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        /**
         * for setting actionbar
         */
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);

        chatDBUtility = new ChatDBUtility();
        chatDBHelper = chatDBUtility.CreateChatDB(ColorsActivity.this);

        initializeView();//for initializing  views
        initializeListener();//all clickListeners on views
        GetSharedPreference();//getting values from SharedPreference
        setData();//for setting data
        setAdapter();//Adapter setting


    }



    private void setData() {

        header_title.setText(getString(R.string.choose_colors));
        /**
         * history - history is renamed as Add , so that we can add data to colors
         */
        history.setText(getString(R.string.add_cric));


    }

    private void initializeListener() {



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * Adding all values of name Arraylsit to StringBuilder ,
                 * so that we can append  comma separator with another string and make it as one string
                 */
                if(name.size()!=0)
                {

                    StringBuilder colors= new StringBuilder();
                    for (int i=0;i<name.size();i++)
                    {
                        if(i!=name.size()-1)
                        {
                            colors.append(name.get(i)+" , ");
                        }else
                        {
                            colors.append(name.get(i));
                        }

                    }
                    /**
                     * Updating the values to DataList table
                     * So that we can show it in on history page
                     */
                    chatDBUtility.UpdateColors(chatDBHelper, colors.toString(),id);
                    Intent intent=new Intent(ColorsActivity.this,SummaryActivity.class);
                    startActivity(intent);
                }else
                {
                    Toast.makeText(ColorsActivity.this, "Please select any colors", Toast.LENGTH_SHORT).show();
                }

            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 *   showing popup  for Adding data to colors
                 *   sending code 1 for -Adding colors
                 *   sending code 2 for - Adding cricketers
                 *   id - adding data according to id ;
                 */

                Utility.AddDialog(ColorsActivity.this,"Add Colors",1,id);
            }
        });











    }

    /**
     * initializing all xml views
     */

    private void initializeView() {


        history=(TextView)findViewById(R.id.history);
        history.setVisibility(View.VISIBLE);
        listView=(ListView)findViewById(R.id.listView);
        next=(Button) findViewById(R.id.next);
        header_title=(TextView)findViewById(R.id.header_title);
    }


    /**
     * Getting values from SharedPreferences
     * id-based on id adding data to database
     */
    public void GetSharedPreference()
    {

        SharedPreferences userSettings;
        userSettings = getSharedPreferences(CommonConstants.USER_SETTINGS_PREFERENCE, Context.MODE_PRIVATE);
        id = userSettings.getInt(CommonConstants.ID, 0);

    }


    /**
     * setAdapter() is used for setting data to listView
     * Uses :- 1.Utility - After Adding data to database,set the data to listView
     *         2.onCreate() - for first time setting the data to listView
     *         CricketerAdapter- pass layout_code 1- for cricketers ,2- for colors
     */


    public  void setAdapter()
    {

        colorsDataRecords=new ArrayList<>();
        colorsDataRecords=chatDBUtility.GetColorsList(chatDBHelper);
        if(colorsDataRecords.size()>0)
        {

            CricketerAdapter cricketerAdapter=new CricketerAdapter(ColorsActivity.this,colorsDataRecords,2);
            listView.setAdapter(cricketerAdapter);

        }else
        {
            Toast.makeText(this, "Please Add Colors", Toast.LENGTH_SHORT).show();
        }
    }
}
