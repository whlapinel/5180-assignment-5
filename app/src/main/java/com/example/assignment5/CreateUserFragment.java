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

import com.example.assignment5.databinding.FragmentCreateUserBinding;


public class CreateUserFragment extends Fragment {


    public CreateUserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentCreateUserBinding binding = FragmentCreateUserBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        Button nextBtn = binding.nextBtn;
        EditText nameInput = binding.nameCreateInput;
        EditText emailInput = binding.emailCreateInput;
        RadioGroup role = binding.roleCreateInput;

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
            FragmentSwitcher listener = (CreateUserFragment.FragmentSwitcher) getActivity();
            assert listener != null;
            listener.setUser(user);
            listener.newProfileFragment(user);
        });
        return view;
    }
    public interface FragmentSwitcher {
        void newProfileFragment(User user);
        void setUser(User user);

    }
}