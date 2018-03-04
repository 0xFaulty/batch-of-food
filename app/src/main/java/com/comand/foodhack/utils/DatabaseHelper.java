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
        db.execSQL("DROP TABLE IF IT EXISTS sex");
        db.execSQL("DROP TABLE IF IT EXISTS activity");
        // Создаём новую таблицу
        onCreate(db);
    }

    private static final String DB_ACTIVITY = "CREATE TABLE IF NOT EXISTS activity " +
            "(id INTEGER PRIMARY KEY autoincrement, name TEXT, coef REAL);";
    private static final String DB_SEX = "CREATE TABLE IF NOT EXISTS sex " +
            "(id INTEGER PRIMARY KEY autoincrement, name TEXT, coef REAL);";
    private static final String DB_USERS = "CREATE TABLE IF NOT EXISTS users " +
            "(id INTEGER PRIMARY KEY autoincrement, name TEXT, sex_id INTEGER REFERENCES sex(id), " +
            "age INTEGER, height REAL, weight REAL, activity_id INTEGER REFERENCES activity(id));";
    private static final String DB_PRODUCTS = "CREATE TABLE IF NOT EXISTS products " +
            "(id INTEGER PRIMARY KEY autoincrement, name TEXT, height REAL, calories REAL, spec TEXT);";
    private static final String DB_MENU_TYPE = "CREATE TABLE IF NOT EXISTS menutype " +
            "(id INTEGER PRIMARY KEY autoincrement, name TEXT);";
    private static final String DB_MENU = "CREATE TABLE IF NOT EXISTS menus " +
            "(id INTEGER PRIMARY KEY autoincrement, products TEXT, menutype INTEGER REFERENCES menutype(id));";
    private static final String DB_MENU_SCHEDULE = "CREATE TABLE IF NOT EXISTS schedule " +
            "(id INTEGER PRIMARY KEY autoincrement, menu_id INTEGER REFERENCES menu(id));";
    private static final String DB_ORDERS = "CREATE TABLE IF NOT EXISTS orders " +
            "(id INTEGER PRIMARY KEY autoincrement, user_id INTEGER REFERENCES users(id), menu_id INTEGER REFERENCES menus(id));";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_ACTIVITY);
        db.execSQL(DB_SEX);
        db.execSQL(DB_USERS);
        db.execSQL(DB_PRODUCTS);
        db.execSQL(DB_MENU_TYPE);
        db.execSQL(DB_MENU);
        db.execSQL(DB_MENU_SCHEDULE);
        db.execSQL(DB_ORDERS);
        testInsert(db);
    }

    private void testInsert(SQLiteDatabase db) {
        db.execSQL("insert into activity(name, coef) values ('Сидячий офисный планктон', 1.2);");
        db.execSQL("insert into activity(name, coef) values ('2 км пешком каждый день', 1.46);");
        db.execSQL("insert into activity(name, coef) values ('Занимаюсь спортом, хожу в зал', 1.73);");
        db.execSQL("insert into sex(name, coef) values ('Мужской', -161.0);");
        db.execSQL("insert into sex(name, coef) values ('Женский', 5.0);");
        db.execSQL("insert into users(name, sex_id, age, height, weight, activity_id) values ('Валентин', 1, 21, 187, 70, 1);");
        db.execSQL("insert into users(name, sex_id, age, height, weight, activity_id) values ('Настя', 2, 1, 1, 1, 2);");
        db.execSQL("insert into products(name, height, calories, spec) values " +
                "('Мясо по царски с сыром', 0.75, 400, 'мясо:0.5;сыр чедер:0.25');");
        db.execSQL("insert into products(name, height, calories, spec) values " +
                "('Пюре с грибами', 0.3, 200, 'пюре:0.1;грибы:0.2');");
        db.execSQL("insert into products(name, height, calories, spec) values " +
                "('Салат Цезарь', 0.5, 250, 'гренки:0.1;салат:0.1;курица:0.3');");
        db.execSQL("insert into products(name, height, calories, spec) values " +
                "('Рыба с макаронами', 0.4, 300, 'рыба:0.3;макароны:0.1');");
        db.execSQL("insert into products(name, height, calories, spec) values " +
                "('Котлеты с гречкой', 0.75, 500, 'котлеты:0.5;гречка:0.25');");
        db.execSQL("insert into products(name, height, calories, spec) values " +
                "('Курица с салатом', 0.7, 400, 'курица:0.6;салат:0.1');");
        db.execSQL("insert into products(name, height, calories, spec) values " +
                "('Мясо с любовью', 1.5, 1000, 'мясо:0.2;любовь:1.3');");
        db.execSQL("insert into menutype(name) values ('10 минут');");
        db.execSQL("insert into menutype(name) values ('Классическое');");
        db.execSQL("insert into menutype(name) values ('Семейное');");
        db.execSQL("insert into menutype(name) values ('Постное');");
        db.execSQL("insert into menutype(name) values ('Премиум');");
        db.execSQL("insert into menutype(name) values ('Фитнес');");
        db.execSQL("insert into menus(products, menutype) values ('4;5;1;3;6;2;7', 1);");
        db.execSQL("insert into menus(products, menutype) values ('1;5;6;3;4;2;7', 2);");
        db.execSQL("insert into schedule(menu_id) values (1);");
        db.execSQL("insert into schedule(menu_id) values (2);");
        db.execSQL("insert into orders(user_id, menu_id) values (1, 2);");
        db.execSQL("insert into orders(user_id, menu_id) values (2, 1);");
    }

}
