package com.example.android_lab;


import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

public class MainActivity extends AppCompatActivity
        implements Fragment1.OnFragmentSentDataListener,Fragment2.OnFragmentClearDataListener {



    //---Виведення даних і показ фрагмента 2
    @Override
    public void onSendData(String textSend, String fontSize){
        Fragment2 fragment2 = (Fragment2)getSupportFragmentManager()
                .findFragmentById(R.id.fragmentContainerView2);
        if (fragment2 !=null){
            FragmentContainerView frView2 = findViewById(R.id.fragmentContainerView2);
            frView2.setVisibility(View.VISIBLE);
            fragment2.SetParamsTextView(textSend,fontSize);
        }
    }

    //---Очищення даних і приховання фрагмента 2
    @Override
    public void onClearData(){
        Fragment1 fragment1 = (Fragment1)getSupportFragmentManager()
                .findFragmentById(R.id.fragmentContainerView1);
        if (fragment1 !=null){
            FragmentContainerView frView2 = findViewById(R.id.fragmentContainerView2);
            frView2.setVisibility(View.INVISIBLE);
            fragment1.ClearInputFragment1();
            Toast.makeText(MainActivity.this, R.string.message_cancel,Toast.LENGTH_LONG).show();
        }
    }


    //private Button buttonOK;

    /*public View.OnClickListener buttonOK_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            text = userText.getText().toString();
            if(text.trim().equals("")){
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
    };*/
    /*
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
    };*/


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment1, Fragment1.class, null)
                    .commit();
        }*/

        //FragmentManager fm = getFragmentManager();
        //FragmentTransaction fg = fm.beginTransaction();


        ///buttonOK = findViewById(R.id.buttonOK);
        //buttonOK.setOnClickListener(buttonOK_click);
        //buttonCancel.setOnClickListener(buttonCancel_click);


    }
}
