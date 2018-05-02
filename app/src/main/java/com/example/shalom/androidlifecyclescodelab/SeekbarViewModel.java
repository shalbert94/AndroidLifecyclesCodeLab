package com.example.shalom.androidlifecyclescodelab;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/*Enables data persistance of SeekBar's value*/
public class SeekbarViewModel extends ViewModel{
    /*Seekbar value is monitored by LiveData*/
    MutableLiveData<Integer> seekbarValue = new MutableLiveData<>();
}
