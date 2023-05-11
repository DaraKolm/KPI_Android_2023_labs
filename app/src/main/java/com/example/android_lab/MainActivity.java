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
    public View.OnClickListener buttonOK_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            text = userText.getText().toString();
            if(text.trim().equals("")/*||text.trim().equals(getString(R.string.text_ent).trim())*/){
                Toast.makeText(MainActivity.this, R.string.message_empty,Toast.LENGTH_LONG).show();
            }else{
                int radioButtonID;
                radioButtonID = radioGroup.getCheckedRadioButtonId();
                radioButton = radioGroup.findViewById(radioButtonID);
                fontSize = radioButton.getText().toString().replace("sp","");
                textView.setText(text);
                textView.setTextSize(Float.parseFloat(fontSize));
            }
        }
    };
    public View.OnClickListener buttonCancel_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //userText.setText(R.string.text_ent);
            userText.setText("");
            textView.setText(R.string.text_ent);
            textView.setTextSize(18);
            //radioGroup.clearCheck();
            radioGroup.check(findViewById(R.id.radioButton1).getId());
            Toast.makeText(MainActivity.this, R.string.message_cancel,Toast.LENGTH_LONG).show();

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button buttonOK = findViewById(R.id.buttonOK);
        final Button buttonCancel = findViewById(R.id.buttonCancel);
        userText = findViewById(R.id.userText);
        textView = findViewById(R.id.textView3);
        radioGroup = findViewById(R.id.radioGroup1);
        radioGroup.check(findViewById(R.id.radioButton1).getId());

        buttonOK.setOnClickListener(buttonOK_click);
        buttonCancel.setOnClickListener(buttonCancel_click);


    }
}
