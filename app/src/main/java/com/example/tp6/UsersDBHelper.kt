package com.example.tp6

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

import java.util.ArrayList

class UsersDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
   public val db = writableDatabase
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }


    companion object {
        public val DATABASE_VERSION = 1
        public val DATABASE_NAME = "PFE.db"

        public val SQL_CREATE_ENTRIES =
            "CREATE TABLE " + EtudiantBC.EtudiantEntry.TABLE_NAME + " (" +
                    EtudiantBC.EtudiantEntry.COLUMN_NAME_NOM  +
                    EtudiantBC.EtudiantEntry.COLUMN_NAME_PRENOM + " TEXT," +
                    EtudiantBC.EtudiantEntry.COLUMN_NAME_EMAIL + " TEXT" +
                    EtudiantBC.EtudiantEntry.COLUMN_NAME_LOGIN  +
                    EtudiantBC.EtudiantEntry.COLUMN_NAME_MDP + " TEXT," +
                    EtudiantBC.EtudiantEntry.COLUMN_NAME_PHONE + " TEXT" +
                    ")";

        public val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + EtudiantBC.EtudiantEntry.TABLE_NAME
    }

}