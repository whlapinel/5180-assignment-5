package com.example.assignment5;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.assignment5.databinding.FragmentMainBinding;


public class MainFragment extends Fragment {

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentMainBinding binding = FragmentMainBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        Button startBtn = binding.startBtn;
        startBtn.setOnClickListener(v -> {
            FragmentSwitcher listener = (FragmentSwitcher) getActivity();
            assert listener != null;
            listener.switchFragment(new CreateUserFragment());
        });
        return view;
    }

    public interface FragmentSwitcher {
        void switchFragment(Fragment fragment);
    }
}

