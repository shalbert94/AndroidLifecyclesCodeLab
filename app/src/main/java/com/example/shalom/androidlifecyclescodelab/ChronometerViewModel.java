package com.example.shalom.androidlifecyclescodelab;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

/*ViewModel holding data related to {@class ChronoActivity.java}*/
public class ChronometerViewModel extends ViewModel {

    /*Holds {@code null} or a {@code System.elapsedRealtime()}*/
    private Long startTime;

    public void init(long startTime) {
        this.startTime = startTime;
    }

    public Long getStartTime() {
        return startTime;
    }
}
