package com.example.assignment5;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {


    private static final String USER = "user";


    private User user;
    private static final String TAG = "ProfileFragment";

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(User user) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putParcelable(USER, user);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        if (getArguments() != null) {
            user = getArguments().getParcelable(USER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_profile, container, false);
        if (user == null) {
            return view;
        } else {
            Log.d(TAG, "onCreateView: user is not null");
            TextView name = view.findViewById(R.id.nameValue);
            TextView email = view.findViewById(R.id.emailValue);
            TextView role = view.findViewById(R.id.roleValue);
            name.setText(user.name);
            email.setText(user.email);
            role.setText(user.role);
            Button updateBtn = view.findViewById(R.id.profileUpdateBtn);
            updateBtn.setOnClickListener(v ->{
                Log.d(TAG, "onCreateView: update button clicked");
                FragmentSwitcher listener = (FragmentSwitcher) getActivity();
                assert listener != null;
                listener.switchFragment(EditUserFragment.newInstance(user));
            });
            return view;
        }
    }

    public void setUser(User user) {
        this.user = user;
    }
}