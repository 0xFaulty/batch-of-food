package com.comand.foodhack.utils;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper implements BaseColumns {

    private static final String DATABASE_NAME = "fooddatabase.db";
    private static final int DATABASE_VERSION = 1;

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Запишем в журнал
        Log.w("SQLite", "Обновляемся с версии " + oldVersion + " на версию " + newVersion);

        // Удаляем старую таблицу и создаём новую
        db.execSQL("DROP TABLE IF IT EXISTS orders");
        db.execSQL("DROP TABLE IF IT EXISTS menus");
        db.execSQL("DROP TABLE IF IT EXISTS menutype");
        db.execSQL("DROP TABLE IF IT EXISTS products");
        db.execSQL("DROP TABLE IF IT EXISTS users");
        db.execSQL("DROP TABLE IF IT EXISTS activity");
        // Создаём новую таблицу
        onCreate(db);
    }

    private static final String DB_ACTIVITY = "CREATE TABLE IF NOT EXISTS activity " +
            "(id INTEGER PRIMARY KEY autoincrement, name TEXT);";
    private static final String DB_USERS = "CREATE TABLE IF NOT EXISTS users " +
            "(id INTEGER PRIMARY KEY autoincrement, name TEXT, age INTEGER, height REAL, weight REAL, activity_id INTEGER REFERENCES activity(id));";
    private static final String DB_PRODUCTS = "CREATE TABLE IF NOT EXISTS products " +
            "(id INTEGER PRIMARY KEY autoincrement, name TEXT, height REAL, calories REAL, spec TEXT);";
    private static final String DB_MENUTYPE = "CREATE TABLE IF NOT EXISTS menutype " +
            "(id INTEGER PRIMARY KEY autoincrement, name TEXT);";
    private static final String DB_MENU = "CREATE TABLE IF NOT EXISTS menus " +
            "(id INTEGER PRIMARY KEY autoincrement, products TEXT, menutype INTEGER REFERENCES menutype(id), weeknumber INTEGER);";
    private static final String DB_ORDERS = "CREATE TABLE IF NOT EXISTS orders " +
            "(id INTEGER PRIMARY KEY autoincrement, user_id INTEGER REFERENCES users(id), menu_id INTEGER REFERENCES menus(id));";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_ACTIVITY);
        db.execSQL(DB_USERS);
        db.execSQL(DB_PRODUCTS);
        db.execSQL(DB_MENUTYPE);
        db.execSQL(DB_MENU);
        db.execSQL(DB_ORDERS);
    }

}
