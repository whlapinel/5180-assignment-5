package com.example.assignment5;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MainFragment extends Fragment {

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        Button startBtn = view.findViewById(R.id.startBtn);
        startBtn.setOnClickListener(v -> {
            MainFragment.FragmentSwitcher listener = (MainFragment.FragmentSwitcher) getActivity();
            assert listener != null;
            listener.switchFragment(new CreateUserFragment());
        });
        return view;
    }

    public interface FragmentSwitcher {
        void switchFragment(Fragment fragment);

        void popFragment();

        void setUser(User user);

        void goToProfile();
    }
}
