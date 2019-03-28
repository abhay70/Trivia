package com.example.demo.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
 * This activity is for adding cricketers and selecting cricketer
 * on next click from   CricketerActivity To ColorsActivity
 *  On Add click - popup for adding cricketers
 *  previous activity - HomepageActivity
 *
 */

public class CricketerActivity extends AppCompatActivity {



    Button next;
    TextView header_title,history;
    ListView listView;


    ChatDBHelper chatDBHelper;
    ChatDBUtility chatDBUtility;

    int id;

   public String cricketer="";

    ArrayList<CricketerDataRecords> cricketerDataRecords;

    CricketerAdapter cricketerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cricketer);

        /**
         * Setting actionbar
         */

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        chatDBUtility = new ChatDBUtility();
        chatDBHelper = chatDBUtility.CreateChatDB(CricketerActivity.this);

        initializeView();//for initializing  views
        initializeListener();//all clickListeners on views
        GetSharedPreference();//getting values from SharedPreference
        setData();//for setting data
        setAdapter();//Adapter setting



    }

    /**
     * setAdapter() is used for setting data to listView
     * Uses :- 1.Utility - After Adding data to database,set the data to listView
     *         2.onCreate() - for first time setting the data to listView
     *         CricketerAdapter- pass layout_code 1- for cricketers ,2- for colors
     */


    public void setAdapter() {
        cricketerDataRecords=new ArrayList<>();
        cricketerDataRecords=chatDBUtility.GetCricketerList(chatDBHelper);
        if(cricketerDataRecords.size()>0)
        {
            cricketerAdapter=new CricketerAdapter(CricketerActivity.this,cricketerDataRecords,1);
            listView.setAdapter(cricketerAdapter);

        }else
        {
            Toast.makeText(this, "Please Add Cricketer", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData() {

    header_title.setText(getString(R.string.cricketer));

        /**
         * history - history is renamed as Add , so that we can add data to cricketers
         */

    history.setText(getString(R.string.add_cric));



    }

    private void initializeListener() {


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!cricketer .equals(""))
                {
                    /**
                     * Updating dataList table
                     */
                    chatDBUtility.UpdateCricketer(chatDBHelper,cricketer ,id);
                    Intent intent=new Intent(CricketerActivity.this,ColorsActivity.class);
                    startActivity(intent);
                }else
                {
                    Toast.makeText(CricketerActivity.this, "Please select any cricketer", Toast.LENGTH_SHORT).show();
                }
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 *   showing popup  for Adding data to cricketers
                 *   sending code 1 for -Adding colors
                 *   sending code 2 for - Adding cricketers
                 *   id - adding data according to id ;
                 */

                Utility.AddDialog(CricketerActivity.this,"Add Cricketer",2,id);
            }
        });


    }

    /**
     * initializing all xml views
     */

    private void initializeView() {



        header_title=(TextView)findViewById(R.id.header_title);
        next=(Button)findViewById(R.id.next);
        history=(TextView)findViewById(R.id.history);
        history.setVisibility(View.VISIBLE);
        listView=(ListView)findViewById(R.id.listView);

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


}
