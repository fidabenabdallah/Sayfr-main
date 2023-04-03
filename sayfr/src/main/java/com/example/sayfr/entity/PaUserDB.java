package com.example.sayfr.entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;



import com.example.sayfr.Dao.UserDao;

public class PaUserDB extends SQLiteOpenHelper {
        public PaUserDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String qry1 = "create table if not exists Patusers(blutooth text, username text , password text)";
            sqLiteDatabase.execSQL(qry1);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
        public void register(String blutooth, String  username , String password ){
            ContentValues cv = new ContentValues();
            cv.put("blutooth",blutooth);
            cv.put("username",username);
            cv.put("password",password);
            SQLiteDatabase db = getWritableDatabase();
            db.insert("Patusers",null,cv);
            db.close();

        }

        public int login(String username, String password){
            int result=0;
            String str[]= new String[2];
            str[0] = username;
            str[1] =password;
            SQLiteDatabase db = getReadableDatabase();
            Cursor c = db.rawQuery( "select * from Patusers where username=? and password=?",str);
            if(c.moveToFirst()) {
                result = 1;
            }

            return result;
        }
    }


