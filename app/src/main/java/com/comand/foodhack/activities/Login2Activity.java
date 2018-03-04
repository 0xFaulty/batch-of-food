package com.comand.foodhack.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.comand.foodhack.Person;
import com.comand.foodhack.R;

public class Login2Activity extends AppCompatActivity {

    EditText etxt_login_name;
    EditText etxt_age;
    EditText etxt_height;
    EditText etxt_weight;
    private int sexUser;
    private double activityUser;

    Button enter;

    private TextView infoActivityUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        etxt_login_name = (EditText) findViewById(R.id.etxt_login_name);
        etxt_age = findViewById(R.id.etxt_age);
        etxt_height = findViewById(R.id.etxt_height);
        etxt_weight = findViewById(R.id.etxt_weight);

        infoActivityUser = findViewById(R.id.txt_infoActivityUser);

        RadioGroup radioGroup_activityUser = (RadioGroup) findViewById(R.id.etxt_activityUser);
        radioGroup_activityUser.clearCheck();
        radioGroup_activityUser.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case -1:
                        infoActivityUser.setText("Выберите");
                        break;
                    case R.id.radioButton_1:
                        infoActivityUser.setText("Сидячий офисный планктон");
                        activityUser = 1.2;
                        break;
                    case R.id.radioButton_2:
                        infoActivityUser.setText("2 км пешком каждый день");
                        activityUser = 1.46;
                        break;
                    case R.id.radioButton_3:
                        infoActivityUser.setText("Занимаюсь спортом, хожу в зал");
                        activityUser = 1.73;
                        break;
                }
            }
        });

        RadioGroup radioGroup_sexUser = (RadioGroup) findViewById(R.id.etxt_sexUser);
        radioGroup_sexUser.clearCheck();
        radioGroup_sexUser.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case -1:
                        break;
                    case R.id.radioButton_men:
                        sexUser = -161 ;
                        break;
                    case R.id.radioButton_2:
                        sexUser = 5;
                        break;
                }
            }
        });

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Person person = new Person(String.valueOf(etxt_login_name.getText()),
                        sexUser,
                        Integer.parseInt(etxt_age.getText().toString()),
                        Integer.parseInt(etxt_height.getText().toString()),
                        Integer.parseInt(etxt_weight.getText().toString()),
                        activityUser);

            }
        });
    }

}
