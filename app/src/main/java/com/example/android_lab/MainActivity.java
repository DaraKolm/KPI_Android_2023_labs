package com.example.android_lab;



import android.os.Bundle;
import android.view.View;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

public class MainActivity extends AppCompatActivity
        implements Fragment1.OnFragmentSentDataListener,Fragment2.OnFragmentClearDataListener {

    DBHelper dbHelper;
    Fragment1 fragment1;
    Fragment2 fragment2;
    //---Виведення даних і показ фрагмента 2
    @Override
    public void onSendData(String textSend, String fontSize){
        fragment2 = (Fragment2)getSupportFragmentManager()
                .findFragmentById(R.id.fragmentContainerView2);
        if (fragment2 !=null){
            FragmentContainerView frView2 = findViewById(R.id.fragmentContainerView2);
            frView2.setVisibility(View.VISIBLE);
            fragment2.SetParamsTextView(textSend,fontSize);

            int res = dbHelper.addNote(textSend,fontSize);
            if(res!=-1){
                Toast.makeText(this, R.string.message_db_saved,Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, R.string.message_db_ERROR_saving,Toast.LENGTH_LONG).show();
            }
        }
    }

    //---Очищення даних і приховання фрагмента 2
    @Override
    public void onClearData(){
        fragment1 = (Fragment1)getSupportFragmentManager()
                .findFragmentById(R.id.fragmentContainerView1);
        if (fragment1 !=null){
            FragmentContainerView frView2 = findViewById(R.id.fragmentContainerView2);
            frView2.setVisibility(View.INVISIBLE);
            fragment1.ClearInputFragment1();
            Toast.makeText(MainActivity.this, R.string.message_cancel,Toast.LENGTH_LONG).show();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);

        /*final Button buttonOpen = findViewById(R.id.buttonOpen);
        buttonOpen.setOnClickListener(v -> {
            Intent intent = new Intent(this, StorageActivity.class);
            startActivity(intent);
        });*/

    }
}
