package com.example.android_lab;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoFragment extends Fragment {

    private VideoView videoView;
    private Button button_findVideo;
    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);

        button_findVideo = view.findViewById(R.id.button_findVideo);
        videoView = view.findViewById(R.id.video_view);
        videoView.setMediaController(new MediaController(this.getContext()));

        button_findVideo.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("video/*");
            // intent.setAction();
            videoActivityResultLauncher.launch(intent);
        });

        return view;}

    private final ActivityResultLauncher<Intent> videoActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();

                        if (data != null) {
                            Uri videoUri = data.getData();
                            videoView.setVideoURI(videoUri);
                        }
                    } else {
                        Toast.makeText(getContext(), "A video error has occurred", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );
}