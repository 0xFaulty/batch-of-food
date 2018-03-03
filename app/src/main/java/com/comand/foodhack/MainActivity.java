package com.comand.foodhack;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.comand.foodhack.utils.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    public SQLiteDatabase mSqLiteDatabase;
    private TextView resultView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_menu:
                    mTextMessage.setText(R.string.title_menu);
                    return true;
                case R.id.navigation_user:
                    mTextMessage.setText(R.string.title_user);
                    return true;
                /*case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;*/
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Button writeToDB = findViewById(R.id.show_button);
        writeToDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printUsers(v);
            }
        });

        Button readFromDB = findViewById(R.id.add_button);
        readFromDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToBase();
            }
        });

        DatabaseHelper mDatabaseHelper = new DatabaseHelper(this, "fooddatabase.db", null, 1);
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();


    }

    public void printUsers(View view) {
        Cursor cursor = mSqLiteDatabase.rawQuery("select name from users", null);
        if(cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                showMessage("User:" + cursor.getString(cursor.getColumnIndex("name")));
                cursor.moveToNext();
            }
        }
        cursor.close();
    }

    public void addToBase() {
        mSqLiteDatabase.execSQL("insert into activity(name) values ('Свободная');");
        mSqLiteDatabase.execSQL("insert into activity(name) values ('Диета');");
        mSqLiteDatabase.execSQL("insert into activity(name) values ('Спорт');");
        mSqLiteDatabase.execSQL("insert into users(name, age, height, weight, activity_id) values ('Валентин', 21, 187, 70, 1);");
        mSqLiteDatabase.execSQL("insert into users(name, age, height, weight, activity_id) values ('Настя', 1, 1, 1, 2);");
        mSqLiteDatabase.execSQL("insert into products(name, height, calories, spec) values " +
                "('Мясо по царски с сыром', 0.75, 400, 'мясо:0.5;сыр чедер:0.25');");
        mSqLiteDatabase.execSQL("insert into products(name, height, calories, spec) values " +
                "('Пюре с грибами', 0.3, 200, 'пюре:0.1;грибы:0.2');");
        mSqLiteDatabase.execSQL("insert into products(name, height, calories, spec) values " +
                "('Салат Цезарь', 0.5, 250, 'гренки:0.1;салат:0.1;курица:0.3');");
        mSqLiteDatabase.execSQL("insert into products(name, height, calories, spec) values " +
                "('Рыба с макаронами', 0.4, 300, 'рыба:0.3;макароны:0.1');");
        mSqLiteDatabase.execSQL("insert into products(name, height, calories, spec) values " +
                "('Котлеты с гречкой', 0.75, 500, 'котлеты:0.5;гречка:0.25');");
        mSqLiteDatabase.execSQL("insert into products(name, height, calories, spec) values " +
                "('Курица с салатом', 0.7, 400, 'курица:0.6;салат:0.1');");
        mSqLiteDatabase.execSQL("insert into products(name, height, calories, spec) values " +
                "('Мясо с любовью', 1.5, 1000, 'мясо:0.2;любовь:1.3');");
        mSqLiteDatabase.execSQL("insert into menutype(name) values ('10 минут');");
        mSqLiteDatabase.execSQL("insert into menutype(name) values ('Классическое');");
        mSqLiteDatabase.execSQL("insert into menutype(name) values ('Семейное');");
        mSqLiteDatabase.execSQL("insert into menutype(name) values ('Постное');");
        mSqLiteDatabase.execSQL("insert into menutype(name) values ('Премиум');");
        mSqLiteDatabase.execSQL("insert into menutype(name) values ('Фитнес');");
        mSqLiteDatabase.execSQL("insert into menus(products, menutype, weeknumber) values ('4;5;1;3;6;2;7', 1, 1);");
        mSqLiteDatabase.execSQL("insert into menus(products, menutype, weeknumber) values ('1;5;6;3;4;2;7', 1, 1);");
        mSqLiteDatabase.execSQL("insert into orders(user_id, menu_id) values (1, 2);");
        mSqLiteDatabase.execSQL("insert into orders(user_id, menu_id) values (2, 1);");

        showMessage("Done!");
    }

    private void showMessage(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
    }

}
