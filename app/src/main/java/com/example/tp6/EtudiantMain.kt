package com.example.tp6
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import android.widget.SimpleCursorAdapter

class EtudiantMain : AppCompatActivity() {

    private var dbHelper: UsersDBHelper? = null
    private var adapter: SimpleCursorAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listactivity)

        val listEtudiant = findViewById<ListView>(R.id.idlistetu)
        dbHelper = UsersDBHelper(this)
        adapter = getAdapter()

        listEtudiant.adapter = adapter
    }

    private fun getDbHelper(): UsersDBHelper {
        if (dbHelper == null) {
            dbHelper = UsersDBHelper(this)
        }
        return dbHelper!!
    }

    private fun getAdapter(): SimpleCursorAdapter {
        if (adapter == null) {
            val db: SQLiteDatabase = getDbHelper().readableDatabase
            val c: Cursor = db.rawQuery("SELECT * FROM etudiant", null)

            adapter = SimpleCursorAdapter(
                this,
                R.layout.ligne_etudiant,
                c,
                arrayOf(EtudiantBC.EtudiantEntry.COLUMN_NAME_NOM, EtudiantBC.EtudiantEntry.COLUMN_NAME_PRENOM),
                intArrayOf(R.id.nometud, R.id.preetud),
                0
            )
        }
        return adapter!!
    }
}
