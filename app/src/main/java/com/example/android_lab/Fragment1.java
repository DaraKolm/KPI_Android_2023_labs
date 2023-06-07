package com.example.android_lab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Fragment1 extends Fragment {

    private EditText userText;
    private RadioGroup radioGroup;
    private int rb1ID;
    private OnFragmentSentDataListener fragmentSentDataListener;
    public Fragment1() {
        // Required empty public constructor
    }

    //Для передачі даних-------------------------------------
    public interface OnFragmentSentDataListener{
        void onSendData(String textSend, String fontSize);

    }
    @Override
    public void onAttach (Context context){
        super.onAttach(context);
        try{
            fragmentSentDataListener = (OnFragmentSentDataListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }

    //очищення полів----------------------------------------------------
    public void ClearInputFragment1(){
        userText.setText("");
        radioGroup.check(rb1ID);
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_1, container, false);

        final Button buttonOK = view.findViewById(R.id.buttonOK);
        buttonOK.setOnClickListener(buttonOK_click);
        userText = view.findViewById(R.id.userText);
        radioGroup = view.findViewById(R.id.radioGroup1);
        radioGroup.check(view.findViewById(R.id.radioButton1).getId());
        rb1ID =view.findViewById(R.id.radioButton1).getId();



        return view;
    }


    public View.OnClickListener buttonOK_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String text = userText.getText().toString();
            //userText.setText(text.toUpperCase());
                if(text.trim().isEmpty()){
                    Toast.makeText(getContext(), R.string.message_empty,Toast.LENGTH_LONG).show();
                }else{
                    int radioButtonID;
                    radioButtonID = radioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = radioGroup.findViewById(radioButtonID);
                    String fontSize = radioButton.getText().toString().replace("sp","");
                    fragmentSentDataListener.onSendData(text,fontSize);

                }
        }
    };
    public View.OnClickListener buttonOpen_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



}