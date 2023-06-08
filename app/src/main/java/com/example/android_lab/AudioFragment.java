package com.example.android_lab;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.provider.OpenableColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class AudioFragment extends Fragment {
    private Button button_findAudio ;
    private Button button_play;

    private TextView audio_songName;
    private TextView timePositionTV;
    private TextView timeDurationTV;
    private Button button_backToStart;
    private AppCompatSeekBar seekBar;


    private MediaPlayer mediaPlayer;

    private double startTime;
    private double finalTime;
    public static int TIME_DURATION_FLAG = 0;
    private final Handler myHandler = new Handler();


    public AudioFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_audio, container, false);

        //define
        button_findAudio = view.findViewById(R.id.button_findAudio);
        button_play = view.findViewById(R.id.button_play_pause);
        button_backToStart = view.findViewById(R.id.button_backToStart);

        audio_songName = view.findViewById(R.id.audio_songName);
        timePositionTV = view.findViewById(R.id.audio_start_time_tv);
        timeDurationTV = view.findViewById(R.id.audio_end_time_tv);
        seekBar = view.findViewById(R.id.audio_progress_seekBar);

        seekBar.setClickable(false);
        seekBar.setEnabled(false);
        button_backToStart.setEnabled(false);
        button_play.setEnabled(false);

        mediaPlayer = new MediaPlayer();
        //-------

        setListeners();

        return view;
        // Inflate the layout for this fragment
    }

    private void setListeners(){
        button_findAudio.setOnClickListener(view -> {
            mediaPlayer.pause();
            button_play.setText(R.string.button_play);
            Intent intent = new Intent();
            intent.setType("audio/*");
            //Activity Action: Allow the user to select a particular kind of data and return it.
            intent.setAction(Intent.ACTION_GET_CONTENT);
            audioActivityResultLauncher.launch(intent);
        });

        button_play.setOnClickListener(v -> {
            if (mediaPlayer != null) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    button_play.setText(R.string.button_play);
                } else{
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());

                    mediaPlayer.start();
                    button_play.setText(R.string.button_pause);

                    finalTime = mediaPlayer.getDuration();
                    startTime = mediaPlayer.getCurrentPosition();

                    if (TIME_DURATION_FLAG == 0) {
                        seekBar.setMax((int) finalTime);
                        TIME_DURATION_FLAG = 1;
                    }

                    updateEndTime();
                    updateStartTime();

                    seekBar.setProgress((int) startTime);
                    myHandler.postDelayed(UpdateSongTime, 10);
                }
            }
        });

        button_backToStart.setOnClickListener(v-> {
            mediaPlayer.seekTo(0);

            finalTime = mediaPlayer.getDuration();
            startTime = mediaPlayer.getCurrentPosition();

            updateEndTime();
            updateStartTime();

            seekBar.setProgress((int) startTime);
            myHandler.postDelayed(UpdateSongTime, 10);
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(mediaPlayer!=null && fromUser) mediaPlayer.seekTo(progress); }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }


    private void updateStartTime() {
        timeSet(startTime, timePositionTV);
    }

    private void updateEndTime() {
        timeSet(finalTime, timeDurationTV);
    }
    private void timeSet(double timeBound, @NonNull TextView textViewSet){
        long min = TimeUnit.MILLISECONDS.toMinutes((long) timeBound);
        long sc= TimeUnit.MILLISECONDS.toSeconds((long) timeBound) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                        timeBound));
        textViewSet.setText(String.format("%s:%s",
                min < 10 ? "0" + min : min,
                sc < 10 ? "0" + sc : sc)
        );
    }
    private final Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            updateStartTime();
            seekBar.setProgress((int) startTime);
            myHandler.postDelayed(this, 10);
        }
    };

    private final ActivityResultLauncher<Intent> audioActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            Uri audioUri = data.getData();
                            try (Cursor returnCursor = requireActivity().getContentResolver().query(audioUri, null, null, null, null)) {
                                int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                                if (!returnCursor.moveToFirst()) {
                                    throw new Exception("File wasn't found");
                                } else  audio_songName.setText(returnCursor.getString(nameIndex));
                            } catch (Exception e) { e.printStackTrace();}

                            mediaPlayer = MediaPlayer.create(getContext(), audioUri);

                            finalTime = mediaPlayer.getDuration();
                            startTime = mediaPlayer.getCurrentPosition();

                            button_play.setEnabled(true);
                            button_backToStart.setEnabled(true);
                        }
                    }else Toast.makeText(getContext(),"Cancelled", Toast.LENGTH_SHORT).show();

                }
            }
    );



}