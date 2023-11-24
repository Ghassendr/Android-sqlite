package com.example.tp6
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonValider = findViewById<Button>(R.id.buttonValider)
        val buttonAnnuler = findViewById<Button>(R.id.buttonAnnuler)

        buttonValider.setOnClickListener {
            if (tousLesChampsRemplis()) {
                afficherAlerteConfirmationEnvoi()
            } else {
                afficherAlerteChampsManquants()
            }
        }

        buttonAnnuler.setOnClickListener {
            afficherAlerteRemiseAZeroChamps()
        }
    }

    private fun estChampVide(editText: EditText): Boolean {
        return editText.text.toString().isEmpty()
    }

    private fun tousLesChampsRemplis(): Boolean {
        val editTextNom = findViewById<EditText>(R.id.editTextNom)
        val editTextPrenom = findViewById<EditText>(R.id.editTextPrenom)
        val editTextTel = findViewById<EditText>(R.id.editTextTel)
        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val editTextLogin = findViewById<EditText>(R.id.editTextLgin)
        val editTextMdp = findViewById<EditText>(R.id.editTextMdp)

        return !estChampVide(editTextNom) && !estChampVide(editTextPrenom) &&
                !estChampVide(editTextTel) && !estChampVide(editTextEmail) &&
                !estChampVide(editTextLogin) && !estChampVide(editTextMdp)
    }

    private fun afficherAlerteChampsManquants() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Champs manquants")
        builder.setMessage("Tous les champs doivent être remplis.")
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    private fun afficherAlerteConfirmationEnvoi() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirmation")
        builder.setMessage("Êtes-vous sûr de vouloir envoyer ces informations?")
        builder.setPositiveButton("Oui") { dialog, _ ->

            val dbHelper = UsersDBHelper(this)
            val db = dbHelper.writableDatabase

            val editTextNom = findViewById<EditText>(R.id.editTextNom)
            val editTextPrenom = findViewById<EditText>(R.id.editTextPrenom)
            val editTextTel = findViewById<EditText>(R.id.editTextTel)
            val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
            val editTextLogin = findViewById<EditText>(R.id.editTextLgin)
            val editTextMdp = findViewById<EditText>(R.id.editTextMdp)

            val nom = editTextNom.text.toString()
            val prenom = editTextPrenom.text.toString()
            val tel = editTextTel.text.toString()
            val email = editTextEmail.text.toString()
            val login = editTextLogin.text.toString()
            val mdp = editTextMdp.text.toString()

            if (nom.isNotEmpty() && prenom.isNotEmpty() && tel.isNotEmpty() &&
                email.isNotEmpty() && login.isNotEmpty() && mdp.isNotEmpty()
            ) {
                val values = ContentValues().apply {
                    put(EtudiantBC.EtudiantEntry.COLUMN_NAME_NOM, nom)
                    put(EtudiantBC.EtudiantEntry.COLUMN_NAME_PRENOM, prenom)
                    put(EtudiantBC.EtudiantEntry.COLUMN_NAME_PHONE, tel)
                    put(EtudiantBC.EtudiantEntry.COLUMN_NAME_EMAIL, email)
                    put(EtudiantBC.EtudiantEntry.COLUMN_NAME_LOGIN, login)
                    put(EtudiantBC.EtudiantEntry.COLUMN_NAME_MDP, mdp)
                }

                val newRowId = db.insert(EtudiantBC.EtudiantEntry.TABLE_NAME, null, values)
                val intent = Intent(this, EtudiantMain::class.java)
                startActivity(intent)
                if (newRowId != -1L) {
                     } else {

                }
            } else {
                }

            db.close()
            dbHelper.close()



            dialog.dismiss()
        }
        builder.setNegativeButton("Non") { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    private fun afficherAlerteRemiseAZeroChamps() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Remise à zéro des champs")
        builder.setMessage("Voulez-vous remettre à zéro tous les champs?")
        builder.setPositiveButton("Oui") { dialog, _ ->
            resetAllFields()
            dialog.dismiss()
        }
        builder.setNegativeButton("Non") { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    private fun resetAllFields() {
        val editTextNom = findViewById<EditText>(R.id.editTextNom)
        val editTextPrenom = findViewById<EditText>(R.id.editTextPrenom)
        val editTextTel = findViewById<EditText>(R.id.editTextTel)
        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val editTextLogin = findViewById<EditText>(R.id.editTextLgin)
        val editTextMdp = findViewById<EditText>(R.id.editTextMdp)

        editTextNom.text.clear()
        editTextPrenom.text.clear()
        editTextTel.text.clear()
        editTextEmail.text.clear()
        editTextLogin.text.clear()
        editTextMdp.text.clear()
    }
}
