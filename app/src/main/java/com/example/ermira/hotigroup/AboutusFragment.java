package com.example.ermira.hotigroup;

/**
 * Created by ermira on 12/4/2016.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class AboutusFragment extends Fragment {

    public AboutusFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_aboutus, container, false);

        return rootView;
    }

}
