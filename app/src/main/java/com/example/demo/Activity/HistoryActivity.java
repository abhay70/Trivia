package com.example.demo.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demo.Adapter.HistoryAdapter;
import com.example.demo.Database.ChatDBHelper;
import com.example.demo.Database.ChatDBUtility;
import com.example.demo.Database.CommonConstants;
import com.example.demo.Model.DataList;
import com.example.demo.R;

import java.util.ArrayList;

/**
 * Used for showing all saved data
 *on home button click-HomepageActivity
 */

public class HistoryActivity extends AppCompatActivity {

    RecyclerView history_list;
    TextView header_title;
    ImageView home;

    private RecyclerView.LayoutManager layoutManager;
    ChatDBHelper chatDBHelper;
    ChatDBUtility chatDBUtility;

    ArrayList<DataList> dataLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        /**
         * for setting actionbar
         */

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);

        chatDBUtility = new ChatDBUtility();
        chatDBHelper = chatDBUtility.CreateChatDB(HistoryActivity.this);

        initializeView();//for initializing  views
        initializeListener();//all clickListeners on views
        setData();//for setting data

    }

    private void setData() {

        /**
         * populating recyclerView
         * dataLists- all saved data in arrayList
         */


        dataLists=new ArrayList<>();
        dataLists=chatDBUtility.GetDataList(chatDBHelper,0);
        layoutManager = new LinearLayoutManager(HistoryActivity.this);
        history_list.setLayoutManager(layoutManager);
        history_list.setItemAnimator(new DefaultItemAnimator());
        HistoryAdapter historyAdapter = new HistoryAdapter(HistoryActivity.this, dataLists);
        history_list.setAdapter(historyAdapter);
        //history_list.setItemViewCacheSize(dataLists.size());



        header_title.setText("History");

    }


    private void initializeListener() {

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 *  i.setFlags = clearing all activity from stack , to avoid backPress problem
                 */

                Intent i = new Intent(HistoryActivity.this, HomepageActivity.class);

                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);

            }
        });


    }

    private void initializeView() {

        history_list=(RecyclerView)findViewById(R.id.history_list);
        header_title=(TextView)findViewById(R.id.header_title);
        home=(ImageView)findViewById(R.id.home);
        home.setVisibility(View.VISIBLE);
    }
}
