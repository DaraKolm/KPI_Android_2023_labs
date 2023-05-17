package com.example.android_lab;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {link Fragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment2 extends Fragment {

    private TextView textView3;
    private OnFragmentClearDataListener fragmentClearDataListener;
    public Fragment2() {
        // Required empty public constructor
    }

    //Встановлюємо новий текст і розмір до виводу
    public void SetParamsTextView(String t, String s){
        textView3.setText(t);
        textView3.setTextSize(Float.parseFloat(s));
    }

    //Для очищення даних-------------------------------------
    public interface OnFragmentClearDataListener{
        void onClearData();

    }
    @Override
    public void onAttach (Context context){
        super.onAttach(context);
        try{
            fragmentClearDataListener = (Fragment2.OnFragmentClearDataListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }




    public View.OnClickListener buttonCancel_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            textView3.setText(R.string.text_ent);
            textView3.setTextSize(18);
            fragmentClearDataListener.onClearData();
            //userText.setText(R.string.text_ent);
            /*userText.setText("");
            textView.setText(R.string.text_ent);
            textView.setTextSize(18);
            //radioGroup.clearCheck();
            radioGroup.check(findViewById(R.id.radioButton1).getId());
            Toast.makeText(MainActivity.this, R.string.message_cancel,Toast.LENGTH_LONG).show();
*/
        }
    };



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, container, false);

        final Button buttonCancel = view.findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(buttonCancel_click);
        textView3= view.findViewById(R.id.textView3);




        return view;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*final Button buttonCancel = getActivity().findViewById(R.id.buttonCancel);
        TextView textView = getActivity().findViewById(R.id.textView3);
*/

    }

    public static Fragment2 newInstance(Button param1, TextView param2) {
        Fragment2 fragment = new Fragment2();
        /*Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);*/
        return fragment;
    }


}