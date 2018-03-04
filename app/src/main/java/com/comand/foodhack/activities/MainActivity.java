package com.comand.foodhack.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.comand.foodhack.R;
import com.comand.foodhack.fragment.Login2ActivityFragment;
import com.comand.foodhack.fragment.PersonalActivityFragment;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_menu:
                    mTextMessage.setText(R.string.title_menu);
                    return true;
                case R.id.navigation_user:
                    //работало
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);

//                    transaction.replace(R.id.navigation_menu, new Login2ActivityFragment()).commit();

//                    mTextMessage.setText(R.string.title_user);
//                    transaction.replace(R.id.main_fragment, new EventsFragment()).commit();
                    return true;
                case R.id.navigation_notifications:
//                    mTextMessage.setText(R.string.title_notifications);
                    transaction.replace(R.id.navigation_menu, new PersonalActivityFragment()).commit();

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setSelectedItemId(R.id.navigation_menu);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

//    public void onClick(View view) {
//        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//        startActivity(intent);
//    }

}
