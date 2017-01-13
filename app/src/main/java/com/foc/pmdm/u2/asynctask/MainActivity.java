package com.foc.pmdm.u2.asynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

// Code by javacasm 13-1-2017
// Referencias
// https://guides.codepath.com/android/Creating-and-Executing-Async-Tasks
// http://developer.android.com/reference/android/os/AsyncTask.html

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        imageView=(ImageView)findViewById(R.id.imageView);
    }

    DownloadImageTask dt;
    public void btStart(View v){
        dt=new DownloadImageTask();
        dt.progressBar=progressBar;
        dt.imageView=imageView;
        dt.execute("https://www.google.es/mobile/android/images/android.jpg");
    }

    public void btStop(View v)   {
        dt.cancel(true);
    }
}
