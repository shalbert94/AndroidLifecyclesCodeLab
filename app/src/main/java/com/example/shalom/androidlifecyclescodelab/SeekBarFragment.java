package com.example.shalom.androidlifecyclescodelab;

import android.app.Fragment;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

public class SeekBarFragment extends Fragment{
    public static final String LOG_TAG = SeekBarFragment.class.getSimpleName();

    private SeekBar seekBar;
    private SeekbarViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seekbar, container, false);

        seekBar = (SeekBar) view.findViewById(R.id.seekbar);

        viewModel = ViewModelProviders.of((FragmentActivity) getActivity()).get(SeekbarViewModel.class);

        subscribeSeekBar();
        return view;
    }

    /*Subscribe Seekbar to LiveData*/
    private void subscribeSeekBar() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    Log.d(LOG_TAG, "Progress changed!");
                    /*Update {@code viewModel.seekbarValue}*/
                    viewModel.seekbarValue.setValue(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        viewModel.seekbarValue.observe((LifecycleOwner) getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                /*If data is updated, inform {@code seekBar} and set it's progress to new value*/
                if (integer != null) seekBar.setProgress(integer);
            }
        });
    }
}
