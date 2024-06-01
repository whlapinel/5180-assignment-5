package com.example.assignment5;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateUserFragment extends Fragment {


    public CreateUserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_create_user, container, false);
        Button nextBtn = view.findViewById(R.id.nextBtn);
        EditText nameInput = view.findViewById(R.id.nameCreateInput);
        EditText emailInput = view.findViewById(R.id.emailCreateInput);
        RadioGroup role = view.findViewById(R.id.roleCreateInput);

        nextBtn.setOnClickListener(v -> {
            String name = nameInput.getText().toString();
            String email = emailInput.getText().toString();
            int selectedId = role.getCheckedRadioButtonId();
            if (name.isEmpty() || email.isEmpty()) {
                Toast.makeText(getActivity(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            if (selectedId == -1) {
                Toast.makeText(getActivity(), "Please select a role", Toast.LENGTH_SHORT).show();
                return;
            }
            RadioButton selectedRadioButton = view.findViewById(selectedId);
            String roleString = selectedRadioButton.getText().toString();
            User user = new User(name, email, roleString);
            Fragment profileFragment = ProfileFragment.newInstance(user);
            FragmentSwitcher listener = (FragmentSwitcher) getActivity();
            assert listener != null;
            listener.switchFragment(profileFragment);
        });
        return view;
    }
}