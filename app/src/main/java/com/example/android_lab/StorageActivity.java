package com.example.android_lab;

import static java.security.AccessController.getContext;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;

public class StorageActivity extends AppCompatActivity {
    private List <Note> list = new ArrayList<>();
    private DBHelper dbHelper;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        /*??????
        *
        *
        * */
        final Button buttonDeleteDB = findViewById(R.id.buttonDeleteDB);
        buttonDeleteDB.setOnClickListener(buttonDeleteDB_click);
        final Button buttonGoBack = findViewById(R.id.buttonGoBack);
        buttonGoBack.setOnClickListener(buttonGoBack_click);

        dbHelper = new DBHelper(this);
        list.clear();
        list.addAll(dbHelper.getAllNotes());
        textView=findViewById(R.id.textViewDB);
        printAll();


        /*??????
         *
         *
         * */
    }


    @Override
    protected void onResume(){
        super.onResume();
        list.clear();
        list.addAll(dbHelper.getAllNotes());
        //adapter.updateData(dbHelper.getAllNotes());
    }

    private void printAll(){
        if(list.size()!=0){
            for (Note n: list
                 ) {
                String str ="Size: "+ n.getFontSize()+"sp Text: "+n.getText()+"\n";
                textView.append(str);
            }
        }else{
            Toast.makeText(StorageActivity.this,R.string.message_db_empty,Toast.LENGTH_LONG).show();
        }
    }


    public View.OnClickListener buttonDeleteDB_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           dbHelper.deleteDB();
           list.clear();
           list.addAll(dbHelper.getAllNotes());
           textView.setText("");
           printAll();
        }
    };

    public View.OnClickListener buttonGoBack_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            StorageActivity.super.onBackPressed();

        }
    };




}
