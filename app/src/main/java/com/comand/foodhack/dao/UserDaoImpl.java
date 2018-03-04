package com.comand.foodhack.dao;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.comand.foodhack.entity.Activity;
import com.comand.foodhack.entity.Sex;
import com.comand.foodhack.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private SQLiteDatabase db;

    public UserDaoImpl(SQLiteDatabase db) {
        this.db = db;
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from users", null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int sex_id = cursor.getInt(cursor.getColumnIndex("sex_id"));
                Cursor sex_cursor = db.rawQuery("select * from sex where id = " + sex_id, null);
                sex_cursor.moveToFirst();
                String sexName = sex_cursor.getString(sex_cursor.getColumnIndex("name"));
                double sexCoef = sex_cursor.getDouble(sex_cursor.getColumnIndex("coef"));
                sex_cursor.close();
                int age = cursor.getInt(cursor.getColumnIndex("age"));
                double height = cursor.getDouble(cursor.getColumnIndex("height"));
                double weight = cursor.getDouble(cursor.getColumnIndex("weight"));
                int activity_id = cursor.getInt(cursor.getColumnIndex("activity_id"));
                Cursor activity_cursor = db.rawQuery("select * from activity where id = " + activity_id, null);
                activity_cursor.moveToFirst();
                String activityName = activity_cursor.getString(activity_cursor.getColumnIndex("name"));
                double activityCoef = activity_cursor.getDouble(activity_cursor.getColumnIndex("coef"));
                activity_cursor.close();
                Sex sex = new Sex(sexName, sexCoef);
                Activity activity = new Activity(activityName, activityCoef);
                users.add(new User(name, sex, age, height, weight, activity));
                cursor.moveToNext();
            }
        }
        cursor.close();

        return users;
    }

}
