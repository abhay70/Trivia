package com.example.demo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.example.demo.Activity.ColorsActivity;
import com.example.demo.Activity.CricketerActivity;
import com.example.demo.Database.ChatDBHelper;
import com.example.demo.Database.ChatDBUtility;
import com.example.demo.Model.ColorsDataRecords;
import com.example.demo.Model.CricketerDataRecords;
import com.example.demo.R;

import java.util.ArrayList;


/**
 * layout code 1 for displaying data for cricketers
 * layout code 2 for displaying data for colors
 *
 */

public class CricketerAdapter extends BaseAdapter {

    Context context;
    LayoutInflater vi;
    ChatDBHelper dbHelper;
    ChatDBUtility dbUtility;
    int selected_position=-1;
    int layout_code;

    ArrayList<CricketerDataRecords> cricketerDataRecords = new ArrayList<CricketerDataRecords>();

    public CricketerAdapter(Context context, ArrayList<CricketerDataRecords> cricketerDataRecords,int layout_code) {
        super();
        this.context = context;
        this.layout_code=layout_code;
        this.cricketerDataRecords = cricketerDataRecords;
        vi = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);


    }

    @Override
    public int getCount() {
        return cricketerDataRecords.size();
    }

    @Override
    public Object getItem(int position) {
        return cricketerDataRecords.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        dbUtility = new ChatDBUtility();
        dbHelper = dbUtility.CreateChatDB(context);
        final viewHolder holder;
        if (convertView == null) {
            convertView = vi.inflate(R.layout.adapter_cricketer, null);


            holder = new viewHolder();
             holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);

            convertView.setTag(holder);

        } else {
            holder = (viewHolder) convertView.getTag();
        }



        holder.checkBox.setText(cricketerDataRecords.get(position).getCrickter());


        /**
         *  layout_code 1 - for cricketers ,2  - for colors
         */

        if(layout_code==1) {

            /**
             * using if condition so that only checkBox is selected
             */
            if (selected_position == position) {
                holder.checkBox.setChecked(true);

            } else {
                holder.checkBox.setChecked(false);

            }


            /**
             * for selecting  cricketers
             */
            holder.checkBox.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {


                    if (((CheckBox) view).isChecked()) {
                        selected_position = position;
                        ((CricketerActivity) context).cricketer = cricketerDataRecords.get(position).getCrickter();
                    } else {
                        selected_position = -1;
                    }


                    notifyDataSetChanged();


                }
            });

        }else if(layout_code==2)
        {

            /**
             * for selecting colors
             */

            holder.checkBox.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {


                    if (((CheckBox) view).isChecked()) {
                        ((ColorsActivity)context).name.add(cricketerDataRecords.get(position).getCrickter());
                    } else {
                       // if(((ColorsActivity)context).name.contains(cricketerDataRecords.get(position).getCrickter()))
                        {
                            ((ColorsActivity)context).name.remove(cricketerDataRecords.get(position).getCrickter());
                        }

                    }


                    notifyDataSetChanged();


                }
            });

        }
        return convertView;

    }


    static class viewHolder {


        public CheckBox checkBox;

    }

}
