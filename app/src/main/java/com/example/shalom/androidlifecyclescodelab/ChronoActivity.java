package com.example.shalom.androidlifecyclescodelab;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Chronometer;

public class ChronoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chrono);

        /*{@code ViewModelStore} provides a new ViewModel or one that was previously created*/
        ChronometerViewModel viewModel = ViewModelProviders.of(this).get(ChronometerViewModel.class);

        /*Get reference to {@code id/chronometer}*/
        Chronometer chronometer = (Chronometer) findViewById(R.id.chronometer);

        if(viewModel.getStartTime() == null) {
            /*When start time is not set*/
            long startTime = SystemClock.elapsedRealtime();
            viewModel.init(startTime);
            chronometer.setBase(viewModel.getStartTime());
        } else {
            /*Viewmodel was retained after setting the start time, so set Chronometer's base to
            * viewModel's {@code startTime}*/
            long startTime = viewModel.getStartTime();
            chronometer.setBase(startTime);
        }

        chronometer.start();

    }
}
