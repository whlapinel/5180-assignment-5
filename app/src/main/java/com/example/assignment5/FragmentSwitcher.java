package com.example.assignment5;

import androidx.fragment.app.Fragment;

public interface FragmentSwitcher {
    void switchFragment(Fragment fragment);

    void popFragment();

    void setUser(User user);

    void goToProfile();
}
