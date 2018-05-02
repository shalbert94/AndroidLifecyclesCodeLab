package com.example.shalom.androidlifecyclescodelab;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ChronoActivity extends AppCompatActivity {
    public static final String LOG_TAG = ChronoActivity.class.getSimpleName();

    private LiveDataTimerViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chrono);

        /*{@code ViewModelStore} provides a new ViewModel or one that was previously created*/
        viewModel = ViewModelProviders.of(this).get(LiveDataTimerViewModel.class);

        subscribe();
    }

    /**
     * Attaches {@code elapsedTimeObserver} to {@code viewModel} to update R.id.timer_textview's text
     * every second
     */
    private void subscribe() {
        /*Observes changes to {@code viewModel.getElapsedTime()} and makes relevant changes in onChanged() */
        final Observer<Long> elapsedTimeObserver = new Observer<Long>() {
            /**
             * Handles changes to the observed LiveData
             * @param aLong the value that was changed
             */
            @Override
            public void onChanged(@Nullable Long aLong) {
                /*Updated text for R.id.timer_textview*/
                String newText = ChronoActivity.this.getResources().getString(R.string.seconds, aLong);
                ((TextView) findViewById(R.id.timer_textview)).setText(newText);
                Log.d(LOG_TAG, "Updating timer");
            }
        };

        /*Enable {@code elapsedTimeObserver} to observe a specific value in {@code viewModel}*/
        viewModel.getElapsedTime().observe(this, elapsedTimeObserver);
    }
}
