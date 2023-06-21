package com.company.lostandfound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Script;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.company.lostandfound.Activities.LoginActivity;
import com.company.lostandfound.Activities.StartingActivity;
import com.company.lostandfound.Fragments.AddItemFragment;
import com.company.lostandfound.Fragments.NotificationFragment;
import com.company.lostandfound.Fragments.ProfileFragment;
import com.company.lostandfound.Fragments.SearchItemFragment;
import com.company.lostandfound.Fragments.homeFragment;
import com.company.lostandfound.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = (FrameLayout) findViewById(R.id.FragmentContainer);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.BottemNavigationView);
        Menu menuNav = bottomNavigationView.getMenu();
        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentContainer, new homeFragment()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavigationMethod);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavigationMethod =
            item -> {

                Fragment fragment = null;
                switch (item.getItemId()) {

                    case R.id.homeMenu:
                        fragment = new homeFragment();
                        break;
                    case R.id.searchMenu:
                        fragment = new SearchItemFragment();
                        break;

                    case R.id.addItemMenu:
                        fragment = new AddItemFragment();
                        break;
                    case R.id.notificationMenu:
                        fragment = new NotificationFragment();
                        break;
                    case R.id.profileMenu:
                        fragment = new ProfileFragment();
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.FragmentContainer, fragment).commit();
                return true;
            };


    @Override
    protected void onStart() {
        super.onStart();
    }
}