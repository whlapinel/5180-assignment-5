package com.example.assignment5;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements FragmentSwitcher {

    private FragmentManager fm;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.main, new MainFragment()).commit();
    }

    @Override
    public void popFragment() {
        fm.popBackStack();
    }

    @Override
    public void switchFragment(Fragment fragment) {
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.main, fragment, fragment.getClass().getName());
        ft.addToBackStack(fragment.getClass().getName());
        ft.commit();
    }

    public void goToProfile() {
        Fragment profileFragment = fm.findFragmentByTag(ProfileFragment.class.getName());
        assert profileFragment != null;
        ((ProfileFragment) profileFragment).setUser(user);
        popFragment();
    }

    public void setUser(User user) {
        this.user = user;
    }

}
