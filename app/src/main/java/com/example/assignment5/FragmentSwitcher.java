package com.example.assignment5;

import androidx.fragment.app.Fragment;

public interface FragmentSwitcher {
    void switchFragment(Fragment fragment);

    void popFragment();

    void setUser(User user);

    void newProfileFragment(User user);

    void goToUpdatedProfile();
}
