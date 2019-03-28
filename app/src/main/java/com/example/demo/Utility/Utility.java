package com.example.demo.Utility;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.demo.Activity.ColorsActivity;
import com.example.demo.Activity.CricketerActivity;
import com.example.demo.Database.ChatDBHelper;
import com.example.demo.Database.ChatDBUtility;
import com.example.demo.R;

public class Utility {

  public static   ChatDBHelper dbHelper;
   public static ChatDBUtility dbUtility;


    /**
     *
     * @param context
     * @param header_text-text for title of popup
     * @param code-1 for colors,2-cricketers
     * @param id-current id for saving data
     */
    public static void  AddDialog(final Context context, String header_text, final int code, final int id)
    {

        dbUtility = new ChatDBUtility();
        dbHelper = dbUtility.CreateChatDB(context);
        final AlertDialog deleteDialog = new AlertDialog.Builder(context).create();
    LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    final View deleteDialogView = inflater.inflate(R.layout.dialog, null);
                deleteDialog.setView(deleteDialogView);

      TextView header=deleteDialogView.findViewById(R.id.textView);
        final EditText add_text=deleteDialogView.findViewById(R.id.add_text);
    final Button yes=deleteDialogView.findViewById(R.id.dialogButtonOK);
    final Button no=deleteDialogView.findViewById(R.id.dialogButtonNo);


    header.setText(header_text);

                yes.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

                if(code==1)
                {
                    /**
                     * adding colors to database
                     */
                    dbUtility.AddToColorsListDB(dbHelper,add_text.getText().toString(),id);
                    /**
                     * updating the layout
                     */
                    ((ColorsActivity)context).setAdapter();

                }else if(code==2)
                {
                    /**
                     * adding cricketers to database
                     */
                    dbUtility.AddToCricketerListDB(dbHelper,add_text.getText().toString(),id);
                    /**
                     * updating the layout
                     */
                    ((CricketerActivity)context).setAdapter();
                }



            deleteDialog.dismiss();
        }
    });
                no.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            deleteDialog.dismiss();
        }
    });

                deleteDialog.show();


    }



}
