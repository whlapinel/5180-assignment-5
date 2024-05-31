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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CreateUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateUserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateUserFragment newInstance(String param1, String param2) {
        CreateUserFragment fragment = new CreateUserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
        int roleInputId = R.id.roleCreateInput;

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
            MainFragment.FragmentSwitcher listener = (MainFragment.FragmentSwitcher) getActivity();
            assert listener != null;
            listener.switchFragment(profileFragment);
        });
        return view;
    }

}