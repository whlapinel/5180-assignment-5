package com.example.assignment5;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment5.databinding.FragmentEditUserBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditUserFragment extends Fragment {
    FragmentSwitcher fragmentSwitcher;
    private static final String USER = "user";


    private User user;
    private static final String TAG = "EditUserFragment: ";

    public EditUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param user User.
     * @return A new instance of fragment ProfileFragment.
     */

    public static EditUserFragment newInstance(User user) {
        EditUserFragment fragment = new EditUserFragment();
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
        fragmentSwitcher = (FragmentSwitcher) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentEditUserBinding binding = FragmentEditUserBinding.inflate(inflater, container, false);
        Log.d(TAG, "onCreateView: ");
        Log.d(TAG, "onCreateView: " + "user.name = " + user.name);
        View view = binding.getRoot();
        if (user == null) {
            return view;
        } else {
            Button submitBtn = binding.submitBtn;
            Button cancelBtn = binding.cancelBtn;
            EditText nameEditInput = binding.nameEditInput;
            EditText emailEditInput = binding.emailEditInput;
            RadioGroup roleEditInput = binding.roleEditInput;
            nameEditInput.setText(user.name);
            emailEditInput.setText(user.email);

            switch (user.role) {
                case "Student":
                    binding.studentEditRadioBtn.setChecked(true);
                    break;
                case "Employee":
                    binding.employeeEditRadioBtn.setChecked(true);
                    break;
                case "Other":
                    binding.otherEditRadioBtn.setChecked(true);
                    break;
            }

            cancelBtn.setOnClickListener(v -> {
                Log.d(TAG, "onCreateView: cancelBtn clicked");
                FragmentSwitcher listener = (FragmentSwitcher) getActivity();
                assert listener != null;
                listener.popFragment();
            });

            submitBtn.setOnClickListener(v -> {
                String name = nameEditInput.getText().toString();
                String email = emailEditInput.getText().toString();
                if (name.isEmpty() || email.isEmpty()) {
                    Toast.makeText(getActivity(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                int selectedRadioId = roleEditInput.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = view.findViewById(selectedRadioId);
                String role = selectedRadioButton.getText().toString();
                User updatedUser = new User(name, email, role);
                fragmentSwitcher.setUser(updatedUser);
                fragmentSwitcher.goToUpdatedProfile();
            });
        }
        return view;
    }
}
