package com.example.shalom.androidlifecyclescodelab.chronometer;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.SystemClock;
import android.support.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

/*ViewModel holding data related to {@class ChronoActivity.java}*/
public class LiveDataTimerViewModel extends ViewModel {

    private static final int ONE_SECOND = 1000;

    /*Value that will be observed*/
    private MutableLiveData<Long> elapsedTime = new MutableLiveData<>();

    private Long initialTime;

    public LiveDataTimerViewModel() {
        initialTime = SystemClock.elapsedRealtime();
        Timer timer = new Timer();

        /*Set frequency of data updates*/
        timer.scheduleAtFixedRate(new TimerTask() {
            /*Task to be run*/
            @Override
            public void run() {
                final long newValue = (SystemClock.elapsedRealtime() - initialTime) / 1000;
                /*Updated {@code elapsedTime}*/
                elapsedTime.postValue(newValue);
            }
        }, ONE_SECOND, ONE_SECOND);
    }

    public LiveData<Long> getElapsedTime() {
        return elapsedTime;
    }

}
