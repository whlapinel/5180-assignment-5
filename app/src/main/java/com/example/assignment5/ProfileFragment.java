package com.example.assignment5;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.assignment5.databinding.FragmentProfileBinding;

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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentProfileBinding binding = FragmentProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        if (user == null) {
            return view;
        } else {
            Log.d(TAG, "onCreateView: user is not null");
            TextView name = binding.nameValue;
            TextView email = binding.emailValue;
            TextView role = binding.roleValue;
            name.setText(user.name);
            email.setText(user.email);
            role.setText(user.role);
            Button updateBtn = binding.profileUpdateBtn;
            updateBtn.setOnClickListener(v ->{
                Log.d(TAG, "onCreateView: update button clicked");
                ProfileFragment.FragmentSwitcher listener = (ProfileFragment.FragmentSwitcher) getActivity();
                assert listener != null;
                listener.switchProfileFragment(EditUserFragment.newInstance(user));
            });
            return view;
        }
    }

    public interface FragmentSwitcher {
        void switchProfileFragment(Fragment fragment);
    }

    public void setUser(User user) {
        this.user = user;
    }
}