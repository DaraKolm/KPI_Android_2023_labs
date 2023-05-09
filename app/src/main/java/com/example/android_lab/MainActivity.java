package com.example.android_lab;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText userText;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private TextView textView;
    private String text;
    private String fontSize;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button butOK = findViewById(R.id.buttonOK);
        Button butCancel = findViewById(R.id.buttonCancel);
        userText = findViewById(R.id.userText);
        textView = findViewById(R.id.textView3);
        radioGroup = findViewById(R.id.radioGroup1);
        radioGroup.check(findViewById(R.id.radioButton1).getId());

        butOK.setOnClickListener(v -> {
            text = userText.getText().toString();
            if(text.trim().equals("")/*||text.trim().equals(getString(R.string.text_ent).trim())*/){
                Toast.makeText(MainActivity.this, R.string.message_empty,Toast.LENGTH_LONG).show();
            }else{
                 int radioButtonID;
                 /*radioButtonID=0;
                boolean flag = true;
                try{radioButtonID = radioGroup.getCheckedRadioButtonId();}

                    catch(Exception e){
                        Toast.makeText(MainActivity.this, R.string.message_empty_radio,Toast.LENGTH_LONG).show();
                        flag=false;
                }
                if(flag){
                    //Toast.makeText(MainActivity.this, R.string.message_empty_radio,Toast.LENGTH_LONG).show();
                    //Toast.makeText(MainActivity.this, "rdID"+radioButtonID,Toast.LENGTH_LONG).show();
                    radioButton = radioGroup.findViewById(radioButtonID);
                    fontSize = radioButton.getText().toString().replace("sp","");
                    textView.setText(text);
                    textView.setTextSize(Float.parseFloat(fontSize));

                }*/
                radioButtonID = radioGroup.getCheckedRadioButtonId();
                radioButton = radioGroup.findViewById(radioButtonID);
                fontSize = radioButton.getText().toString().replace("sp","");
                textView.setText(text);
                textView.setTextSize(Float.parseFloat(fontSize));
            }
        });

        butCancel.setOnClickListener(v -> {
            //userText.setText(R.string.text_ent);
            userText.setText("");
            textView.setText(R.string.text_ent);
            textView.setTextSize(18);
            //radioGroup.clearCheck();
            radioGroup.check(findViewById(R.id.radioButton1).getId());
            Toast.makeText(MainActivity.this, R.string.message_cancel,Toast.LENGTH_LONG).show();

        });


    }
}
