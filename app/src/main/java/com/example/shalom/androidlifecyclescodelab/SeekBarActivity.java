package com.example.shalom.androidlifecyclescodelab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.shalom.androidlifecyclescodelab.R;

public class SeekBarActivity extends AppCompatActivity {

    /*Hosts SeekBarFragments*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seekbar);

    }
}
